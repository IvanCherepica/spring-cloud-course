package com.example.creationbookservice.util;

import com.example.creationbookservice.model.Book;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class BookGenerator {

    private final Random rnd = new Random();

    public Book createNewBook() {
        int randomId = rnd.nextInt();
        return Book.builder()
                .id(randomId)
                .name("Book name: " + randomId)
                .description("Book description: " + randomId)
                .status("unchecked")
                .price(randomId++)
                .build();
    }
}
