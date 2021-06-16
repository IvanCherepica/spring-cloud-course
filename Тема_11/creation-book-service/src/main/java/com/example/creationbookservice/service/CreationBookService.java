package com.example.creationbookservice.service;

import com.example.creationbookservice.model.Book;
import com.example.creationbookservice.util.BookGenerator;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableBinding(Source.class)
@EnableScheduling
@Component
@AllArgsConstructor
public class CreationBookService {

    private final Source source;
    private final BookGenerator bookGenerator;

    @Scheduled(fixedRate = 10000)
    private void sendMessage() {
        Book book = bookGenerator.createNewBook();

        source
                .output()
                .send(MessageBuilder.withPayload(book).build());
    }
}
