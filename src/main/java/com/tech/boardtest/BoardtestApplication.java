package com.tech.boardtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tech.*")
public class BoardtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardtestApplication.class, args);
    }

}
