package com.example.clientservice.connector;

import com.example.clientservice.config.FeignConfig;
import com.example.clientservice.connector.fallback.BookServiceFallback;
import com.example.clientservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "book-service-update",
        url = "${book-service-update.url}",
        configuration = FeignConfig.class,
        fallback = BookServiceFallback.class
)
public interface BookServiceUpdateConnector {

    @GetMapping("/show")
    List<Book> getAllBooksList();
}
