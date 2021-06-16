package com.example.clientservice.controller;

import com.example.clientservice.model.Book;
import com.example.clientservice.service.Client2Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookRestController {

    private final Client2Service client2Service;

    public BookRestController(Client2Service client2Service) {
        this.client2Service = client2Service;
    }

    @GetMapping("/getAllBooksByFeignClient")
    public List<Book> getAllBooks() {
        return client2Service.getAllBooksList();
    }
}
