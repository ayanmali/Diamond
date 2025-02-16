package com.diamond.diamond.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.customer.FetchCustomerDto;
import com.diamond.diamond.dtos.customer.NewCustomerDto;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.services.CustomerService;
import com.diamond.diamond.services.VendorService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final VendorService vendorService;

    public CustomerController(CustomerService customerService, VendorService vendorService) {
        this.customerService = customerService;
        this.vendorService = vendorService;
    }

    @PostMapping("/new")
    public FetchCustomerDto addCustomer(@RequestBody NewCustomerDto customerDto) {
        //TODO: process POST request
        Vendor vendor = vendorService.findVendorById(customerDto.getVendorId());
        return customerService.saveCustomer(customerDto, vendor);
    }

    @GetMapping("/id/{id}")
    public FetchCustomerDto getCustomerById(@PathVariable(value="id") String id) {
        return customerService.findCustomerDtoById(id);
    }

    @GetMapping("/email/{email}")
    public FetchCustomerDto getCustomerByEmail(@PathVariable(value="email") String email) {
        return customerService.findCustomerDtoByEmail(email);
    }

    @PostMapping("/update-name/{id}")
    public FetchCustomerDto updateName(@PathVariable(value="id") String id, @RequestBody String name) {
        //TODO: process POST request
        return customerService.updateCustomerName(UUID.fromString(id), name);
    }

    @PostMapping("/update-email")
    public FetchCustomerDto updateEmail(@PathVariable(value="id") String id, @RequestBody String email) {
        //TODO: process POST request
        return customerService.updateCustomerEmail(UUID.fromString(id), email);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable(value="id") String id) {
        //TODO: process POST request
        customerService.deleteCustomerById(id);
    }
    
}
