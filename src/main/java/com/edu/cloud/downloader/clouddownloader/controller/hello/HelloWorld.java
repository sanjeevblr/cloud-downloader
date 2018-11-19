package com.edu.cloud.downloader.clouddownloader.controller.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hello")
public class HelloWorld {

    @GetMapping
    public String hi(){
        return "Hello World";
    }
}
