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
        String tenant = UserContextHolder.getUserContext().tenant();
        if (tenant == null) {
            return "INIT";
        }
        return tenant;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
