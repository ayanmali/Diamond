package com.diamond.diamond.services.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.account.FetchAccountDto;
import com.diamond.diamond.entities.user.Account;
import com.diamond.diamond.repositories.user.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    
    // private final PasswordEncoder passwordEncoder;
    // private final AuthenticationManager authManager;

    public AccountService(AccountRepository accountRepository/*, PasswordEncoder passwordEncoder, AuthenticationManager authManager*/) {
        this.accountRepository = accountRepository;

        // if (WALLET_ENCRYPTION_KEY == null || WALLET_ENCRYPTION_KEY.isEmpty()) {
        //     throw new IllegalArgumentException("WALLET_ENCRYPTION_KEY is not set in the application properties.");
        // } else {
        //     System.out.println("ENCRYPTION KEY FOUND: " + WALLET_ENCRYPTION_KEY);
        // }

        // byte[] decodedWalletKey = Base64.getDecoder().decode(WALLET_ENCRYPTION_KEY);

        // // Create a SecretKey object from the decoded key
        // this.secretWalletKey = new SecretKeySpec(decodedWalletKey, 0, decodedWalletKey.length, ALGORITHM);

        // // Decode the Base64-encoded String key
        // byte[] decodedPinKey = Base64.getDecoder().decode(PIN_ENCRYPTION_KEY);

        // // Create a SecretKey object from the decoded key
        // this.secretPinKey = new SecretKeySpec(decodedPinKey, 0, decodedPinKey.length, ALGORITHM);

        // this.passwordEncoder = passwordEncoder;
        // this.authManager = authManager;
    }

    // public Account saveUser(Account account) {
    //     return accountRepository.save(account);
    // }
    // public FetchAccountDto convertAccountToFetchDto(Account account) {
    //     FetchAccountDto accountDto = new FetchAccountDto();
        
    //     accountDto.setEmail(account.getEmail());
    //     accountDto.setName(account.getName());
    //     accountDto.setBusinessName(account.getBusinessName());
    //     accountDto.setId(account.getId());
    //     accountDto.setCreatedAt(account.getCreatedAt());
    //     accountDto.setUpdatedAt(account.getUpdatedAt());

    //     if (account.getWallets() != null && Hibernate.isInitialized(account.getWallets())) {
    //         accountDto.setWallets(
    //             account.getWallets().stream() // Convert the List<AccountWallet> to a Stream<AccountWallet>
    //             .map(AccountWalletService::convertAccountWalletToFetchDto) // Map each AccountWallet to FetchAccountWalletDto
    //             .collect(Collectors.toList()));
    //     }
    //     return accountDto;
    // }

    //@Transactional
    // public FetchAccountDto signUp(String email, String name) {
    //     Account user = new Account();
    //     user.setEmail(email);
    //     user.setName(name);
    //     // user.setBusinessName(input.getBusinessName());
    //     //user.setWalletSetId(walletSetId);

    //     // saving the newly registered user to the Users repository
    //     return new FetchAccountDto(accountRepository.save(user));
    // }

    /*
     * Checks if a given user exists in the database
     */
    public boolean existsById(UUID id) {
        return accountRepository.existsById(id);
    }

    /*
     * Checks if a given user email exists in the database
     */
    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    // public List<AccountWallet> getAccountWallets(UUID id) {
        
    // }

    public List<FetchAccountDto> findAccountDtosWithFilters(
        UUID id, 
        String email, 
        Date createdBefore, 
        Date createdAfter, 
        Integer pageSize) {
    
        //UUID uuid = id != null ? UUID.fromString(id) : null;
    
        // creating the pagable object
        Pageable pageable = pageSize != null ? 
            PageRequest.of(0, pageSize) : 
            Pageable.unpaged();
        
        // Querying the DB
        Page<Account> accounts = accountRepository.findAccountsWithFilters(
            id, 
            email, 
            createdBefore, 
            createdAfter, 
            pageable
        );
        
        // Returning the data in the appropriate format
        return accounts.getContent()
            .stream()
            .map(FetchAccountDto::new)
            .collect(Collectors.toList());
    }

    //@Transactional
    public FetchAccountDto findAccountDtoById(String id) {
        return new FetchAccountDto(
            accountRepository.findById(UUID.fromString(id))
            .orElseThrow());
    }

    //@Transactional
    public FetchAccountDto findAccountDtoById(UUID id) {
        return new FetchAccountDto(
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
        return new FetchAccountDto(
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
        return new FetchAccountDto(accountRepository.save(account));
    }

    public FetchAccountDto updateAccountName(UUID id, String name) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setBusinessName(name);
        return new FetchAccountDto(accountRepository.save(account));
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