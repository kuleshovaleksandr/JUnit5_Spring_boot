package com.example.junit5_spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello world";
    }
}
