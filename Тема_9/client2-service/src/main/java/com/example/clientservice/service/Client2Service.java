package com.example.clientservice.service;

import com.example.clientservice.connector.BookServiceConnector;
import com.example.clientservice.connector.BookServiceUpdateConnector;
import com.example.clientservice.model.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class Client2Service {

    private static final Logger LOG = Logger.getLogger(Client2Service.class.getName());

    private BookServiceConnector bookServiceConnector;
    private BookServiceUpdateConnector bookServiceUpdateConnector;

    public Client2Service(BookServiceConnector bookServiceConnector, BookServiceUpdateConnector bookServiceUpdateConnector) {
        this.bookServiceConnector = bookServiceConnector;
        this.bookServiceUpdateConnector = bookServiceUpdateConnector;
    }

    @HystrixCommand(fallbackMethod = "getAllBookListFromFallback")
    public List<Book> getAllBooksList() {
        return bookServiceConnector.getAllBooksList();
    }

    public List<Book> getAllBookListFromFallback() {
        return bookServiceUpdateConnector.getAllBooksList();
    }
}
