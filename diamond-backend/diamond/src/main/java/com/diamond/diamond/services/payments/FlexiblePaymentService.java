package com.diamond.diamond.services.payments;

import org.springframework.stereotype.Service;

import com.diamond.diamond.repositories.payments.FlexiblePaymentRepository;

@Service
public class FlexiblePaymentService extends SimplePaymentService {
    public FlexiblePaymentService(FlexiblePaymentRepository paymentRepository) {
        super(paymentRepository);
    }
}