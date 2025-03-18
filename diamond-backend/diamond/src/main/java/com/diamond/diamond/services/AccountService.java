package com.diamond.diamond.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.account.FetchAccountDto;
import com.diamond.diamond.dtos.account.RegisterUserDto;
import com.diamond.diamond.entities.Account;
import com.diamond.diamond.repositories.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    // private final PasswordEncoder passwordEncoder;
    // private final AuthenticationManager authManager;

    public AccountService(AccountRepository accountRepository/*, PasswordEncoder passwordEncoder, AuthenticationManager authManager*/) {
        this.accountRepository = accountRepository;
        // this.passwordEncoder = passwordEncoder;
        // this.authManager = authManager;
    }

    // public Account saveUser(Account account) {
    //     return accountRepository.save(account);
    // }
    public FetchAccountDto convertAccountToFetchDto(Account account) {
        FetchAccountDto accountDto = new FetchAccountDto();
        
        accountDto.setEmail(account.getEmail());
        accountDto.setBusinessName(account.getBusinessName());
        accountDto.setId(account.getId());
        accountDto.setCreatedAt(account.getCreatedAt());
        accountDto.setUpdatedAt(account.getUpdatedAt());

        if (account.getWallets() != null && Hibernate.isInitialized(account.getWallets())) {
            accountDto.setWallets(
                account.getWallets().stream() // Convert the List<AccountWallet> to a Stream<AccountWallet>
                .map(AccountWalletService::convertAccountWalletToFetchDto) // Map each AccountWallet to FetchAccountWalletDto
                .collect(Collectors.toList()));
        }
        return accountDto;
    }

    //@Transactional
    public FetchAccountDto signUp(RegisterUserDto input, UUID walletSetId) {
        Account user = new Account();
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword());
        user.setBusinessName(input.getBusinessName());
        user.setWalletSetId(walletSetId);

        // saving the newly registered user to the Users repository
        return convertAccountToFetchDto(accountRepository.save(user));
    }

    // public List<AccountWallet> getAccountWallets(UUID id) {
        
    // }

    public List<FetchAccountDto> findAccountsWithFilters(
        String id, 
        String email, 
        Date createdBefore, 
        Date createdAfter, 
        Integer pageSize) {
    
        UUID uuid = id != null ? UUID.fromString(id) : null;
    
        Pageable pageable = pageSize != null ? 
            PageRequest.of(0, pageSize) : 
            Pageable.unpaged();

        Page<Account> accounts = accountRepository.findAccountsWithFilters(
            uuid, 
            email, 
            createdBefore, 
            createdAfter, 
            pageable
        );

        return accounts.getContent()
            .stream()
            .map(this::convertAccountToFetchDto)
            .collect(Collectors.toList());
    }

    //@Transactional
    public FetchAccountDto findAccountDtoById(String id) {
        return convertAccountToFetchDto(
            accountRepository.findById(UUID.fromString(id))
            .orElseThrow());
    }

    //@Transactional
    public FetchAccountDto findAccountDtoById(UUID id) {
        return convertAccountToFetchDto(
            accountRepository.findById(id)
            .orElseThrow());
    }   

    //@Transactional
    public Account findAccountById(String id) {
        return accountRepository.findById(UUID.fromString(id))
        .orElseThrow();
    }

    //@Transactional
    public Account findAccountById(UUID id) {
        return accountRepository.findById(id)
        .orElseThrow();
    }

    public FetchAccountDto findAccountDtoByEmail(String email) {
        return convertAccountToFetchDto(
            accountRepository.findByEmail(email)
            .orElseThrow());

        // List<Account> accounts = accountRepository.findByEmail(email);
        // return accounts.stream().map(account -> {
        //     FetchAccountDto accountDto = new FetchAccountDto();
        //     accountDto.setEmail(account.getEmail());
        //     accountDto.setBusinessName(account.getBusinessName());
        //     accountDto.setId(account.getId());
        //     accountDto.setCreatedAt(account.getCreatedAt());
        //     accountDto.setUpdatedAt(account.getUpdatedAt());
        //     return accountDto;
        // }).collect(Collectors.toList());
    }

    public Account findAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow();
    }

    // public List<AccountWallet> findWallets(UUID walletId) {
    //     return accountRepository.findWallets(walletId);
    // }
    // @Transactional
    // public List<FetchAccountWalletDto> findAccountWallets(UUID accountId) {        
    //     FetchAccountDto accountDto = findAccountDtoById(accountId);
    //     if (accountDto.getWallets() == null) return new ArrayList<>();

    //     Account account = accountRepository.findById(accountId).orElseThrow();
    //     List<FetchAccountWalletDto> wallets = new ArrayList<>();
    //     for (AccountWallet wallet : account.getWallets()) {
    //         wallets.add(AccountWalletService.convertAccountWalletToFetchDto(wallet));
    //     }
    //     return wallets;
    // }

    public FetchAccountDto updateAccountEmail(UUID id, String email) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setEmail(email);
        return convertAccountToFetchDto(accountRepository.save(account));
    }

    public FetchAccountDto updateAccountName(UUID id, String name) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setBusinessName(name);
        return convertAccountToFetchDto(accountRepository.save(account));
    }

    public void deleteAccountById(UUID id) {
        accountRepository.deleteById(id);
    }

    public void deleteAccountById(String id) {
        UUID uuidId = UUID.fromString(id);
        deleteAccountById(uuidId);
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }

}