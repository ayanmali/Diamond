package com.diamond.diamond.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.repositories.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomerById(String id) {
        return customerRepository.findById(UUID.fromString(id)).orElseThrow();
    }

    public Customer findCustomerById(UUID id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElseThrow();
    }

    public Customer updateCustomerEmail(UUID id, String email) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            // Modify the entity's properties
            customer.setEmail(email);
            // Save the updated entity
            return customerRepository.save(customer);
        }
        return null;
    }

    public Customer updateCustomerName(UUID id, String name) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            // Modify the entity's properties
            customer.setName(name);
            // Save the updated entity
            return customerRepository.save(customer);
        }
        return null;
    }

    public void deleteCustomerById(UUID id) {
        customerRepository.deleteById(id);
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

}