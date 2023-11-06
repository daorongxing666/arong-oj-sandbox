package com.arong.ojbackendsandbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class testController {

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        String res = "Hello" + name;
        System.out.println(res);
        return res;
    }
}
