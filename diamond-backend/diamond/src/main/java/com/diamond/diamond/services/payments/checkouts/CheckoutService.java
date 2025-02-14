package com.diamond.diamond.services.payments.checkouts;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.entities.payments.checkouts.CheckoutPayment;
import com.diamond.diamond.repositories.payments.checkoutpayments.CheckoutRepository;
import com.diamond.diamond.services.payments.PaymentService;

@Service
public class CheckoutService extends PaymentService<CheckoutPayment> {

    public CheckoutService(CheckoutRepository checkoutRepository) {
        super(checkoutRepository);
    }
    
    public CheckoutPayment updateHasMaxNumberOfPayments(UUID id, Boolean hasMaxNumberOfPayments) {
        CheckoutPayment payment = this.paymentRepository.findById(id).orElseThrow();
        payment.setHasMaxNumberOfPayments(hasMaxNumberOfPayments);
        return this.paymentRepository.save(payment);
    }

    public CheckoutPayment updateMaxNumberOfPayments(UUID id, Integer maxNumberOfPayments) {
        CheckoutPayment payment = this.paymentRepository.findById(id).orElseThrow();
        payment.setMaxNumberOfPayments(maxNumberOfPayments);
        return this.paymentRepository.save(payment);
    }

    public CheckoutPayment updateEnablePromoCodes(UUID id, Boolean enablePromoCodes) {
        CheckoutPayment payment = this.paymentRepository.findById(id).orElseThrow();
        payment.setEnablePromoCodes(enablePromoCodes);
        return this.paymentRepository.save(payment);
    }

    public CheckoutPayment updatePromoCodes(UUID id, List<PromoCode> promoCodes) {
        CheckoutPayment payment = this.paymentRepository.findById(id).orElseThrow();
        payment.setPromoCodes(promoCodes);
        return this.paymentRepository.save(payment);
    }

}