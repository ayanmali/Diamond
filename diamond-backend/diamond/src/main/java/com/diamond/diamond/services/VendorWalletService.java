package com.diamond.diamond.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.repositories.VendorWalletRepository;

@Service
public class VendorWalletService {
    private final VendorWalletRepository vendorWalletRepository;

    public VendorWalletService(VendorWalletRepository vendorWalletRepository) {
        this.vendorWalletRepository = vendorWalletRepository;
    }

    public VendorWallet savePayment(VendorWallet wallet) {
        return vendorWalletRepository.save(wallet);
    }

    public Optional<VendorWallet> findWalletById(Long id) {
        return vendorWalletRepository.findById(id);
    }

    public Optional<VendorWallet> findWalletByAddress(String address) {
        return vendorWalletRepository.findByAddress(address);
    }

    public void deleteWalletById(Long id) {
        vendorWalletRepository.deleteById(id);
    }

    public void deleteWallet(VendorWallet wallet) {
        vendorWalletRepository.delete(wallet);
    }

    
}
