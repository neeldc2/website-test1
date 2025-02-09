package com.example.website_1.config;

import com.example.website_1.usercontext.UserContextHolder;
import org.springframework.util.StringUtils;

//public class DataSourceRouting extends AbstractRoutingDataSource {
public class DataSourceRouting {

    //@Override
    protected Object determineCurrentLookupKey() {
        // To test this, do not send userId in the header
        if (UserContextHolder.getUserContext() == null
                || !StringUtils.hasText(UserContextHolder.getUserContext().userId().toString())) {
            return null;
        }
        return UserContextHolder.getUserContext().userId().toString();
    }
}
