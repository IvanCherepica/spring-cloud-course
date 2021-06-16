package com.example.clientservice.controller;

import com.example.clientservice.model.Book;
import com.example.clientservice.service.Client2Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class ClientServiceController {

    Logger logger = java.util.logging.Logger.getLogger(ClientServiceController.class.getName());

    private final Client2Service client2Service;

    public ClientServiceController(Client2Service client2Service) {
        this.client2Service = client2Service;
    }

    @GetMapping("/getAllBooksByFeignClient")
    public List<Book> getAllBooks() {
        logger.info("Calling through Feign Client");
        return client2Service.getAllBooksList();
    }
}
