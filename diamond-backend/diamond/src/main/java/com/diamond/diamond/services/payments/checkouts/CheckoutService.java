package com.diamond.diamond.services.payments.checkouts;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.payments.fetch_payments.FetchCheckoutPaymentDto;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.entities.payments.checkouts.CheckoutPayment;
import com.diamond.diamond.repositories.payments.checkoutpayments.CheckoutRepository;
import com.diamond.diamond.services.payments.PaymentService;

@Service
public class CheckoutService extends PaymentService<CheckoutPayment> {

    public CheckoutService(CheckoutRepository checkoutRepository) {
        super(checkoutRepository);
    }

    @Override
    public FetchCheckoutPaymentDto convertPaymentToFetchDto(CheckoutPayment checkoutPayment) {
        // TODO Auto-generated method stub
        FetchCheckoutPaymentDto checkoutPaymentDto = new FetchCheckoutPaymentDto(checkoutPayment);

        // checkoutPaymentDto.setAmount(checkoutPayment.getAmount());
        // checkoutPaymentDto.setChain(checkoutPayment.getChain());
        // checkoutPaymentDto.setCreatedAt(checkoutPayment.getCreatedAt());
        // checkoutPaymentDto.setCurrency(checkoutPayment.getStablecoinCurrency());
        // checkoutPaymentDto.setId(checkoutPayment.getId());

        checkoutPaymentDto.setHasMaxNumberOfPayments(checkoutPayment.getHasMaxNumberOfPayments());
        checkoutPaymentDto.setMaxNumberOfPayments(checkoutPayment.getMaxNumberOfPayments());
        checkoutPaymentDto.setEnablePromoCodes(checkoutPayment.getEnablePromoCodes());
        
        if (checkoutPayment.getValidPromoCodes() != null && Hibernate.isInitialized(checkoutPayment.getValidPromoCodes())) {
            // Getting the IDs of the valid promo codes for this payment
            List<Long> promoCodeIds = new ArrayList<>();
            for (PromoCode promoCode : checkoutPayment.getValidPromoCodes()) {
                promoCodeIds.add(promoCode.getId());
            }
            checkoutPaymentDto.setValidPromoCodeIds(promoCodeIds);
        }

        return checkoutPaymentDto;
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