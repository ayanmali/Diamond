package com.diamond.diamond.services;

import java.util.Optional;

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

    public Optional<CustomerWallet> findWalletById(Long id) {
        return customerWalletRepository.findById(id);
    }

    public Optional<CustomerWallet> findWalletByAddress(String address) {
        return customerWalletRepository.findByAddress(address);
    }

    public void deleteWalletById(Long id) {
        customerWalletRepository.deleteById(id);
    }

    public void deleteWallet(CustomerWallet wallet) {
        customerWalletRepository.delete(wallet);
    }
}
