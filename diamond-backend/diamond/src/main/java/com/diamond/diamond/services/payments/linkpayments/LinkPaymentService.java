package com.diamond.diamond.services.payments.linkpayments;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.entities.payments.link_payments.LinkPayment;
import com.diamond.diamond.repositories.payments.linkpayments.LinkPaymentRepository;
import com.diamond.diamond.services.payments.PaymentService;

@Service
public class LinkPaymentService extends PaymentService<LinkPayment> {
    //private final LinkPaymentRepository linkPaymentRepository;

    public LinkPaymentService(LinkPaymentRepository linkPaymentRepository) {
        super(linkPaymentRepository);
        //this.linkPaymentRepository = linkPaymentRepository;
    }

    public LinkPayment updateHasMaxNumberOfPayments(UUID id, Boolean hasMaxNumberOfPayments) {
        LinkPayment payment = this.paymentRepository.findById(id).orElseThrow();
        payment.setHasMaxNumberOfPayments(hasMaxNumberOfPayments);
        return this.paymentRepository.save(payment);
    }

    public LinkPayment updateMaxNumberOfPayments(UUID id, Integer maxNumberOfPayments) {
        LinkPayment payment = this.paymentRepository.findById(id).orElseThrow();
        payment.setMaxNumberOfPayments(maxNumberOfPayments);
        return this.paymentRepository.save(payment);
    }

    public LinkPayment updateEnablePromoCodes(UUID id, Boolean enablePromoCodes) {
        LinkPayment payment = this.paymentRepository.findById(id).orElseThrow();
        payment.setEnablePromoCodes(enablePromoCodes);
        return this.paymentRepository.save(payment);
    }

    public LinkPayment updatePromoCodes(UUID id, List<PromoCode> promoCodes) {
        LinkPayment payment = this.paymentRepository.findById(id).orElseThrow();
        payment.setPromoCodes(promoCodes);
        return this.paymentRepository.save(payment);
    }


}