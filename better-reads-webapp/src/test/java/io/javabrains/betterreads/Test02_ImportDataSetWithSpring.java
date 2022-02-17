package io.javabrains.betterreads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.javabrains.betterreads.author.Author;
import io.javabrains.betterreads.author.AuthorRepository;
import io.javabrains.betterreads.book.Book;
import io.javabrains.betterreads.book.BookRepository;

@SpringBootTest
public class Test02_ImportDataSetWithSpring {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    private String workFile   = "src/test/resources/test-works.txt";
    
    private String authorFile = "src/test/resources/test-authors.txt";
    
    @Test
    public void should_load_authors() throws IOException {
        loadAuthors(0);
        loadBooks();
    }
    
    public void loadAuthors(int startline) throws IOException {
       final AtomicInteger currentLine = new AtomicInteger(0);
       Files.lines(Paths.get(authorFile)).forEach(l -> {
           try {
               if (currentLine.incrementAndGet()> startline) {
                   String json = l.substring(l.indexOf("{"));
                   JSONObject jsonObj = new JSONObject(json);
                   Author a = new Author();
                   a.setName(jsonObj.optString("name"));
                   a.setPersonalName(jsonObj.optString("personal_name"));
                   a.setId(jsonObj.optString("key").replaceAll("/authors/", ""));
                   authorRepository.save(a);
                   System.out.println(currentLine.get() + ": Saving author: " + a.getName());
               }
           } catch(Exception e) {
               e.printStackTrace();
           }
       }); 
    }
    
    public void loadBooks() throws IOException {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"); 
        Files.lines(Paths.get(workFile)).forEach(l -> {
               try {
                   String json = l.substring(l.indexOf("{"));
                   JSONObject jsonObj = new JSONObject(json);
                   Book b = new Book();
                   b.setId(jsonObj.getString("key").replaceAll("/works/", ""));
                   b.setName(jsonObj.optString("title"));
                   
                   JSONObject jsonDesc = jsonObj.optJSONObject("decription");
                   if (jsonDesc != null) {
                       b.setDescription(jsonDesc.optString("value"));
                   }
                   
                   JSONObject pubdate = jsonObj.optJSONObject("created");
                   if (pubdate != null) {
                       String srVal = pubdate.optString("value");
                       b.setPublishedDate(LocalDate.parse(srVal, dt));
                   }
                   
                   JSONArray coversArray = jsonObj.optJSONArray("covers");
                   if (coversArray != null) {
                       List<String> coverIds = new ArrayList<>();
                       for(int i=0;i<coversArray.length();i++) {
                           coverIds.add(String.valueOf(coversArray.getLong(i)));
                       }
                       b.setCoverIds(coverIds);
                   }
                  
                   JSONArray authorsArray = jsonObj.optJSONArray("authors");
                   if (authorsArray != null) {
                       List<String> authorIds = new ArrayList<>();
                       for(int i=0;i<authorsArray.length();i++) {
                           authorIds.add(
                                   authorsArray.getJSONObject(i)
                                               .getJSONObject("author")
                                               .getString("key").replaceAll("/authors/", "")); 
                       }
                       b.setAuthorIds(authorIds);
                       b.setAuthorNames(authorIds.stream().map(authorRepository::findById).map(o -> {
                           if (!o.isPresent()) {
                               return "Uknown";
                           }
                           return o.get().getName();
                       }).collect(Collectors.toList()));
                   }

                   System.out.println("saving book..." + b.getName());
                   bookRepository.save(b);  
                   
               } catch(Exception e) {
                   e.printStackTrace();
               }
           });
    }
    
}
