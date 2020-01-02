package com.example.PostawIWygraj;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import net.minidev.json.parser.ParseException;

@SpringBootApplication
@EnableScheduling
public class PostawIWygrajApplication {

    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
	SpringApplication.run(PostawIWygrajApplication.class, args);
	
	

    }

}
