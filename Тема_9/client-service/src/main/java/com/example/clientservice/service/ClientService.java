package com.example.clientservice.service;

import com.example.clientservice.connector.BookServiceConnector;
import com.example.clientservice.model.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ClientService {

    private final Logger LOG = java.util.logging.Logger.getLogger(ClientService.class.getName());

    @Value("${book-service.url}")
    private String bookServiceUrl;

    private RestTemplate restTemplate;
    private BookServiceConnector bookServiceConnector;

    public ClientService(RestTemplate restTemplate, BookServiceConnector bookServiceConnector) {
        this.restTemplate = restTemplate;
        this.bookServiceConnector = bookServiceConnector;
    }

    @HystrixCommand(fallbackMethod = "failed")
    public List<Book> getAllBooksByFeign() {
        return bookServiceConnector.getAllBooksList();
    }

    public String getAllBooksByRestTemplate() {
        return restTemplate.getForObject(bookServiceUrl+"/data", String.class);
    }

    public List<Book> failed() {
        String error = "Service is not available now. Please try again later";
        LOG.log(Level.INFO, error);
        return new ArrayList<>();
    }

}
