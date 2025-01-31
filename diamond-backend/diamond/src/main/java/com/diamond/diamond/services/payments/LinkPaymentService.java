package com.diamond.diamond.services.payments;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.LinkPayment;
import com.diamond.diamond.repositories.payments.LinkPaymentRepository;

@Service
public class LinkPaymentService extends PaymentService<LinkPayment> {
    //private final LinkPaymentRepository linkPaymentRepository;

    public LinkPaymentService(LinkPaymentRepository linkPaymentRepository) {
        super(linkPaymentRepository);
        //this.linkPaymentRepository = linkPaymentRepository;
    }

}