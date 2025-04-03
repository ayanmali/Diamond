package com.diamond.diamond.services.user;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
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
    private static final int KEY_SIZE = 256;
    private static final int DATA_LENGTH = 128;
    private static final String ALGORITHM = "AES/GCM/NoPadding";

    // Encoded (string) keys from application.properties
    @Value("${wallet.encryption.key}")
    private String walletEncryptionKey;

    @Value("${pin.encryption.key}")
    private String pinEncryptionKey;

    // SecretKey objects used for encrypting data
    private static SecretKey secretWalletKey;
    private static SecretKey secretPinKey;
    // private final PasswordEncoder passwordEncoder;
    // private final AuthenticationManager authManager;

    public AccountService(AccountRepository accountRepository, @Value("${wallet.encryption.key}") String walletEncryptionKey, @Value("${pin.encryption.key}") String pinEncryptionKey /*, PasswordEncoder passwordEncoder, AuthenticationManager authManager*/) {
        this.accountRepository = accountRepository;
        this.walletEncryptionKey = walletEncryptionKey;
        this.pinEncryptionKey = pinEncryptionKey;

        // if (WALLET_ENCRYPTION_KEY == null || WALLET_ENCRYPTION_KEY.isEmpty()) {
        //     throw new IllegalArgumentException("WALLET_ENCRYPTION_KEY is not set in the application properties.");
        // } else {
        //     System.out.println("ENCRYPTION KEY FOUND: " + WALLET_ENCRYPTION_KEY);
        // }

        secretWalletKey = stringToSecretKey(walletEncryptionKey);        // Decode the Base64-encoded String key
        secretPinKey = stringToSecretKey(pinEncryptionKey);              // Decode the Base64-encoded String key

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
    public FetchAccountDto signUp(String email, String name) {
        Account user = new Account();
        user.setEmail(email);
        user.setName(name);
        // user.setBusinessName(input.getBusinessName());
        //user.setWalletSetId(walletSetId);

        // saving the newly registered user to the Users repository
        return new FetchAccountDto(accountRepository.save(user));
    }

    // public List<AccountWallet> getAccountWallets(UUID id) {
        
    // }

    public FetchAccountDto updatePin(UUID id, String encryptedPin) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setEncryptedPin(encryptedPin);
        return new FetchAccountDto(accountRepository.save(account));
    }

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

    // Generate a secure AES key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE, SecureRandom.getInstanceStrong());
        return keyGenerator.generateKey();
    }

    // public static String secretKeyToString(SecretKey secretKey) {
    //     byte[] rawData = secretKey.getEncoded();
    //     return Base64.getEncoder().encodeToString(rawData);
    // }

    public static SecretKey stringToSecretKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    // Encrypt a string (wallet private key or user PIN) using AES/GCM
    // Pass in either the wallet encryption key or the PIN encryption key
    public String encrypt(String data, SecretKey secretKey) throws Exception {
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Create IV (Initialization Vector)
        byte[] iv = new byte[12];
        SecureRandom.getInstanceStrong().nextBytes(iv);

        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(DATA_LENGTH, iv);

        // Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

        // Perform Encryption
        byte[] cipherText = cipher.doFinal(data.getBytes());

        // Concatenate IV and ciphertext
        byte[] encryptedData = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, encryptedData, 0, iv.length);
        System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Decrypt a string (wallet private key or user PIN) using AES/GCM
    // Pass in either the wallet encryption key or the PIN encryption key
    public byte[] decrypt(String encryptedData, SecretKey secretKey) throws Exception {
        // Get Cipher Instance
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Decode the encrypted data
        byte[] decoded = Base64.getDecoder().decode(encryptedData);

        // Extract IV
        byte[] iv = new byte[12];
        System.arraycopy(decoded, 0, iv, 0, iv.length);

        // Extract ciphertext
        byte[] cipherText = new byte[decoded.length - iv.length];
        System.arraycopy(decoded, iv.length, cipherText, 0, cipherText.length);

        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(DATA_LENGTH, iv);

        // Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE,  secretKey, gcmParameterSpec);

        // Perform Decryption
        return cipher.doFinal(cipherText);

        // return new String(decryptedText);
    }

    public static SecretKey getSecretWalletKey() {
        return secretWalletKey;
    }

    public static SecretKey getSecretPinKey() {
        return secretPinKey;
    }

}