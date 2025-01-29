package com.diamond.diamond.services;

import java.util.Optional;
import java.util.UUID;

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

    public Vendor saveUser(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Optional<Vendor> findVendorById(UUID id) {
        return vendorRepository.findById(id);
    }

    public Optional<Vendor> findVendorByEmail(String email) {
        return vendorRepository.findByEmail(email);
    }

    public Vendor updateVendorEmail(UUID id, String email) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            Vendor vendor = optionalVendor.get();
            vendor.setEmail(email);
            return vendorRepository.save(vendor);
        }
        return null;
    }

    public Vendor updateVendorName(UUID id, String name) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            Vendor vendor = optionalVendor.get();
            vendor.setBusinessName(name);
            return vendorRepository.save(vendor);
        }
        return null;
    }

    public void deleteVendorById(UUID id) {
        vendorRepository.deleteById(id);
    }

    public void deleteVendor(Vendor vendor) {
        vendorRepository.delete(vendor);
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
