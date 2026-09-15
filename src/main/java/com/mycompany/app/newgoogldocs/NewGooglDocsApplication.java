package com.mycompany.app.newgoogldocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NewGooglDocsApplication {



    public static void main(String[] args) {
        SpringApplication.run(NewGooglDocsApplication.class, args);
    }

}
