package com.diamond.diamond.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.wallets.FetchCustomerWalletDto;
import com.diamond.diamond.dtos.wallets.NewCustomerWalletDto;
import com.diamond.diamond.entities.user.Customer;
import com.diamond.diamond.entities.user.CustomerWallet;
import com.diamond.diamond.repositories.CustomerWalletRepository;

@Service
public class CustomerWalletService {
    private final CustomerWalletRepository customerWalletRepository;

    public CustomerWalletService(CustomerWalletRepository customerWalletRepository) {
        this.customerWalletRepository = customerWalletRepository;
    }

    public static FetchCustomerWalletDto convertCustomerWalletToFetchDto(CustomerWallet wallet) {
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

    public FetchCustomerWalletDto findWalletDtoById(Long id) {
        return convertCustomerWalletToFetchDto(customerWalletRepository.findById(id).orElseThrow());
    }

    public CustomerWallet findWalletById(Long id) {
        return customerWalletRepository.findById(id).orElseThrow();
    }

    public FetchCustomerWalletDto findWalletDtoByAddress(String address) {
        return convertCustomerWalletToFetchDto(customerWalletRepository.findByAddress(address).orElseThrow());
    }

    public CustomerWallet findWalletByAddress(String address) {
        return customerWalletRepository.findByAddress(address).orElseThrow();
    }

    public List<CustomerWallet> findWalletsByCustomer(Customer customer) {
        return customerWalletRepository.findByCustomer(customer);
    }

    public List<FetchCustomerWalletDto> findWalletDtosByCustomer(Customer customer) {
        return customerWalletRepository.findByCustomer(customer).stream() // Convert the List<Customer> to a Stream<Customer>
        .map(CustomerWalletService::convertCustomerWalletToFetchDto) // Map each Customer to FetchCustomerDto
        .collect(Collectors.toList());
    }

    public void deleteWalletById(Long id) {
        customerWalletRepository.deleteById(id);
    }

    public void deleteWallet(CustomerWallet wallet) {
        customerWalletRepository.delete(wallet);
    }
}
