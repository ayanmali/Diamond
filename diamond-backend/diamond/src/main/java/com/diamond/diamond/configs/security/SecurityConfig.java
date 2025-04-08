package com.diamond.diamond.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
 * TODO: add Single sign on
 * TODO: add 2FA
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http
        .cors(Customizer.withDefaults()) // enables CORS using the bean defined in CorsConfig
        .csrf(csrf -> csrf.disable()) // CSRF protection
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/", "/error", "/webjars/**", "/ws/**", "/sockjs/**").permitAll() // these URLs don't require authentication
        .anyRequest().authenticated()) // anything else requires authentication

        .oauth2Login(oauth2login -> oauth2login
        .successHandler((request, response, authentication) -> response.sendRedirect("/auth/details")));


        // Logout user
        // .logout(logout -> logout
        //     .logoutSuccessUrl("/logout-success") // Redirect to a success page after logout
        //     .clearAuthentication(true)
        //     .deleteCookies("JSESSIONID")
        //     .logoutUrl("/logout") // Specify the logout URL
            // .addLogoutHandler((request, response, authentication) -> {
            //     // Custom logic to handle OAuth logout
            //     // Redirect to the OAuth provider's logout URL
            //     String oauthLogoutUrl = "https://oauth-provider.com/logout"; // Replace with actual logout URL
            //     try {
            //         response.sendRedirect(oauthLogoutUrl);
            //     } catch (IOException e) {
            //         // TODO Auto-generated catch block
            //         System.out.println("Error logging out: " + e.getMessage());
            //     }
            // })
        //);
        
        // Exception handling -- redirect user to login page
        // .exceptionHandling(exception -> exception
        // .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

        // Handle unauthorized access
        // .exceptionHandling(exception -> exception
        // .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
        // );
        
        return http.build();
    }
}
