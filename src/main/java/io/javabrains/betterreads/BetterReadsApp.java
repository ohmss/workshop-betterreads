package io.javabrains.betterreads;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.javabrains.betterreads.loader.DataLoaderBatch;

/**
 * Main application class with main method that runs the Spring Boot app
 */
@SpringBootApplication
public class BetterReadsApp {
    
	public static void main(String[] args) {
		SpringApplication.run(BetterReadsApp.class, args);
	}
	
	@Autowired
	DataLoaderBatch batchLoadData;
	
	@PostConstruct
	public void start() throws IOException {
	    //batchLoadData.loadAuthors(0);
	    //batchLoadData.loadBooks();
	}
}
