package com.example.website_1.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class TenantDataSourceRegistry {

    private final Map<String, DataSource> dataSources = new ConcurrentHashMap<>();
    private final FlywayProperties flywayProperties;

    public DataSource getOrCreateTenantDataSource(String tenantId) {
        return dataSources.computeIfAbsent(tenantId, this::createDataSourceForTenant);
    }

    public DataSource createDataSourceForTenant(String tenantId) {
       /* HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/" + tenantId);
        dataSource.setUsername("root");
        dataSource.setPassword("Asdf1234@");
        return dataSource;*/

        // Create a new DataSource dynamically
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/" + tenantId);
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("Asdf1234@");
        //hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        DataSource dataSource = new HikariDataSource(hikariConfig);

        runFlywayMigrations(dataSource);

        return dataSource;
    }

    private void runFlywayMigrations(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(flywayProperties.getLocations().toArray(new String[0])) // Migration scripts location
                .baselineOnMigrate(flywayProperties.isBaselineOnMigrate()) // Allows running on non-empty schemas
                .load();

        flyway.migrate();
    }

}
