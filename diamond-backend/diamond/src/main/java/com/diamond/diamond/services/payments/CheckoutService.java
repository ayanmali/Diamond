package com.diamond.diamond.services.payments;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.CheckoutPayment;
import com.diamond.diamond.repositories.payments.CheckoutRepository;

@Service
public class CheckoutService {
    private final CheckoutRepository checkoutRepository;

    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public CheckoutPayment saveCheckoutPayment(CheckoutPayment checkoutPayment) {
        return checkoutRepository.save(checkoutPayment);
    }

    public Optional<CheckoutPayment> findCheckoutPaymentById(UUID id) {
        return checkoutRepository.findById(id);
    }

    public void deletePaymentById(UUID id) {
        checkoutRepository.deleteById(id);
    }

}