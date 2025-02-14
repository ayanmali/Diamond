package com.diamond.diamond.repositories.payments.checkoutpayments;

import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.checkouts.CheckoutPayment;
import com.diamond.diamond.repositories.payments.PaymentRepository;

@Repository
public interface CheckoutRepository extends PaymentRepository<CheckoutPayment>{

}
