package com.diamond.diamond.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.account.FetchAccountDto;
import com.diamond.diamond.dtos.account.RegisterUserDto;
import com.diamond.diamond.dtos.wallets.FetchAccountWalletDto;
import com.diamond.diamond.entities.user.Account;
import com.diamond.diamond.services.AccountService;
import com.diamond.diamond.services.AccountWalletService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountWalletService accountWalletService;
    //private final CircleApiClient circleApiClient;
    //private final CircleGrpcClient circleGrpcClient;

    public AccountController(AccountService accountService, AccountWalletService accountWalletService /*,CircleApiClient circleApiClient, CircleGrpcClient circleGrpcClient*/) {
        this.accountService = accountService;
        this.accountWalletService = accountWalletService;
        //this.circleApiClient = circleApiClient;
        //this.circleGrpcClient = circleGrpcClient;
    }

    // helper method to add wallets to an account DTO object by fetching from the DB
    private FetchAccountDto loadAccountWallets(FetchAccountDto accountDto) {
        Account account = accountService.findAccountById(accountDto.getId());
        List<FetchAccountWalletDto> wallets = accountWalletService.findWalletDtosByAccount(
                                                                account);
        accountDto.setWallets(wallets);
        return accountDto;
        
    }

    @PostMapping("/signup")
    public FetchAccountDto signup(@Valid @RequestBody RegisterUserDto registerUserDto) {
        // Optional<UUID> optionalWalletSetId = circleGrpcClient.createWalletSet("");
        // // todo: error handling
        // if (optionalWalletSetId.isEmpty()) {
        //     return new FetchAccountDto();
        // }
        // UUID walletSetId = optionalWalletSetId.get();
        FetchAccountDto accountDto = accountService.signUp(registerUserDto);
        return accountDto;
    }
    
    @GetMapping("/id/{id}")
    public FetchAccountDto getAccountById(@PathVariable(value = "id") UUID id) {
        FetchAccountDto accountDto = accountService.findAccountDtoById(id);

        accountDto = loadAccountWallets(accountDto);

        return accountDto;
    }

    @GetMapping("/accounts")
    public List<FetchAccountDto> getAccounts(@RequestParam(required=false) UUID id, @RequestParam(required=false) String email, @RequestParam(required=false) Date createdBefore, @RequestParam(required=false) Date createdAfter, @RequestParam(required=false) Integer pageSize) {
        return accountService.findAccountDtosWithFilters(
        id,
        email,
        createdBefore,
        createdAfter,
        pageSize
        );
    }

    @GetMapping("/email/{email}")
    public FetchAccountDto getAccountByEmail(@PathVariable(value="email") String email) {
        // getting the account dto
        FetchAccountDto accountDto = accountService.findAccountDtoByEmail(email);
        
        accountDto = loadAccountWallets(accountDto);

        return accountDto;
    }

    // @GetMapping("wallets/{id}")
    // List<FetchAccountWalletDto> getWallets(@PathVariable(value="id") String id) {
    //     return accountService.findAccountWallets(UUID.fromString(id));
    // }
    
    @PatchMapping("/id/{id}/update-email")
    public FetchAccountDto updateEmail(@RequestBody String email, @PathVariable(value="id") UUID id) {
        //TODO: process POST request
        FetchAccountDto accountDto = accountService.updateAccountEmail(id, email);
        accountDto = loadAccountWallets(accountDto);
        return accountDto;
    }
    
    @PatchMapping("/id/{id}/update-name")
    public FetchAccountDto updateBusinessName(@RequestBody String name, @PathVariable(value="id") UUID id) {
        //TODO: process POST request
        FetchAccountDto accountDto = accountService.updateAccountName(id, name);
        accountDto = loadAccountWallets(accountDto);
        return accountDto;
    }

    @DeleteMapping("/id/{id}/delete")
    public FetchAccountDto deleteAccount(@PathVariable(value="id") UUID id) {
        //TODO: process POST request
        FetchAccountDto accountDto = accountService.findAccountDtoById(id);
        accountDto = loadAccountWallets(accountDto);
        accountService.deleteAccountById(id);
        return accountDto;
    }
    
    // @GetMapping("/email")
    // public String getMethodName(@RequestParam String param) {
    //     return new String();
    // }
    
    
    // public ResponseEntity<Account> getAccountById() {

    // }
}
