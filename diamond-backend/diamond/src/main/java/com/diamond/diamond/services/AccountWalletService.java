package com.diamond.diamond.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.wallets.FetchAccountWalletDto;
import com.diamond.diamond.dtos.wallets.NewAccountWalletDto;
import com.diamond.diamond.entities.Account;
import com.diamond.diamond.entities.AccountWallet;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.repositories.AccountWalletRepository;
import com.diamond.diamond.types.WalletStatus;

@Service
public class AccountWalletService {
    private final AccountWalletRepository accountWalletRepository;

    public AccountWalletService(AccountWalletRepository accountWalletRepository) {
        this.accountWalletRepository = accountWalletRepository;
    }

    public static FetchAccountWalletDto convertAccountWalletToFetchDto(AccountWallet accountWallet) {
        FetchAccountWalletDto walletDto = new FetchAccountWalletDto();
        walletDto.setAddress(accountWallet.getAddress());
        walletDto.setChain(accountWallet.getChain());
        walletDto.setCreatedAt(accountWallet.getCreatedAt());
        walletDto.setId(accountWallet.getId());
        walletDto.setStatus(accountWallet.getStatus());
        walletDto.setUpdatedAt(accountWallet.getUpdatedAt());
        walletDto.setAccountId(accountWallet.getAccount().getId());
        walletDto.setWalletName(accountWallet.getWalletName());
        return walletDto;
    }

    public FetchAccountWalletDto saveWallet(NewAccountWalletDto walletDto, Account account, String address, UUID id) {
        AccountWallet accountWallet = new AccountWallet(
                                        address,
                                        walletDto.getWalletName(),
                                        account,
                                        walletDto.getChain(),
                                        id);
        accountWallet.setStatus(WalletStatus.ACTIVE);

        return convertAccountWalletToFetchDto(accountWalletRepository.save(accountWallet));
    }

    public FetchAccountWalletDto findWalletDtoById(Long id) {
        return convertAccountWalletToFetchDto(accountWalletRepository.findById(id).orElseThrow());
    }

    public AccountWallet findWalletById(Long id) {
        return accountWalletRepository.findById(id).orElseThrow();
    }

    public FetchAccountWalletDto findWalletDtoByAddress(String address) {
        return convertAccountWalletToFetchDto(accountWalletRepository.findByAddress(address).orElseThrow());
    }

    public AccountWallet findWalletByAddress(String address) {
        return accountWalletRepository.findByAddress(address).orElseThrow();
    }

    public List<FetchAccountWalletDto> findWalletDtosByAccount(Account account) {
        // List<AccountWallet> accountWallets = accountWalletRepository.findByAccount(account);
        //List<AccountWallet> accountWallets = accountWalletRepository.findAll();
        return accountWalletRepository.findByAccount(account).stream() // Convert the List<AccountWallet> to a Stream<AccountWallet>
            .map(AccountWalletService::convertAccountWalletToFetchDto) // Map each AccountWallet to FetchAccountWalletDto
            .collect(Collectors.toList()); // Collect the results into a List<FetchAccountWalletDto>
    }

    public List<AccountWallet> findWalletsByAccount(Account account) {
        return accountWalletRepository.findByAccount(account);
    }

    public List<AccountWallet> findWalletsByPayment(Payment payment) {
        return accountWalletRepository.findByPayments(List.of(payment));
    }

    public List<FetchAccountWalletDto> findWalletDtosByPayment(Payment payment) {
        return accountWalletRepository.findByPayments(List.of(payment)).stream() // Convert the List<AccountWallet> to a Stream<AccountWallet>
        .map(AccountWalletService::convertAccountWalletToFetchDto) // Map each AccountWallet to FetchAccountWalletDto
        .collect(Collectors.toList()); // Collect the results into a List<FetchAccountWalletDto>
    }

    public List<AccountWallet> findWalletsByPayments(List<Payment> payments) {
        return accountWalletRepository.findByPayments(payments);
    }

    public List<FetchAccountWalletDto> findWalletDtosByPayments(List<Payment> payments) {
        return accountWalletRepository.findByPayments(payments).stream() // Convert the List<AccountWallet> to a Stream<AccountWallet>
        .map(AccountWalletService::convertAccountWalletToFetchDto) // Map each AccountWallet to FetchAccountWalletDto
        .collect(Collectors.toList()); // Collect the results into a List<FetchAccountWalletDto>
    }

    public FetchAccountWalletDto updateWalletName(Long id, String name) {
        AccountWallet accountWallet = accountWalletRepository.findById(id).orElseThrow();
        accountWallet.setName(name);
        return convertAccountWalletToFetchDto(accountWalletRepository.save(accountWallet));
    }

    public FetchAccountWalletDto archiveWallet(Long id) {
        AccountWallet accountWallet = accountWalletRepository.findById(id).orElseThrow();
        accountWallet.setStatus(WalletStatus.ARCHIVED);
        return convertAccountWalletToFetchDto(accountWalletRepository.save(accountWallet));
    }

    public FetchAccountWalletDto reactivateWallet(Long id) {
        AccountWallet accountWallet = accountWalletRepository.findById(id).orElseThrow();
        accountWallet.setStatus(WalletStatus.ACTIVE);
        return convertAccountWalletToFetchDto(accountWalletRepository.save(accountWallet));
    }

    public void deleteWalletById(Long id) {
        accountWalletRepository.deleteById(id);
    }

    public void deleteWallet(AccountWallet wallet) {
        accountWalletRepository.delete(wallet);
    }

}