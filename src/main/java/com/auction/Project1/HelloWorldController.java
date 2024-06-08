package com.auction.Project1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloWorldController {
    @GetMapping(path = "/vip")
    public String helloWorld() {

        return "Hello World";
    }
}
