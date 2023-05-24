package com.rytesoft.rytewebspringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@RestController
public class RytewebspringappApplication {

    public static void main(String[] args) {
        SpringApplication.run(RytewebspringappApplication.class, args);
    }

}
