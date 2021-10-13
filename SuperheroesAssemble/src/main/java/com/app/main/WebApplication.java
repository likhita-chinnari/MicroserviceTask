package com.app.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
public class WebApplication
{
    public static void main(final String[] args) {
        System.out.println("Spring Boot WebApplication");
        SpringApplication.run((Class)WebApplication.class, args);
    }
}