package com.example.clientservice.controller;

import com.example.clientservice.model.Book;
import com.example.clientservice.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBooksByFeignClient")
    public List<Book> getAllBooks() {
        return bookService.getAllBooksByFeign();
    }

    @GetMapping("/getAllBooksByRestTemplate")
    public String data() {
        return bookService.getAllBooksByRestTemplate();
    }
}
