package com.diamond.diamond.repositories.payments;

import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.SimplePayment;

@Repository
public interface SimplePaymentRepository extends PaymentRepository<SimplePayment> {
    
}
