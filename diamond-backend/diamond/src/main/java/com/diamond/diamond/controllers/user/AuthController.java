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
import com.diamond.diamond.services.AuthService;
import com.diamond.diamond.services.OAuthService;
import com.diamond.diamond.services.user.AccountService;

import org.springframework.web.bind.annotation.RequestBody;

import com.diamond.diamond.dtos.account.FetchAccountBalanceDto;
import com.diamond.diamond.dtos.account.PasswordLoginDto;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AccountService accountService;
    private final OAuthService oauthService;
    private final AuthService authService;

    public AuthController(OAuthService oauthService, AccountService accountService, AuthService authService) {
        this.accountService = accountService;
        this.oauthService = oauthService;
        this.authService = authService;
    }

    @GetMapping("/details")
    public Map<String, String> getUserDetails(@AuthenticationPrincipal OAuth2User principal,
    @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        return oauthService.getUserNameAndEmail(principal, client);
    }

    @PostMapping("/login-oauth")
    public FetchAccountDto loginUser(@AuthenticationPrincipal OAuth2User principal,
                                     @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        Map<String, String> userData = oauthService.getUserNameAndEmail(principal, client);

        if (accountService.existsByEmail(userData.get("email"))) {
            Account user = accountService.findAccountByEmail(userData.get("email"));
            // if they exist, log them in
            return new FetchAccountDto(user);
        }
        // if they don't exist, add them to DB
        return new FetchAccountDto(authService.signUp(userData.get("email"), userData.get("name")));
    }

    @PostMapping("/login-email")
    public FetchAccountDto loginUser(@RequestBody PasswordLoginDto loginDto) {
        return new FetchAccountDto(authService.authenticate(loginDto));
    }
    
    
}
