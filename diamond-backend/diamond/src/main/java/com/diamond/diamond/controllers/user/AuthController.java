package com.diamond.diamond.controllers.user;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.services.OAuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final OAuthService oauthService;

    public AuthController(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/details")
    public Map<String, String> getUserDetails(@AuthenticationPrincipal OAuth2User principal,
    @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        return oauthService.getUserNameAndEmail(principal, client);
    }
}
