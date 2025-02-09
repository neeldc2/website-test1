package com.example.website_1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FlywayController {

    //@Autowired
    //private final Flyway flyway;

    @GetMapping("/flyway")
    public void runFlyway() {
        //flyway.migrate();
        log.info("Ran flyway");
    }

}
