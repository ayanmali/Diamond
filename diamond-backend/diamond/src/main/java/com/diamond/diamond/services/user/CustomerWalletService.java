package com.diamond.diamond.services.user;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.wallets.FetchCustomerWalletDto;
import com.diamond.diamond.dtos.wallets.NewCustomerWalletDto;
import com.diamond.diamond.entities.user.Customer;
import com.diamond.diamond.entities.user.CustomerWallet;
import com.diamond.diamond.repositories.user.CustomerWalletRepository;

@Service
public class CustomerWalletService {
    private final CustomerWalletRepository customerWalletRepository;

    public CustomerWalletService(CustomerWalletRepository customerWalletRepository) {
        this.customerWalletRepository = customerWalletRepository;
    }

    // public static FetchCustomerWalletDto convertCustomerWalletToFetchDto(CustomerWallet wallet) {
    //     FetchCustomerWalletDto walletDto = new FetchCustomerWalletDto();
    //     walletDto.setId(wallet.getId());
    //     walletDto.setAddress(wallet.getAddress());
    //     walletDto.setChain(wallet.getChain());
    //     walletDto.setCustomerId(wallet.getCustomer().getId());
    //     return walletDto;
    // }

    public FetchCustomerWalletDto saveWallet(UUID customerId, NewCustomerWalletDto walletDto) {
        CustomerWallet wallet = new CustomerWallet(walletDto.getAddress(),
                                                   walletDto.getChain(),
                                                   customerId);
        return new FetchCustomerWalletDto(customerWalletRepository.save(wallet));
    }

    public FetchCustomerWalletDto findWalletDtoById(UUID id) {
        return new FetchCustomerWalletDto(customerWalletRepository.findById(id).orElseThrow());
    }

    public CustomerWallet findWalletById(UUID id) {
        return customerWalletRepository.findById(id).orElseThrow();
    }

    public FetchCustomerWalletDto findWalletDtoByAddress(String address) {
        return new FetchCustomerWalletDto(customerWalletRepository.findByAddress(address).orElseThrow());
    }

    public CustomerWallet findWalletByAddress(String address) {
        return customerWalletRepository.findByAddress(address).orElseThrow();
    }

    public List<CustomerWallet> findWalletsByCustomer(Customer customer) {
        return customerWalletRepository.findByCustomerId(customer.getId());
    }

    public List<FetchCustomerWalletDto> findWalletDtosByCustomer(Customer customer) {
        return customerWalletRepository.findByCustomerId(customer.getId()).stream() // Convert the List<Customer> to a Stream<Customer>
        .map(FetchCustomerWalletDto::new) // Map each Customer to FetchCustomerDto
        .collect(Collectors.toList());
    }

    public void deleteWalletById(UUID id) {
        customerWalletRepository.deleteById(id);
    }

    public void deleteWallet(CustomerWallet wallet) {
        customerWalletRepository.delete(wallet);
    }
}
