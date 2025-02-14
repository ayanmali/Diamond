package com.diamond.diamond.services.payments;

import org.springframework.stereotype.Service;

import com.diamond.diamond.repositories.payments.PaymentTransactionRepository;

@Service
public class PaymentTransactionService {
    private final PaymentTransactionRepository txnRepository;

    public PaymentTransactionService(PaymentTransactionRepository txnRepository) {
        this.txnRepository = txnRepository;
    }

}
