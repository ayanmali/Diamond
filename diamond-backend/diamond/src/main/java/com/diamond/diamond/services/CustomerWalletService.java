package com.diamond.diamond.services;

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

    public FetchCustomerWalletDto saveWallet(Customer customer, NewCustomerWalletDto walletDto) {
        CustomerWallet wallet = new CustomerWallet(walletDto.getAddress(),
                                                   walletDto.getChain(),
                                                   customer);
        return convertCustomerWalletToFetchDto(customerWalletRepository.save(wallet));
    }

    public FetchCustomerWalletDto findWalletById(Long id) {
        return convertCustomerWalletToFetchDto(customerWalletRepository.findById(id).orElseThrow());
    }

    public FetchCustomerWalletDto findWalletByAddress(String address) {
        return convertCustomerWalletToFetchDto(customerWalletRepository.findByAddress(address).orElseThrow());
    }

    public void deleteWalletById(Long id) {
        customerWalletRepository.deleteById(id);
    }

    public void deleteWallet(CustomerWallet wallet) {
        customerWalletRepository.delete(wallet);
    }
}
