package com.diamond.diamond.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.LoginUserDto;
import com.diamond.diamond.dtos.RegisterUserDto;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.repositories.VendorRepository;

@Service
public class AuthService {

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public AuthService(VendorRepository vendorRepository, PasswordEncoder passwordEncoder, AuthenticationManager authManager) {
        this.vendorRepository = vendorRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    public Vendor signUp(RegisterUserDto input) {
        Vendor user = new Vendor();
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setBusinessName(input.getFullName());

        // saving the newly registered user to the Users repository
        return vendorRepository.save(user);
    }

    public Vendor authenticate(LoginUserDto input) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );

        // returns the User object if it successfully authenticates
        return vendorRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
