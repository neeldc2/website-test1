package com.example.website_1.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.example.website_1"}
        ,
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "tenantBasedTransactionManager"
)
public class DynamicMultiTenantConnectionProviderConfig {

    @Autowired
    TenantDataSourceRegistry tenantDataSourceRegistry;

    @Bean("tenantBasedTransactionManager")
    public PlatformTransactionManager tenantBasedTransactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private DataSource createDefaultDataSource() {
        // Default dummy data source, not used but required by Spring
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/default1");
        dataSource.setUsername("root");
        dataSource.setPassword("Asdf1234@");
        return dataSource;
    }

    //@Lazy
    @Bean(name = "entityManagerFactory")
    @ConditionalOnBean(name = "dynamicMultiTenantConnectionProvider")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("dynamicMultiTenantConnectionProvider")
            MultiTenantConnectionProvider connectionProvider,
            @Qualifier("currentTenantIdentifierResolverImpl2")
            CurrentTenantIdentifierResolver tenantResolver) {

        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        emfBean.setPackagesToScan("com.example.website_1"); // Scan entity packages
        emfBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emfBean.setPersistenceUnitName("tenantdb-persistence-unit");

        Map<String, Object> properties = new HashMap<>();
        //properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE); // Enable multi-tenancy
        properties.put("hibernate.multiTenancy", "DATABASE");
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, connectionProvider);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantResolver);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);
        properties.put(Environment.HBM2DDL_AUTO, "none");

        emfBean.setJpaPropertyMap(properties);
        return emfBean;
    }

}
