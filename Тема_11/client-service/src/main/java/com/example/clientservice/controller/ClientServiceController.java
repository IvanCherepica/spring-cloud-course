package com.example.clientservice.controller;

import com.example.clientservice.model.Book;
import com.example.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class ClientServiceController {

    @Value("${book-service.url}")
    private String bookServiceUrl;

    private Environment env;
    private ClientService service;
    private RestTemplate restTemplate;

    public ClientServiceController(
//            Logger logger,
            Environment env,
            ClientService service,
            RestTemplate restTemplate) {
//        this.logger = logger;
        this.env = env;
        this.service = service;
        this.restTemplate = restTemplate;
    }


    @GetMapping("/getAllBooksByFeignClient")
    public List<Book> getAllBooks() {
        return service.getAllBooksByFeign();
    }

    @GetMapping("/getAllBooksByRestTemplate")
    public String data() {
        return service.getAllBooksByRestTemplate();
    }

    @RequestMapping("/getInfoByRemote")
    public String getInfoFromBookService() {
        String result = this.restTemplate.getForObject("http://book-service/", String.class);
        return result;
    }

}
