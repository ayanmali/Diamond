package com.diamond.diamond.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.CustomerWallet;
import com.diamond.diamond.repositories.CustomerWalletRepository;

@Service
public class CustomerWalletService {
    private final CustomerWalletRepository customerWalletRepository;

    public CustomerWalletService(CustomerWalletRepository customerWalletRepository) {
        this.customerWalletRepository = customerWalletRepository;
    }

    public CustomerWallet savePayment(CustomerWallet wallet) {
        return customerWalletRepository.save(wallet);
    }

    public Optional<CustomerWallet> findWalletById(UUID id) {
        return customerWalletRepository.findById(id);
    }

    public Optional<CustomerWallet> findWalletByAddress(String address) {
        return customerWalletRepository.findByAddress(address);
    }

    public void deleteWalletById(UUID id) {
        customerWalletRepository.deleteById(id);
    }

    public void deleteWallet(CustomerWallet wallet) {
        customerWalletRepository.delete(wallet);
    }
}
