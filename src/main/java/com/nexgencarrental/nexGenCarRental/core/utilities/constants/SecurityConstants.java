package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

public final class SecurityConstants {

    private SecurityConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/webjars/**",
            "/v3/api-docs/**",
            "/v2/api-docs",
            "/api/v1/auth/**",
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/refresh-token",
    };

    public static final String[] USER_URLS = {
            "/api/v1/users/**",
            "/api/v1/users/getByEmail"
    };

    public static final String[] ADMIN_URLS = {
            "/api/v1/brands/**",
            "/api/v1/colors/**"
    };


    public static final String[] AUTHENTICATED_URLS = {
            "/api/v1/**"
    };

    public static final String BEARER_AUTH_SCHEME_NAME = "bearerAuth";
    public static final String BEARER_SCHEME = "bearer";
    public static final String BEARER_FORMAT = "JWT";
}