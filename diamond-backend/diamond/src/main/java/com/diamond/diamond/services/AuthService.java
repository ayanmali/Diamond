package com.diamond.diamond.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.LoginUserDto;
import com.diamond.diamond.dtos.RegisterUserDto;
import com.diamond.diamond.entities.User;
import com.diamond.diamond.repositories.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    public User signUp(RegisterUserDto input) {
        User user = new User();
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setFullName(input.getFullName());

        // saving the newly registered user to the Users repository
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );

        // returns the User object if it successfully authenticates
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
