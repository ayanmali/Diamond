package com.diamond.diamond.services.payments.checkouts;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.checkouts.CheckoutPaymentTransaction;
import com.diamond.diamond.repositories.payments.checkoutpayments.CheckoutTxnRepository;
import com.diamond.diamond.services.payments.PaymentTxnService;

@Service
public class CheckoutTxnService extends PaymentTxnService<CheckoutPaymentTransaction> {
    public CheckoutTxnService(CheckoutTxnRepository paymentRepository) {
        super(paymentRepository);
    }
}
