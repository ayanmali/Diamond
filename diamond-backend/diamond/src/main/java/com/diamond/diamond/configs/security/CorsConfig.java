package com.diamond.diamond.configs.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Set allowed origins; you can use "*" for all or specify the origins explicitly, e.g., "http://localhost:3000"
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://diamondpay.dev"));
        // Allow specific methods; you can include GET, POST, PUT, DELETE, etc.
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Specify allowed headers
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        // Allow credentials (cookies, authorization headers, TLS client certificates, etc.)
        configuration.setAllowCredentials(true);
        // Optionally set the max age (in seconds) to cache the CORS configuration
        configuration.setMaxAge(3600L);

        // Map the configuration to all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
