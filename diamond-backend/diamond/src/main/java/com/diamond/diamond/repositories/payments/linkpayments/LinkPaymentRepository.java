package com.diamond.diamond.repositories.payments.linkpayments;

import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.link_payments.LinkPayment;
import com.diamond.diamond.repositories.payments.PaymentRepository;

@Repository
public interface LinkPaymentRepository extends PaymentRepository<LinkPayment>{
    
}
