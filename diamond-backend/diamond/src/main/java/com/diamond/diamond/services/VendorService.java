package com.diamond.diamond.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.vendor.FetchVendorDto;
import com.diamond.diamond.dtos.vendor.RegisterUserDto;
import com.diamond.diamond.dtos.wallets.FetchVendorWalletDto;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.repositories.VendorRepository;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    // private final PasswordEncoder passwordEncoder;
    // private final AuthenticationManager authManager;

    public VendorService(VendorRepository vendorRepository/*, PasswordEncoder passwordEncoder, AuthenticationManager authManager*/) {
        this.vendorRepository = vendorRepository;
        // this.passwordEncoder = passwordEncoder;
        // this.authManager = authManager;
    }

    // public Vendor saveUser(Vendor vendor) {
    //     return vendorRepository.save(vendor);
    // }
    public FetchVendorDto convertVendorToFetchDto(Vendor vendor) {
        FetchVendorDto vendorDto = new FetchVendorDto();
        
        vendorDto.setEmail(vendor.getEmail());
        vendorDto.setBusinessName(vendor.getBusinessName());
        vendorDto.setId(vendor.getId());
        vendorDto.setCreatedAt(vendor.getCreatedAt());
        vendorDto.setUpdatedAt(vendor.getUpdatedAt());
        return vendorDto;
    }

    public FetchVendorDto signUp(RegisterUserDto input) {
        Vendor user = new Vendor();
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword());
        user.setBusinessName(input.getBusinessName());

        // saving the newly registered user to the Users repository
        return convertVendorToFetchDto(vendorRepository.save(user));
    }

    // public List<VendorWallet> getVendorWallets(UUID id) {
        
    // }

    public FetchVendorDto findVendorDtoById(String id) {
        UUID uuidId = UUID.fromString(id);
        return convertVendorToFetchDto(vendorRepository.findById(uuidId).orElseThrow());
    }

    public FetchVendorDto findVendorDtoById(UUID id) {
        return convertVendorToFetchDto(vendorRepository.findById(id).orElseThrow());
    }

    public Vendor findVendorById(String id) {
        UUID uuidId = UUID.fromString(id);
        return vendorRepository.findById(uuidId).orElseThrow();
    }

    public Vendor findVendorById(UUID id) {
        return vendorRepository.findById(id).orElseThrow();
    }

    public FetchVendorDto findVendorDtoByEmail(String email) {
        return convertVendorToFetchDto(vendorRepository.findByEmail(email).orElseThrow());

        // List<Vendor> vendors = vendorRepository.findByEmail(email);
        // return vendors.stream().map(vendor -> {
        //     FetchVendorDto vendorDto = new FetchVendorDto();
        //     vendorDto.setEmail(vendor.getEmail());
        //     vendorDto.setBusinessName(vendor.getBusinessName());
        //     vendorDto.setId(vendor.getId());
        //     vendorDto.setCreatedAt(vendor.getCreatedAt());
        //     vendorDto.setUpdatedAt(vendor.getUpdatedAt());
        //     return vendorDto;
        // }).collect(Collectors.toList());
    }

    public Vendor findVendorByEmail(String email) {
        return vendorRepository.findByEmail(email).orElseThrow();
    }

    // public List<VendorWallet> findWallets(UUID walletId) {
    //     return vendorRepository.findWallets(walletId);
    // }
    public List<FetchVendorWalletDto> findVendorWallets(UUID vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow();
        List<FetchVendorWalletDto> wallets = new ArrayList<>();
        for (VendorWallet wallet : vendor.getWallets()) {
            wallets.add(VendorWalletService.convertVendorWalletToFetchDto(wallet));
        }
        return wallets;
    }

    public FetchVendorDto updateVendorEmail(UUID id, String email) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow();
        vendor.setEmail(email);
        return convertVendorToFetchDto(vendorRepository.save(vendor));
    }

    public FetchVendorDto updateVendorName(UUID id, String name) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow();
        vendor.setBusinessName(name);
        return convertVendorToFetchDto(vendorRepository.save(vendor));
    }

    public void deleteVendorById(UUID id) {
        vendorRepository.deleteById(id);
    }

    public void deleteVendorById(String id) {
        UUID uuidId = UUID.fromString(id);
        deleteVendorById(uuidId);
    }

    public void deleteVendor(Vendor vendor) {
        vendorRepository.delete(vendor);
    }

}