package com.example.website_1.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    /**
     * This will stop flyway from running during start up
     *
     * @return
     */
    @Bean
    public FlywayMigrationStrategy noopFlywayMigrationStrategy() {
        return flyway -> System.out.println("I'm not migrating right now.");
    }

    /*@Bean
    public FlywayMigrationInitializer flywayInitializer(Flyway flyway,
                                                        ObjectProvider<FlywayMigrationStrategy> migrationStrategy) {
        return new MyFlywayMigrationInitializer(flyway, migrationStrategy.getIfAvailable());
    }*/

    @Bean
    @ConfigurationProperties(prefix = "spring.flyway")
    public FlywayProperties flywayProperties() {
        return new FlywayProperties();
    }
}
