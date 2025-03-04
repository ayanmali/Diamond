package com.diamond.diamond.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.Account;
import com.diamond.diamond.entities.AccountWallet;
import com.diamond.diamond.entities.payments.Payment;



@Repository
public interface AccountWalletRepository extends JpaRepository<AccountWallet, Long> {

    Optional<AccountWallet> findByAddress(String address);
    List<AccountWallet> findByAccount(Account account);
    // Geting the AccountWallets associated with any number of Payments
    // For a single Payment, this query returns the wallet distribution for that Payment
    List<AccountWallet> findByPayments(List<Payment> payments);
    //List<AccountWallet> findByPaymentId();
    
}