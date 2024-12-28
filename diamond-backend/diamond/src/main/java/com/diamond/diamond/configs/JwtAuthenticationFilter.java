/*
 * 
 */
package com.diamond.diamond.configs;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.diamond.diamond.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Checks a given request for the JWT present in the "Authorization" header
 * Reject the request if the token is invalid
 * If the token is valid, extract the username, find the corresponding user in the database, and set it in the authentication context
 * 
 * todo: add caching w/ Redis to speed up user checks in the DB
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            HandlerExceptionResolver handlerExceptionResolver,
            JwtService jwtService,
            UserDetailsService userDetailsService) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        // Checking to ensure the authentication header is not null and that it starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Authorization header is null");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Obtaining everything that comes after "Bearer " in the header
            final String jwt = authHeader.substring(7);
            // the currently authenticated principal
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // The user's username
            final String username = jwtService.extractUsername(jwt);
            // Obtaining the user details for the user corresponding to the given JWT
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Determining if the given user's JWT is valid
            final boolean isValid = jwtService.isTokenValid(jwt, userDetails);

            // If the token is valid
            if (isValid && authentication != null && username != null) {
                // generating the auth token for the user
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                // setting the authentication context to contain the user's auth token
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }

    }
}
