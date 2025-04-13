package com.diamond.diamond.services;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.account.EmailPasswordRegisterDto;
import com.diamond.diamond.dtos.account.PasswordLoginDto;

import com.diamond.diamond.entities.user.Account;
import com.diamond.diamond.repositories.user.AccountRepository;

@Service
public class AuthService {
    private static final int KEY_SIZE = 256;
    private static final int DATA_LENGTH = 128;
    private static final String ALGORITHM = "AES/GCM/NoPadding";

    private final AccountRepository accountRepository;

    // Encoded (string) encryption keys from application.properties
    @Value("${wallet.encryption.key}")
    private String walletEncryptionKey;

    @Value("${pin.encryption.key}")
    private String pinEncryptionKey;

    @Value("${password.encryption.key}")
    private String passwordEncryptionKey;

    // SecretKey objects used for encrypting data
    private final SecretKey secretWalletKey;
    private final SecretKey secretPinKey;
    private final SecretKey secretPasswordKey;

    public AuthService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

        this.secretWalletKey = stringToSecretKey(walletEncryptionKey);        // Decode the Base64-encoded String key
        this.secretPinKey = stringToSecretKey(pinEncryptionKey);              // Decode the Base64-encoded String key
        this.secretPasswordKey = stringToSecretKey(passwordEncryptionKey);    // Decode the Base64-encoded String key
    }

    // public Account saveUser(Account Account) {
    //     return AccountRepository.save(Account);
    // }

    // public Optional<Account> findAccountById(UUID id) {
    //     return accountRepository.findById(id);
    // }

    // public Optional<Account> findAccountByEmail(String email) {
    //     return accountRepository.findByEmail(email);
    // }

    // public Account updateAccountEmail(UUID id, String email) {
    //     Optional<Account> optionalAccount accountRepository.findById(id);
    //     if (optionalAccount.isPresent()) {
    //         Account Account = optionalAccount.get();
    //         Account.setEmail(email);
    //         return accountRepository.save(Account);
    //     }
    //     return null;
    // }

    // public Account updateAccountName(UUID id, String name) {
    //     Optional<Account> optionalAccount accountRepository.findById(id);
    //     if (optionalAccount.isPresent()) {
    //         Account Account = optionalAccount.get();
    //         Account.setBusinessName(name);
    //         return AccountRepository.save(Account);
    //     }
    //     return null;
    // }

    // public void deleteAccountById(UUID id) {
    //     accountRepository.deleteById(id);
    // }

    // public void deleteAccount(Account Account) {
    //     accountRepository.delete(Account);
    // }

    public Account signUp(EmailPasswordRegisterDto input) {
        try {
            // Getting the email and name specified in the request
            Account user = new Account();
            user.setEmail(input.getEmail());
            user.setName(input.getName());

            // Encrypting the plain text password provided in the request
            user.setEncryptedPassword(
                encrypt(input.getPlainTextPassword(), this.secretPasswordKey)
            );

            // saving the newly registered user to the Accounts repository
            return accountRepository.save(user);
        } 
        catch (Exception e) {
            System.out.println("Error signing up user: " + e.getMessage());
            return null;
        }
    }

    public Account authenticate(PasswordLoginDto input) {
        // authManager.authenticate(
        //         new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        // );
        try {
            String encryptedPassword = accountRepository.findByEmail(input.getEmail())
                                                        .orElseThrow()
                                                        .getEncryptedPassword();
            String decryptedPassword = decrypt(encryptedPassword, secretPasswordKey);

            if (decryptedPassword.equals(input.getPassword())) {
                // returns the User object if it successfully authenticates
                return accountRepository.findByEmail(input.getEmail()).orElseThrow();
            }
        }
        catch (Exception e) {
            System.out.println("Error authenticating user: " + e.getMessage());
        }
        return null;
    }

    // Generate a secure AES encryption key
    public SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE, SecureRandom.getInstanceStrong());
        return keyGenerator.generateKey();
    }

    // public static String secretKeyToString(SecretKey secretKey) {
    //     byte[] rawData = secretKey.getEncoded();
    //     return Base64.getEncoder().encodeToString(rawData);
    // }

    public final SecretKey stringToSecretKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    // Encrypt a string (password, wallet private key or user PIN) using AES/GCM
    // Pass in either the password encryption key or wallet encryption key or the PIN encryption key
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
    // Pass in either the password, wallet, or PIN encryption key
    public String decrypt(String encryptedData, SecretKey secretKey) throws Exception {
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
        // return cipher.doFinal(cipherText);

        return new String(cipher.doFinal(cipherText));
    }

    public SecretKey getSecretWalletKey() {
        return secretWalletKey;
    }

    public SecretKey getSecretPinKey() {
        return secretPinKey;
    }

    public SecretKey getPasswordSecretKey() {
        return secretPasswordKey;
    }
}
