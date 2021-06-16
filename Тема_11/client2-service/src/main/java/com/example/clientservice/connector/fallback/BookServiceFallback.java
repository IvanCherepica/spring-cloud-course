package com.example.clientservice.connector.fallback;

import com.example.clientservice.connector.BookServiceConnector;
import com.example.clientservice.model.Book;
import com.example.clientservice.service.Client2Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class BookServiceFallback implements BookServiceConnector {

    Logger logger = Logger.getLogger(BookServiceFallback.class.getName());

    private Client2Service client2Service;

    public BookServiceFallback(Client2Service client2Service) {
        this.client2Service = client2Service;
    }

    @Override
    public List<Book> getAllBooksList() {
        logger.info("ERROR: Service is not available now");
        logger.info("Calling book-service-update by Feign Client");
        return client2Service.getAllBookListFromFallback();
    }
}
