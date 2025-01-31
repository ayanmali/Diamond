// /*
//  * Routes certain incoming requests to the authentication middleware (as defined in JwtAuthenticationFilter.java)
//  * /signup and /login endpoints do not require authentication to be accessed. Any other URL must require authentication
//  * Each request is stateless i.e. every request is unique and treated as a new one
//  * We must use the custom authentication provider and it must be executed before the authentication middleware
//  * CORS configuration must only allow GET and POST requests
//  */
// package com.diamond.diamond.configs;

// import java.util.List;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final AuthenticationProvider authProvider;
//     private final JwtAuthenticationFilter jwtAuthFilter;

//     public SecurityConfig(AuthenticationProvider authProvider, JwtAuthenticationFilter jwtAuthFilter) {
//         this.authProvider = authProvider;
//         this.jwtAuthFilter = jwtAuthFilter;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.csrf(csrf -> csrf.disable())
//                 .authorizeHttpRequests((authz) -> authz
//                 .requestMatchers("/api/auth/**").permitAll()
//                 //.requestMatchers("/api/user/**").hasRole("USER")
//                 .anyRequest().authenticated())
//                 .sessionManagement((sessions) -> sessions
//                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 .authenticationProvider(authProvider)
//                 .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//         return http.build();
//     }

//     @Bean
//     CorsConfigurationSource corsConfigurationSource() {
//         final CorsConfiguration corsConfig = new CorsConfiguration();

//         corsConfig.setAllowedOrigins(List.of("http://localhost:8080"));
//         corsConfig.setAllowedMethods(List.of("GET", "POST"));
//         corsConfig.setAllowedHeaders(List.of("Authorization", "Content-Type"));

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

//         source.registerCorsConfiguration("/**", corsConfig);

//         return source;
//     }
// }
