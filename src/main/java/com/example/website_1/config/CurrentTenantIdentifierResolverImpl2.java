package com.example.website_1.config;

import com.example.website_1.usercontext.UserContextHolder;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl2 implements CurrentTenantIdentifierResolver<String> {

    @Override
    public String resolveCurrentTenantIdentifier() {
        if (UserContextHolder.getUserContext() == null) {
            return "INIT";
        }
        String tenantId = UserContextHolder.getUserContext().tenantId();
        if (tenantId == null) {
            return "INIT";
        }
        return tenantId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
