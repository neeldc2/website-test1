package com.example.website_1.config;

/**
 * Using this approach, a restart won't be needed if a new tenant is added, unlike DataSourceRouting/DataSourceConfig class implementation.
 * Con with this approach is that it needs a default database to connect to, which is not a tenant database.
 * This class needs DataSourceManager class only.
 * To enable this, uncomment the code in this class and in DataSourceManager.
 */
//@Component
//public class TenantAwareRoutingDataSource extends AbstractRoutingDataSource {
public class TenantAwareRoutingDataSource {

    /*
    private final DataSourceManager dataSourceManager;

    public TenantAwareRoutingDataSource(DataSourceManager dataSourceManager) {
        this.dataSourceManager = dataSourceManager;

        // Set a dummy map for targetDataSources and a default data source
        Map<Object, Object> dummyDataSources = new HashMap<>();
        dummyDataSources.put("default", createDefaultDataSource());

        this.setTargetDataSources(dummyDataSources);
        this.setDefaultTargetDataSource(createDefaultDataSource());
        this.afterPropertiesSet(); // Important to trigger initialization
    }

    @Override
    protected Object determineCurrentLookupKey() {
        if (UserContextHolder.getUserContext() == null
                || !StringUtils.hasText(UserContextHolder.getUserContext().tenantId().toString())) {
            return "default1";
        }
        return UserContextHolder.getUserContext().tenantId();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        String tenantId = (String) determineCurrentLookupKey();
        if (tenantId == null) {
            throw new IllegalStateException("Tenant ID not set in TenantContext");
        }
        return dataSourceManager.getDataSource(tenantId);
    }

    private DataSource createDefaultDataSource() {
        // Default dummy data source, not used but required by Spring
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/default1");
        dataSource.setUsername("root");
        dataSource.setPassword("Asdf1234@");
        return dataSource;
    }*/
}
