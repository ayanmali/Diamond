// package com.diamond.diamond.services;

// import java.util.Optional;
// import java.util.UUID;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.diamond.diamond.dtos.LoginUserDto;
// import com.diamond.diamond.dtos.RegisterUserDto;
// import com.diamond.diamond.entities.Account;
// import com.diamond.diamond.repositories.AccountRepository;

// @Service
// public class AuthService {

//     private final AccountRepository AccountRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final AuthenticationManager authManager;

//     public AuthService(AccountRepository AccountRepository, PasswordEncoder passwordEncoder, AuthenticationManager authManager) {
//         this.AccountRepository accountRepository;
//         this.passwordEncoder = passwordEncoder;
//         this.authManager = authManager;
//     }

//     // public Account saveUser(Account Account) {
//     //     return AccountRepository.save(Account);
//     // }

//     public Optional<Account> findAccountById(UUID id) {
//         return AccountRepository.findById(id);
//     }

//     public Optional<Account> findAccountByEmail(String email) {
//         return AccountRepository.findByEmail(email);
//     }

//     public Account updateAccountEmail(UUID id, String email) {
//         Optional<Account> optionalAccount accountRepository.findById(id);
//         if (optionalAccount.isPresent()) {
//             Account Account = optionalAccount.get();
//             Account.setEmail(email);
//             return AccountRepository.save(Account);
//         }
//         return null;
//     }

//     public Account updateAccountName(UUID id, String name) {
//         Optional<Account> optionalAccount accountRepository.findById(id);
//         if (optionalAccount.isPresent()) {
//             Account Account = optionalAccount.get();
//             Account.setBusinessName(name);
//             return AccountRepository.save(Account);
//         }
//         return null;
//     }

//     public void deleteAccountById(UUID id) {
//         AccountRepository.deleteById(id);
//     }

//     public void deleteAccount(Account Account) {
//         AccountRepository.delete(Account);
//     }

//     public Account signUp(RegisterUserDto input) {
//         Account user = new Account();
//         user.setEmail(input.getEmail());
//         user.setPassword(passwordEncoder.encode(input.getPassword()));
//         user.setBusinessName(input.getFullName());

//         // saving the newly registered user to the Users repository
//         return AccountRepository.save(user);
//     }

//     public Account authenticate(LoginUserDto input) {
//         authManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
//         );

//         // returns the User object if it successfully authenticates
//         return AccountRepository.findByEmail(input.getEmail())
//                 .orElseThrow();
//     }
// }
