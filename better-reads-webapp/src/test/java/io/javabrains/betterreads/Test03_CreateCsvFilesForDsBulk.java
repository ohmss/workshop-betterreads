package io.javabrains.betterreads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.datastax.stargate.sdk.utils.Utils;

import io.javabrains.betterreads.author.Author;

public class Test03_CreateCsvFilesForDsBulk {

 // Input
    public static final String authorFile   = "/Users/cedricklunven/Downloads/ol_dump_authors_2021-11-30.txt";
    public static final String workFile     = "/Users/cedricklunven/Downloads/ol_dump_works_2021-11-30.txt";
    
    // Output
    public static final String outputFolder = "/Users/cedricklunven/Downloads/output/"; 
    public static final String HEADERS      = "id,author_id,author_names,book_description,book_name,cover_ids,published_date\n";
    public static final long BOOKS_PER_FILE = 250000;
    
    @Test
    public void should_generate_csv() throws IOException {
        System.out.println("Generating files of " + BOOKS_PER_FILE + " records");
        generateCsvBookById(loadAuthors(), 0);
    }
    
    /**
     * Load pivot file author_id/autho_name as an in-memeory map.
     */
    public Map<String, Author> loadAuthors() throws IOException {
        System.out.println("Loading Authors in memory as a pivot (~9 millions records, ~1min)");
        long start = System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger();
        Map<String, Author> res =  Files.lines(Paths.get(authorFile))
             .map(l -> l.substring(l.indexOf("{")))
             .map(JSONObject::new)
             .map(json -> new Author(
                     json.optString("key").replaceAll("/authors/", ""),
                     json.optString("name"),
                     json.optString("personal_name")))
             .peek(a -> {
                 if (ai.incrementAndGet()%100000 == 0 ) {
                     System.out.println("- " + ai + " authors loaded.");
                 }
             })
             .collect(Collectors.toMap(Author::getId, Function.identity()));
        long end = System.currentTimeMillis();
        System.out.println("Done in " + (end-start) + " millis.");
        return res;
    }
    
    
    /**
     * Generate CSV files for DSBulk.
     * 
     * @param authors
     *      in memory map
     * @param start
     *      offset (you can stop the process)
     * @throws IOException
     *      
     */
    public void generateCsvBookById(Map<String, Author> authors, long start)  
    throws IOException {
        
        AtomicLong rownum = new AtomicLong(start);
         
        Files.lines(Paths.get(workFile)).forEach(row -> {
            try {
                if (rownum.get() >= start) {
                    String     json    = row.substring(row.indexOf("{"));
                    String     newLine = mapWorkRow(authors, new JSONObject(json));
                    Files.write(getPath(start, rownum.get()), newLine.getBytes(), StandardOpenOption.APPEND);
                    rownum.incrementAndGet();
                }
            } catch(Exception e) { 
                // swallow
            }
        });
    }
    
    /**
     * Initialize a new file when needed.
     * @param rowNum
     *      current roNum
     * @return
     * @throws IOException
     */
    private Path getPath(long start, long rowNum) throws IOException {
        String fileName = outputFolder + "book_by_id_" + (int) Math.floor(rowNum/BOOKS_PER_FILE) + ".csv";
        Path path = Paths.get(fileName);
        if (rowNum == start || rowNum % BOOKS_PER_FILE==0 ) {
            System.out.println("Creating " + fileName + " as rownum is " + rowNum);
            new File(fileName).createNewFile();
            Files.write(path, HEADERS.getBytes(), StandardOpenOption.APPEND);
        }
       
        return path;
    }
    
    /**
     * Convert a Json into expected row
     * @param authors
     *      in memory map
     * @param jsonObj
     *      current works row
     * @return
     *      target row
     */
    private String mapWorkRow(Map<String, Author> authors, JSONObject jsonObj) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"); 
        DateTimeFormatter dt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dt3 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        try {
            // ID
            sb.append(jsonObj.getString("key").replaceAll("/works/", ""));
            
            // --- author_id, author names
            List<String> authorIds   = new ArrayList<>();
            List<String> authorNames = new ArrayList<>();
            JSONArray authorsArray = jsonObj.optJSONArray("authors");
            if (authorsArray != null) {
                for(int i=0;i<authorsArray.length();i++) {
                    JSONObject authorItem = authorsArray.getJSONObject(i);
                    JSONObject author = authorItem.optJSONObject("author");
                    if (author != null) {
                        String authorid = author
                                .getString("key")
                                .replaceAll("/authors/", "")
                                .replaceAll("/a/", "");
                        if (authors.containsKey(authorid)) {
                            authorNames.add("\\\"" + authors.get(authorid).getName() + "\\\"");
                        }
                        authorIds.add("\\\"" + authorid + "\\\"");
                    }
                }
            }
            sb.append(",\"" + authorIds.toString() + "\"");
            sb.append(",\"" + authorNames.toString() + "\"");
            
            // --- book_description
            sb.append(",");
            JSONObject desc = jsonObj.optJSONObject("description");
            if (desc != null) {
                sb.append("\"" + desc.optString("value")
                                     .replaceAll("\"", "")
                                     .subSequence(0, Math.min(desc.length()-1,4000)) + "\"");
            }
            
            // --- book_name
            sb.append(",");
            String title = jsonObj.optString("title");
            if (Utils.hasLength(title)) sb.append("\"" + title.replaceAll("\"", "") + "\"");
            
            // -- cover_ids
            List<String> coverIds = new ArrayList<>();
            JSONArray coversArray = jsonObj.optJSONArray("covers");
            if (coversArray != null) {
                for(int i=0;i<coversArray.length();i++) {
                    coverIds.add("\\\"" + String.valueOf(coversArray.getLong(i)) + "\\\"");
                }
            }
            sb.append(",\"" + coverIds.toString() + "\"");
            
            // -- published_date
            JSONObject pubdate = jsonObj.optJSONObject("created");
            sb.append(",");
            if (pubdate != null) {
                String srVal = pubdate.optString("value");
                try {
                    sb.append(LocalDate.parse(srVal, dt).format(dt2));
                } catch(Exception ex) {
                    sb.append(LocalDate.parse(srVal, dt3).format(dt2));
                }
            }
            sb.append("\n");       
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    
}
