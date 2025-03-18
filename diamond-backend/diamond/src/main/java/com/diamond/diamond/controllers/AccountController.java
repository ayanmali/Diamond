package com.diamond.diamond.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.account.FetchAccountDto;
import com.diamond.diamond.dtos.account.RegisterUserDto;
import com.diamond.diamond.dtos.wallets.FetchAccountWalletDto;
import com.diamond.diamond.entities.Account;
import com.diamond.diamond.services.AccountService;
import com.diamond.diamond.services.AccountWalletService;
import com.diamond.diamond.utils.CircleClient;


@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountWalletService accountWalletService;
    private final CircleClient circleClient;

    public AccountController(AccountService accountService, AccountWalletService accountWalletService, CircleClient circleClient) {
        this.accountService = accountService;
        this.accountWalletService = accountWalletService;
        this.circleClient = circleClient;
    }

    private FetchAccountDto loadAccountWallets(FetchAccountDto accountDto) {
        Account account = accountService.findAccountById(accountDto.getId());
        List<FetchAccountWalletDto> wallets = accountWalletService.findWalletDtosByAccount(
                                                                account);
        accountDto.setWallets(wallets);
        return accountDto;
        
    }

    @PostMapping("/signup")
    public FetchAccountDto signup(@RequestBody RegisterUserDto registerUserDto) {
        UUID walletSetId = UUID.fromString(
            circleClient.createWalletSet("", UUID.randomUUID())
        );
        FetchAccountDto accountDto = accountService.signUp(registerUserDto, walletSetId);
        return accountDto;
    }
    
    @GetMapping("/id/{id}")
    public FetchAccountDto getAccountById(@PathVariable(value = "id") String id) {
        FetchAccountDto accountDto = accountService.findAccountDtoById(id);

        accountDto = loadAccountWallets(accountDto);

        return accountDto;
    }

    @GetMapping("/accounts")
    public List<FetchAccountDto> getAccounts(@RequestParam(required=false) String id, @RequestParam(required=false) String email, @RequestParam(required=false) Date createdBefore, @RequestParam(required=false) Date createdAfter, @RequestParam(required=false) Integer pageSize) {
        return accountService.findAccountsWithFilters(
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
    
    @PostMapping("update-email/{id}")
    public FetchAccountDto updateEmail(@RequestBody String email, @PathVariable(value="id") String id) {
        //TODO: process POST request
        FetchAccountDto accountDto = accountService.updateAccountEmail(UUID.fromString(id), email);
        accountDto = loadAccountWallets(accountDto);
        return accountDto;
    }
    
    @PostMapping("/update-name/{id}")
    public FetchAccountDto updateBusinessName(@RequestBody String name, @PathVariable(value="id") String id) {
        //TODO: process POST request
        FetchAccountDto accountDto = accountService.updateAccountName(UUID.fromString(id), name);
        accountDto = loadAccountWallets(accountDto);
        return accountDto;
    }

    @PostMapping("/delete/{id}")
    public FetchAccountDto deleteAccount(@PathVariable(value="id") String id) {
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
