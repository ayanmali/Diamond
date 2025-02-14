package com.diamond.diamond.repositories.payments;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.PaymentTransaction;

@Repository
public interface PaymentTxnRepository<T extends PaymentTransaction> extends JpaRepository<T, UUID> {
    Optional<T> findByTxHash(String txHash);
}
