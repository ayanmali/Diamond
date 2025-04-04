package com.diamond.diamond.controllers.user;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.account.FetchAccountDto;
import com.diamond.diamond.entities.user.Account;
import com.diamond.diamond.services.OAuthService;
import com.diamond.diamond.services.user.AccountService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AccountService accountService;
    private final OAuthService oauthService;

    public AuthController(OAuthService oauthService, AccountService accountService) {
        this.accountService = accountService;
        this.oauthService = oauthService;
    }

    @GetMapping("/details")
    public Map<String, String> getUserDetails(@AuthenticationPrincipal OAuth2User principal,
    @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        return oauthService.getUserNameAndEmail(principal, client);
    }

    @PostMapping("/login")
    public FetchAccountDto loginUser(@AuthenticationPrincipal OAuth2User principal,
                                     @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        Map<String, String> userData = oauthService.getUserNameAndEmail(principal, client);

        if (accountService.existsByEmail(userData.get("email"))) {
            Account user = accountService.findAccountByEmail(userData.get("email"));
            // if they exist, log them in
            return new FetchAccountDto(user);
        }
        // if they don't exist, add them to DB
        return accountService.signUp(userData.get("email"), userData.get("name"));
        
    }
    
}
