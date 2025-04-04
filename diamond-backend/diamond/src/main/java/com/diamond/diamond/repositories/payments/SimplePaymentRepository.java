package com.diamond.diamond.repositories.payments;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.SimplePayment;

@Repository
@Primary
public interface SimplePaymentRepository extends PaymentRepository<SimplePayment> {
    // TODO: filter for simple payments
}
