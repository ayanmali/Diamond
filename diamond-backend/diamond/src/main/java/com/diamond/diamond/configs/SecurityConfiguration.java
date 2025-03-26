package com.diamond.diamond.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/", "/error", "/webjars/**")
        .permitAll().anyRequest().authenticated())

        // Login
        .oauth2Login(oauth2 -> oauth2
        .loginPage("/oauth2/authorization/google")
        .defaultSuccessUrl("/dashboard"))

        // Logout user
        .logout(logout -> logout
        .logoutSuccessUrl("/")
        .clearAuthentication(true)
        .deleteCookies("JSESSIONID"))
        
        // Exception handling -- redirect user to login page
        .exceptionHandling(exception -> exception
            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
        );

        return http.build();
    }
}
