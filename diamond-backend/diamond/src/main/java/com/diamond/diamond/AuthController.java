package com.diamond.diamond;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /*
     * Endpoint for signing the user up
     * can be accessed without authentication
     */
    @PostMapping("/signup")
    String signUpUser() {
        return "";
    }

    /*
     * Endpoint for validating the user's credentials when they sign in
     * can be accessed without authentication
     */
    @PostMapping("/login")
    String authenticateUser() {
        return "";
    }

}
