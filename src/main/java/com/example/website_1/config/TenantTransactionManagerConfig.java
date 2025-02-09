package com.example.website_1.config;

//@Configuration
//@EnableTransactionManagement
public class TenantTransactionManagerConfig {

    /*@Autowired
    private DataSourceManager dataSourceManager;

    //@Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(tenantAwareDataSource())
                .packages("com.example.demo.entity") // Replace with your entity package
                .persistenceUnit("tenantPU")
                .build();
    }


    //@Bean
    public DataSource tenantAwareDataSource() {
        return new TenantAwareRoutingDataSource(dataSourceManager);
    }

   // @Bean(name = "tenantBasedTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(tenantAwareDataSource());
    }*/
}
