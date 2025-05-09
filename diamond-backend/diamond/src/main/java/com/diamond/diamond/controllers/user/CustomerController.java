package com.diamond.diamond.controllers.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.customer.FetchCustomerDto;
import com.diamond.diamond.dtos.customer.NewCustomerDto;
import com.diamond.diamond.services.user.AccountService;
import com.diamond.diamond.services.user.CustomerService;
import com.diamond.diamond.services.user.CustomerWalletService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final AccountService accountService;
    private final CustomerWalletService customerWalletService;

    public CustomerController(CustomerService customerService, AccountService accountService, CustomerWalletService customerWalletService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.customerWalletService = customerWalletService;
    }

    // private FetchCustomerDto loadCustomerWallets(FetchCustomerDto customerDto) {
    //     Customer customer = customerService.findCustomerById(customerDto.getId());
    //     List<FetchCustomerWalletDto> customerWallets = customerWalletService.findWalletDtosByCustomer(customer);
    //     customerDto.setWallets(customerWallets);
    //     return customerDto;
    // }

    @PostMapping("/{id}/new")
    public FetchCustomerDto addCustomer(@PathVariable(value="id") UUID accountId, @Valid @RequestBody NewCustomerDto customerDto) {
        //TODO: process POST request
        //Account account = accountService.findAccountById(customerDto.getAccountId());
        return customerService.saveCustomer(customerDto, accountId);
    }

    @GetMapping("/{id}/customers")
    public List<FetchCustomerDto> getCustomers(@RequestBody UUID customerId,
                                               @RequestBody String email,
                                               @PathVariable(value="id") UUID accountId,
                                               @RequestBody Date createdBefore,
                                               @RequestBody Date createdAfter,
                                               @RequestBody Integer pageSize) {
        return customerService.findCustomerDtosWithFilters(customerId, email, accountId, createdBefore, createdAfter, pageSize);
    }
    

    @GetMapping("/customerid/{id}")
    public FetchCustomerDto getCustomerById(@PathVariable(value="id") UUID id) {
        //Customer customer = customerService.findCustomerById(id);
        return customerService.findCustomerDtoById(id);
        //customerDto = loadCustomerWallets(customerDto);
        //return customerDto;
    }

    // @GetMapping("/accountid/{id}")
    // public List<FetchCustomerDto> getCustomersByAccount(@PathVariable(value="id") UUID accountId) {
    //     List<FetchCustomerDto> customerDtos = new ArrayList<>();
        
    //     // get all the customers
    //     List<Customer> customers = customerService.findCustomersByAccount(accountService.findAccountById(accountId));
        
    //     // get the list of wallets for each of those customers
    //     for (Customer customer : customers) {
    //         // get the dto for this customer
    //         FetchCustomerDto customerDto = CustomerService.convertCustomerToFetchDto(customer);
    //         customerDto = loadCustomerWallets(customerDto);
    //         // add the customer dto to the list
    //         customerDtos.add(customerDto);
    //     }
    //     // return a list containing all the customers, each customer contains a list of walletDtos that belong to them
    //     return customerDtos;

    // }
    
    @GetMapping("/email/{email}")
    public FetchCustomerDto getCustomerByEmail(@PathVariable(value="email") String email) {
        return customerService.findCustomerDtoByEmail(email);
        // customerDto = loadCustomerWallets(customerDto);
        // return customerDto;
    }

    @PatchMapping("/customerid/{id}/update-name")
    public FetchCustomerDto updateName(@PathVariable(value="id") UUID id, @RequestBody String name) {
        //TODO: process POST request
        return customerService.updateCustomerName(id, name);
        // customerDto = loadCustomerWallets(customerDto);
        // return customerDto;
    }

    @PatchMapping("/customerid/{id}/update-email")
    public FetchCustomerDto updateEmail(@PathVariable(value="id") UUID id, @RequestBody String email) {
        //TODO: process POST request
        return customerService.updateCustomerEmail(id, email);
        // customerDto = loadCustomerWallets(customerDto);
        // return customerDto;
    }

    @DeleteMapping("/id/{id}/delete")
    public FetchCustomerDto delete(@PathVariable(value="id") UUID id) {
        //TODO: process POST request
        FetchCustomerDto customerDto = customerService.findCustomerDtoById(id);
        //customerDto = loadCustomerWallets(customerDto);
        customerService.deleteCustomerById(id);
        return customerDto;
    }
    
}
