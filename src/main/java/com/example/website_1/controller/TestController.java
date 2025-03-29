package com.example.website_1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    public static Integer a = 1;

    @GetMapping("/test")
    public String getString() {
        a++;
        log.debug("/test value is {}", a);

        return "working" + a;
    }
}
