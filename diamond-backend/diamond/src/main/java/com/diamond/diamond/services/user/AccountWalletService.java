package com.diamond.diamond.services.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.wallets.FetchAccountWalletDto;
import com.diamond.diamond.dtos.wallets.NewAccountWalletDto;
import com.diamond.diamond.entities.user.Account;
import com.diamond.diamond.entities.user.AccountWallet;
import com.diamond.diamond.repositories.user.AccountWalletRepository;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.WalletStatus;

@Service
public class AccountWalletService {
    private final AccountWalletRepository accountWalletRepository;

    public AccountWalletService(AccountWalletRepository accountWalletRepository) {
        this.accountWalletRepository = accountWalletRepository;
    }

    // public static FetchAccountWalletDto convertAccountWalletToFetchDto(AccountWallet accountWallet) {
    //     FetchAccountWalletDto walletDto = new FetchAccountWalletDto();
    //     walletDto.setAddress(accountWallet.getAddress());
    //     walletDto.setChain(accountWallet.getChain());
    //     walletDto.setCreatedAt(accountWallet.getCreatedAt());
    //     walletDto.setId(accountWallet.getId());
    //     walletDto.setStatus(accountWallet.getStatus());
    //     walletDto.setUpdatedAt(accountWallet.getUpdatedAt());
    //     walletDto.setAccountId(accountWallet.getAccount().getId());
    //     walletDto.setWalletName(accountWallet.getWalletName());
    //     return walletDto;
    // }

    public FetchAccountWalletDto saveWallet(NewAccountWalletDto walletDto, UUID accountId, String walletAddress, String encryptedPrivateKey) {
        AccountWallet accountWallet = new AccountWallet(
                                        walletAddress,
                                        encryptedPrivateKey,
                                        walletDto.getWalletName(),
                                        accountId,
                                        walletDto.getChain());

        return new FetchAccountWalletDto(accountWalletRepository.save(accountWallet));
    }

    public List<FetchAccountWalletDto> findWalletDtosWithFilters(
        UUID accountId,
        UUID walletId,
        Blockchain chain,
        WalletStatus status,
        Date createdBefore,
        Date createdAfter,
        Integer pageSize
    ) {

       // Creating pageable object from the given page size 
        Pageable pageable = pageSize != null ? 
            PageRequest.of(0, pageSize) : 
            Pageable.unpaged();

        // calling the repository to query the DB
        Page<AccountWallet> accountWallets = accountWalletRepository.findAccountsWithFilters(
            walletId, 
            accountId, 
            chain,
            status,
            createdBefore, 
            createdAfter, 
            pageable
        );

        // returning the list of wallets in the appropriate format
        return accountWallets.getContent()
            .stream()
            .map(FetchAccountWalletDto::new)
            .collect(Collectors.toList());
    }

    public FetchAccountWalletDto findWalletDtoById(UUID id) {
        return new FetchAccountWalletDto(accountWalletRepository.findById(id).orElseThrow());
    }

    public AccountWallet findWalletById(UUID id) {
        return accountWalletRepository.findById(id).orElseThrow();
    }

    public FetchAccountWalletDto findWalletDtoByAddress(String address) {
        return new FetchAccountWalletDto(accountWalletRepository.findByAddress(address).orElseThrow());
    }

    public AccountWallet findWalletByAddress(String address) {
        return accountWalletRepository.findByAddress(address).orElseThrow();
    }

    public List<FetchAccountWalletDto> findWalletDtosByAccount(Account account) {
        // List<AccountWallet> accountWallets = accountWalletRepository.findByAccount(account);
        //List<AccountWallet> accountWallets = accountWalletRepository.findAll();
        return accountWalletRepository.findByAccountId(account.getId()).stream() // Convert the List<AccountWallet> to a Stream<AccountWallet>
            .map(FetchAccountWalletDto::new) // Map each AccountWallet to FetchAccountWalletDto
            .collect(Collectors.toList()); // Collect the results into a List<FetchAccountWalletDto>
    }

    public List<AccountWallet> findWalletsByAccount(Account account) {
        return accountWalletRepository.findByAccountId(account.getId());
    }

    // public List<AccountWallet> findWalletsByPayment(Payment payment) {
    //     return accountWalletRepository.findByPaymentIds(List.of(payment.getId()));
    // }

    // public List<FetchAccountWalletDto> findWalletDtosByPayment(Payment payment) {
    //     return accountWalletRepository.findByPaymentIds(
    //         List.of(payment.getId())
    //     )
    //     .stream()
    //     .map(FetchAccountWalletDto::new)
    //     .collect(Collectors.toList());
    // }

    // public List<AccountWallet> findWalletsByPayments(List<Payment> payments) {
    //     return accountWalletRepository.findByPaymentIds(payments.stream().map(Payment::getId).collect(Collectors.toList()));
    // }

    // public List<FetchAccountWalletDto> findWalletDtosByPayments(List<Payment> payments) {
    //     return accountWalletRepository.findByPaymentIds(
    //         payments.stream()
    //         .map(Payment::getId)
    //         .collect(Collectors.toList()))
    //     .stream() // Convert the List<AccountWallet> to a Stream<AccountWallet>
    //     .map(FetchAccountWalletDto::new) // Map each AccountWallet to FetchAccountWalletDto
    //     .collect(Collectors.toList()); // Collect the results into a List<FetchAccountWalletDto>
    // }

    public FetchAccountWalletDto updateWalletName(UUID id, String name) {
        AccountWallet accountWallet = accountWalletRepository.findById(id).orElseThrow();
        accountWallet.setName(name);
        return new FetchAccountWalletDto(accountWalletRepository.save(accountWallet));
    }

    public FetchAccountWalletDto archiveWallet(UUID id) {
        AccountWallet accountWallet = accountWalletRepository.findById(id).orElseThrow();
        accountWallet.setStatus(WalletStatus.ARCHIVED);
        return new FetchAccountWalletDto(accountWalletRepository.save(accountWallet));
    }

    public FetchAccountWalletDto reactivateWallet(UUID id) {
        AccountWallet accountWallet = accountWalletRepository.findById(id).orElseThrow();
        accountWallet.setStatus(WalletStatus.ACTIVE);
        return new FetchAccountWalletDto(accountWalletRepository.save(accountWallet));
    }

    public void deleteWalletById(UUID id) {
        accountWalletRepository.deleteById(id);
    }

    public void deleteWallet(AccountWallet wallet) {
        accountWalletRepository.delete(wallet);
    }

}