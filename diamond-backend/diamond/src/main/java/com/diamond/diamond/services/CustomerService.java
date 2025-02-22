package com.diamond.diamond.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.customer.FetchCustomerDto;
import com.diamond.diamond.dtos.customer.NewCustomerDto;
import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.repositories.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static FetchCustomerDto convertCustomerToFetchDto(Customer customer) {
        FetchCustomerDto customerDto = new FetchCustomerDto();
        
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setVendorId(customer.getVendor().getId());
        customerDto.setCreatedAt(customer.getCreatedAt());
        customerDto.setUpdatedAt(customer.getUpdatedAt());


        if (customer.getWallets() != null && Hibernate.isInitialized(customer.getWallets())) {
            customerDto.setWallets(
                customer.getWallets().stream() // Convert the List<CustomerWallet> to a Stream<CustomerWallet>
                .map(CustomerWalletService::convertCustomerWalletToFetchDto) // Map each Customerallet to FetchCustomerWalletDto
                .collect(Collectors.toList()));
        }

        return customerDto;
    }

    public FetchCustomerDto saveCustomer(NewCustomerDto newCustomer, Vendor vendor) {
        Customer customer = new Customer(vendor,
                                         newCustomer.getName(),
                                         newCustomer.getEmail()
                                         //newCustomer.getWallets() 
                                        );
        
        return convertCustomerToFetchDto(customerRepository.save(customer));
    }

    public Customer findCustomerById(String id) {
        return customerRepository.findById(UUID.fromString(id)).orElseThrow();
    }

    public Customer findCustomerById(UUID id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public FetchCustomerDto findCustomerDtoById(String id) {
        //return convertCustomerToFetchDto(customerRepository.findById(UUID.fromString(id)).orElseThrow());
        return convertCustomerToFetchDto(
            customerRepository.findById(UUID.fromString(id))
            .orElseThrow());
    }

    public FetchCustomerDto findCustomerDtoById(UUID id) {
        //return convertCustomerToFetchDto(customerRepository.findById(id).orElseThrow());
        return convertCustomerToFetchDto(
            customerRepository.findById(id)
            .orElseThrow());
    }

    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElseThrow();
    }

    public FetchCustomerDto findCustomerDtoByEmail(String email) {
        return convertCustomerToFetchDto(customerRepository.findByEmail(email).orElseThrow());
    }

    public List<FetchCustomerDto> findCustomerDtosByVendor(Vendor vendor) {
        return customerRepository.findByVendor(vendor).stream() // Convert the List<Customer> to a Stream<Customer>
        .map(CustomerService::convertCustomerToFetchDto) // Map each Customer to FetchCustomerDto
        .collect(Collectors.toList());
    }

    public List<Customer> findCustomersByVendor(Vendor vendor) {
        return customerRepository.findByVendor(vendor);
    }

    public FetchCustomerDto updateCustomerEmail(UUID id, String email) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        // Modify the entity's properties
        customer.setEmail(email);
        // Save the updated entity
        return convertCustomerToFetchDto(customerRepository.save(customer));
    }

    public FetchCustomerDto updateCustomerName(UUID id, String name) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        // Modify the entity's properties
        customer.setName(name);
        // Save the updated entity
        return convertCustomerToFetchDto(customerRepository.save(customer));
    }

    public void deleteCustomerById(UUID id) {
        customerRepository.deleteById(id);
    }

    public void deleteCustomerById(String id) {
        UUID uuidId = UUID.fromString(id);
        deleteCustomerById(uuidId);
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

}