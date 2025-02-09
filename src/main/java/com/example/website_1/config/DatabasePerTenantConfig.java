package com.example.website_1.config;

/*@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.example.website_1"},
        entityManagerFactoryRef = "tenantEntityManagerFactory",
        transactionManagerRef = "tenantTransactionManager")*/
public class DatabasePerTenantConfig {
/*
    @Autowired
    private DataSourceManager dataSourceManager;

    *//*@Bean("tenantBasedTransactionManager")
    public PlatformTransactionManager tenantBasedTransactionManager() {
        String tenantId = UserContextHolder.getUserContext().tenantId().toString();
        DataSource dataSource = dataSourceManager.getDataSource(tenantId);
        return new DataSourceTransactionManager(dataSource);
    }*//*

    @Bean(name = "tenantJpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "tenantTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("tenantEntityManagerFactory") EntityManagerFactory tenantEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(tenantEntityManager);
        return transactionManager;
    }

    *//**
     * The multi tenant connection provider
     *
     * @return
     *//*
    @Bean(name = "datasourceBasedMultitenantConnectionProvider")
    //@ConditionalOnBean(name = "masterEntityManagerFactory")
    public MultiTenantConnectionProvider multiTenantConnectionProvider() {
        // Autowires the multi connection provider
        return new DataSourceBasedMultiTenantConnectionProviderImpl();
    }

    *//**
     * The current tenant identifier resolver
     *
     * @return
     *//*
    @Bean(name = "currentTenantIdentifierResolver")
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
        return new CurrentTenantIdentifierResolverImpl();
    }

    *//**
     * Creates the entity manager factory bean which is required to access the
     * JPA functionalities provided by the JPA persistence provider, i.e.
     * Hibernate in this case.
     *
     * @param connectionProvider
     * @param tenantResolver
     * @return
     *//*
    @Bean(name = "tenantEntityManagerFactory")
    @ConditionalOnBean(name = "datasourceBasedMultitenantConnectionProvider")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("datasourceBasedMultitenantConnectionProvider")
            MultiTenantConnectionProvider connectionProvider,
            @Qualifier("currentTenantIdentifierResolver")
            CurrentTenantIdentifierResolver tenantResolver) {
        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        //All tenant related entities, repositories and service classes must be scanned
        emfBean.setPackagesToScan("com.example.website_1");
        emfBean.setJpaVendorAdapter(jpaVendorAdapter());
        emfBean.setPersistenceUnitName("tenantdb-persistence-unit");
        Map<String, Object> properties = new HashMap<>();
        //properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, connectionProvider);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantResolver);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);
        properties.put(Environment.HBM2DDL_AUTO, "none");
        emfBean.setJpaPropertyMap(properties);
        return emfBean;
    }*/
}
