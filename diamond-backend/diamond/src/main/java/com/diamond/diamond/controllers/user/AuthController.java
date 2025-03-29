package com.diamond.diamond.controllers.user;
// /*
//  * Defining API endpoints for user authentication
//  */
// package com.diamond.diamond.controllers;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.diamond.diamond.dtos.LoginUserDto;
// import com.diamond.diamond.dtos.RegisterUserDto;
// import com.diamond.diamond.entities.Account;
// import com.diamond.diamond.responses.LoginResponse;
// import com.diamond.diamond.services.AuthService;
// import com.diamond.diamond.services.JwtService;


// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//     private final JwtService jwtService;
//     private final AuthService authService;

//     public AuthController(JwtService jwtService, AuthService authService) {
//         this.jwtService = jwtService;
//         this.authService = authService;
//     }

//     /*
//      * Endpoint for signing the user up
//      * can be accessed without authentication
//      */
//     @PostMapping("/signup")
//     public ResponseEntity<Account> signUpUser(@RequestBody RegisterUserDto registerUserDto) {
//         Account user = authService.signUp(registerUserDto);

//         // returning the newly registered user
//         return ResponseEntity.ok(user);
//     }

//     /*
//      * Endpoint for validating the user's credentials when they sign in
//      * can be accessed without authentication
//      */
//     @PostMapping("/login")
//     public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginUserDto loginUserDto) {
//         Account authenticatedUser = authService.authenticate(loginUserDto);

//         String accessToken = jwtService.generateAccessToken(authenticatedUser);
//         String refreshToken = jwtService.generateRefreshToken(authenticatedUser);

//         // Creating an object to use for the response after the user makes the login request
//         // this response will contain their tokens as well as the amount of time until they expire
//         LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken,
//                 jwtService.getAccessExpiration(),
//                 jwtService.getRefreshExpiration());

//         return ResponseEntity.ok(loginResponse);
//     }

//     @GetMapping("/id")
//     public String getAccountById(@RequestParam String param) {
//         return "Testing ID endpoint";
//     }
    

// }
