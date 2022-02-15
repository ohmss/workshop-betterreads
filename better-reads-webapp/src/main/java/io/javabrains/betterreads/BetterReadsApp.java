package io.javabrains.betterreads;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main application class with main method that runs the Spring Boot app
 */
@SpringBootApplication
@RestController
public class BetterReadsApp {
    
	public static void main(String[] args) {
		SpringApplication.run(BetterReadsApp.class, args);
	}
	
	@RequestMapping(value = "/user")
	public Principal user(Principal principal) {
	    return principal;
	}
}
