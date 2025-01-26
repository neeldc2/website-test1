package com.example.website_1.usercontext;

import lombok.Builder;
import lombok.NonNull;

import java.util.UUID;

@Builder
public record UserContext(
        @NonNull UUID userId
) {
}
