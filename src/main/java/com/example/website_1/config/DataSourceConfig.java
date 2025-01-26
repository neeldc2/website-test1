package com.example.website_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * This way, to add a tenant, a restart will be required.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceRouting dataSourceRouting = new DataSourceRouting();

        Map<Object, Object> dataSources = new HashMap<>();

        // Example configuration for multiple tenants
        dataSources.put("87ff56fc-9cc3-4fce-8b34-fbae7f89545b", createDataSource("jdbc:mysql://localhost:3306/website1", "root", "Asdf1234@"));
        dataSources.put("cad5b393-770a-4280-b239-92663c22787e", createDataSource("jdbc:mysql://localhost:3306/website1b", "root", "Asdf1234@"));

        dataSourceRouting.setTargetDataSources(dataSources);
        // To test this, do not send userId in the header
        dataSourceRouting.setDefaultTargetDataSource(dataSources.get("87ff56fc-9cc3-4fce-8b34-fbae7f89545b"));
        return dataSourceRouting;
    }

    private DataSource createDataSource(String url, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
