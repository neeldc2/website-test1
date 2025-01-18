package com.example.website_1.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebsiteConfig {

    @Bean
    public FlywayMigrationStrategy noopFlywayMigrationStrategy() {
        return flyway -> System.out.println("I'm not migrating right now.");
    }
}
