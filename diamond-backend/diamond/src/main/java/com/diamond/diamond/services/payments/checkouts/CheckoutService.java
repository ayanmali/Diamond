package com.diamond.diamond.services.payments.checkouts;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.checkouts.CheckoutPayment;
import com.diamond.diamond.repositories.payments.checkoutpayments.CheckoutRepository;
import com.diamond.diamond.services.payments.PaymentService;

@Service
public class CheckoutService extends PaymentService<CheckoutPayment> {

    public CheckoutService(CheckoutRepository checkoutRepository) {
        super(checkoutRepository);
    }

}