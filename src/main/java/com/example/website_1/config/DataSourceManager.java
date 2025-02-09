package com.example.website_1.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataSourceManager {
    // Make this into a caffeine cache which will expire after 30 mins.
    // Have on removal listener, which will close the data sources connection when removed from cache.
    private final Map<String, DataSource> dataSources = new ConcurrentHashMap<>();

    public DataSource getDataSource(String tenantId) {
        return dataSources.computeIfAbsent(tenantId, this::createDataSource);
    }

    private DataSource createDataSource(String tenantId) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/" + tenantId);
        dataSource.setUsername("root");
        dataSource.setPassword("Asdf1234@");
        return dataSource;
    }
}
