package com.example.website_1.config;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DynamicMultiTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl<String> {

    @Autowired
    private TenantDataSourceRegistry tenantDataSourceRegistry;

    @Override
    protected DataSource selectAnyDataSource() {
        throw new IllegalStateException("No default DataSource available. Tenant must be specified.");
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        return tenantDataSourceRegistry.getOrCreateTenantDataSource(tenantIdentifier);
    }
}
