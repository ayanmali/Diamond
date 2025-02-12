package com.diamond.diamond.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.wallets.FetchCustomerWalletDto;
import com.diamond.diamond.dtos.wallets.NewCustomerWalletDto;
import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.CustomerWallet;
import com.diamond.diamond.repositories.CustomerWalletRepository;

@Service
public class CustomerWalletService {
    private final CustomerWalletRepository customerWalletRepository;

    public CustomerWalletService(CustomerWalletRepository customerWalletRepository) {
        this.customerWalletRepository = customerWalletRepository;
    }

    public FetchCustomerWalletDto convertCustomerWalletToFetchDto(CustomerWallet wallet) {
        FetchCustomerWalletDto walletDto = new FetchCustomerWalletDto();
        walletDto.setId(wallet.getId());
        walletDto.setAddress(wallet.getAddress());
        walletDto.setChain(wallet.getChain());
        walletDto.setCustomerId(wallet.getCustomer().getId());
        return walletDto;
    }

    public CustomerWallet saveWallet(Customer customer, NewCustomerWalletDto walletDto) {
        CustomerWallet wallet = new CustomerWallet(walletDto.getAddress(),
                                                   walletDto.getChain(),
                                                   customer);
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
