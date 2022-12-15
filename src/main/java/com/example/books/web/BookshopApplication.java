package com.example.books.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BookshopApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BookshopApplication.class, args);
	}

}
