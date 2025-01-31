package com.diamond.diamond.services.payments;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.CheckoutPayment;
import com.diamond.diamond.repositories.payments.CheckoutRepository;

@Service
public class CheckoutService extends PaymentService<CheckoutPayment> {

    public CheckoutService(CheckoutRepository checkoutRepository) {
        super(checkoutRepository);
    }

}