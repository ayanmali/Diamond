package com.diamond.diamond.services;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // TODO Auto-generated method stub
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User user = delegate.loadUser(userRequest);
        // String email = user.getAttribute("email");
        return user;
    }
    
    public Map<String, String> getUserNameAndEmail(OAuth2User principal, OAuth2AuthorizedClient client) {
        
        String provider = client.getClientRegistration().getRegistrationId();
        String email;
        
        // Apply different logic based on the provider
        switch(provider) {
            case "github" -> {
                // For GitHub, make an API call to get the email
                String accessToken = client.getAccessToken().getTokenValue();
                email = fetchGitHubEmail(accessToken);
            }
                
            case "google" -> // For Google, the email is typically available in the principal
                email = principal.getAttribute("email");
                
            default -> email = "Unknown provider: " + provider;
        }
        
        return Map.of("user", principal.getAttribute("name"), 
                      "email", email, 
                      "provider", provider);
    }

    // Helper method to send a request to GitHub API to get user email
    private String fetchGitHubEmail(String accessToken) {
        try {
            WebClient webClient = WebClient.builder()
            .baseUrl("https://api.github.com")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
            .build();
        
        List<Map<String, Object>> emails = webClient.get()
            .uri("/user/emails")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
            .block();

        if (emails == null) {
            throw new Exception("Emails list from GitHub API is null");
        }
        
        // Find the primary email
        for (Map<String, Object> email : emails) {
            if ((Boolean) email.get("primary")) {
                return (String) email.get("email");
            }
        }
        
        // If no primary email, return the first one
        if (!emails.isEmpty()) {
            return (String) emails.get(0).get("email");
        }
        
        return null;

        } 
        catch (Exception e) {
            System.out.println("Error fetching GitHub email: " + e.getMessage());
            return null;
        }

    }
}
