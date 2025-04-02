package com.diamond.diamond.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
 * TODO: add Single sign on
 * TODO: add 2FA
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http.authorizeHttpRequests(authorize -> authorize
        // .requestMatchers("/", "/error", "/webjars/**")
        // .permitAll().anyRequest().authenticated())
        
        http.csrf(csrf -> csrf.disable()) // CSRF protection
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/", "/error", "/webjars/**").permitAll() // these URLs don't require authentication
        .anyRequest().authenticated()) // anything else requires authentication

        // Login
        //.oauth2Login(Customizer.withDefaults());
        // .oauth2Login(oauth2 -> oauth2
        // .loginPage("/login")
        // .defaultSuccessUrl("/dashboard"))
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
