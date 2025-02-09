package com.example.website_1.usercontext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class UserContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler)
            throws Exception {
        String userId = request.getHeader("x-user-id");
        String tenantId = request.getHeader("x-tenant-id");

        UserContext userContext;
        UserContext.UserContextBuilder builder = UserContext.builder();

        // remove if the code needs to fail if userId does not exist
        if (StringUtils.hasText(userId)) {
            builder.userId(UUID.fromString(userId));
        }

        if (StringUtils.hasText(tenantId)) {
            builder.tenantId(tenantId);
        }

        userContext = builder.build();
        UserContextHolder.setUserContext(userContext);

        // It tells Spring to further process the request (true) or not (false).
        return true;
    }

    @Override
    public void afterCompletion(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Object handler,
            final Exception ex)
            throws Exception {
        // Clear the ThreadLocal to prevent memory leaks
        UserContextHolder.clear();
    }
}
