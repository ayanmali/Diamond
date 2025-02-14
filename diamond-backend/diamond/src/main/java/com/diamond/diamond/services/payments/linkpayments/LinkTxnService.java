package com.diamond.diamond.services.payments.linkpayments;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.link_payments.LinkPaymentTransaction;
import com.diamond.diamond.repositories.payments.linkpayments.LinkTxnRepository;
import com.diamond.diamond.services.payments.PaymentTxnService;

@Service
public class LinkTxnService extends PaymentTxnService<LinkPaymentTransaction> {
    public LinkTxnService(LinkTxnRepository paymentRepository) {
        super(paymentRepository);
    }
}
