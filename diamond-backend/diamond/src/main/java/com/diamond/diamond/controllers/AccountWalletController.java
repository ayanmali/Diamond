package com.diamond.diamond.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.wallets.FetchAccountWalletDto;
import com.diamond.diamond.dtos.wallets.NewAccountWalletDto;
import com.diamond.diamond.entities.Account;
import com.diamond.diamond.services.AccountService;
import com.diamond.diamond.services.AccountWalletService;
//import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/userwallets")
public class AccountWalletController {
    private final AccountWalletService accountWalletService;
    private final AccountService accountService;

    public AccountWalletController(AccountWalletService accountWalletService, AccountService accountService) {
        this.accountWalletService = accountWalletService;
        this.accountService = accountService;
    }

    @PostMapping("/new")
    public FetchAccountWalletDto createWallet(@RequestBody NewAccountWalletDto accountWalletDto) {
        //TODO: process POST request
        Account account = accountService.findAccountById(accountWalletDto.getAccountId());
        return accountWalletService.saveWallet(accountWalletDto, account);
    }

    @GetMapping("/id/{id}")
    public FetchAccountWalletDto getWalletByWalletId(@PathVariable(value = "id") Long id) {
        return accountWalletService.findWalletDtoById(id);
    }

    /*
     * Get all wallets associated with a Account
     */
    @GetMapping("/Accountid/{id}")
    public List<FetchAccountWalletDto> getWalletsByAccount(@PathVariable(value="id") String accountId) {
        return accountWalletService.findWalletDtosByAccount(accountService.findAccountById(accountId));
    }
    
    @GetMapping("/address/{address}")
    public FetchAccountWalletDto getWalletByAddress(@PathVariable(value = "address") String address) {
        return accountWalletService.findWalletDtoByAddress(address);
    }
    
    @PostMapping("/update-name/{id}")
    public FetchAccountWalletDto updateWalletName(@RequestBody String name, @PathVariable(value = "id") Long id) {
        return accountWalletService.updateWalletName(id, name);
    }

    @PostMapping("/archive/{id}")
    public FetchAccountWalletDto archiveWallet(@PathVariable(value="id") Long id) {
        //TODO: process POST request
        return accountWalletService.archiveWallet(id);
    }

    @PostMapping("/activate")
    public FetchAccountWalletDto reactivateWallet(@PathVariable(value="id") Long id) {
        //TODO: process POST request
        return accountWalletService.reactivateWallet(id);
    }
    
}
