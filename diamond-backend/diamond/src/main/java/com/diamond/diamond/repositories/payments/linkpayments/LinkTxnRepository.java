package com.diamond.diamond.repositories.payments.linkpayments;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.link_payments.LinkPaymentTxn;
import com.diamond.diamond.repositories.payments.PaymentTxnRepository;

@Repository
public interface LinkTxnRepository extends PaymentTxnRepository<LinkPaymentTxn> {
    @Override
    Optional<LinkPaymentTxn> findByTxHash(String txHash);
}
