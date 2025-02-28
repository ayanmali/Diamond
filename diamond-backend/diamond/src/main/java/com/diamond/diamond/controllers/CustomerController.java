package com.diamond.diamond.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.customer.FetchCustomerDto;
import com.diamond.diamond.dtos.customer.NewCustomerDto;
import com.diamond.diamond.dtos.wallets.FetchCustomerWalletDto;
import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.services.CustomerService;
import com.diamond.diamond.services.CustomerWalletService;
import com.diamond.diamond.services.VendorService;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final VendorService vendorService;
    private final CustomerWalletService customerWalletService;

    public CustomerController(CustomerService customerService, VendorService vendorService, CustomerWalletService customerWalletService) {
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.customerWalletService = customerWalletService;
    }

    private FetchCustomerDto loadCustomerWallets(FetchCustomerDto customerDto) {
        Customer customer = customerService.findCustomerById(customerDto.getId());
        List<FetchCustomerWalletDto> customerWallets = customerWalletService.findWalletDtosByCustomer(customer);
        customerDto.setWallets(customerWallets);
        return customerDto;
    }

    @PostMapping("/new")
    public FetchCustomerDto addCustomer(@RequestBody NewCustomerDto customerDto) {
        //TODO: process POST request
        Vendor vendor = vendorService.findVendorById(customerDto.getVendorId());
        return customerService.saveCustomer(customerDto, vendor);
    }

    @GetMapping("/id/{id}")
    public FetchCustomerDto getCustomerById(@PathVariable(value="id") String id) {
        //Customer customer = customerService.findCustomerById(id);
        FetchCustomerDto customerDto = customerService.findCustomerDtoById(id);
        customerDto = loadCustomerWallets(customerDto);
        return customerDto;
    }

    @GetMapping("/vendorid/{id}")
    public List<FetchCustomerDto> getCustomersByVendor(@PathVariable(value="id") String vendorId) {
        List<FetchCustomerDto> customerDtos = new ArrayList<>();
        
        // get all the customers
        List<Customer> customers = customerService.findCustomersByVendor(vendorService.findVendorById(vendorId));
        
        // get the list of wallets for each of those customers
        for (Customer customer : customers) {
            // get the dto for this customer
            FetchCustomerDto customerDto = CustomerService.convertCustomerToFetchDto(customer);
            customerDto = loadCustomerWallets(customerDto);
            // add the customer dto to the list
            customerDtos.add(customerDto);
        }
        // return a list containing all the customers, each customer contains a list of walletDtos that belong to them
        return customerDtos;

    }
    
    @GetMapping("/email/{email}")
    public FetchCustomerDto getCustomerByEmail(@PathVariable(value="email") String email) {
        FetchCustomerDto customerDto = customerService.findCustomerDtoByEmail(email);
        customerDto = loadCustomerWallets(customerDto);
        return customerDto;
    }

    @PostMapping("/update-name/{id}")
    public FetchCustomerDto updateName(@PathVariable(value="id") String id, @RequestBody String name) {
        //TODO: process POST request
        FetchCustomerDto customerDto = customerService.updateCustomerName(UUID.fromString(id), name);
        customerDto = loadCustomerWallets(customerDto);
        return customerDto;
    }

    @PostMapping("/update-email")
    public FetchCustomerDto updateEmail(@PathVariable(value="id") String id, @RequestBody String email) {
        //TODO: process POST request
        FetchCustomerDto customerDto = customerService.updateCustomerEmail(UUID.fromString(id), email);
        customerDto = loadCustomerWallets(customerDto);
        return customerDto;
    }

    @PostMapping("/delete/{id}")
    public FetchCustomerDto delete(@PathVariable(value="id") String id) {
        //TODO: process POST request
        FetchCustomerDto customerDto = customerService.findCustomerDtoById(id);
        customerDto = loadCustomerWallets(customerDto);
        customerService.deleteCustomerById(id);
        return customerDto;
    }
    
}
