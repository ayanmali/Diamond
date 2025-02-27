package com.diamond.diamond.services.payments.linkpayments;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.payments.fetch_payments.FetchLinkPaymentDto;
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

    @Override
    public FetchLinkPaymentDto convertPaymentToFetchDto(LinkPayment linkPayment) {
        // TODO Auto-generated method stub
        //LinkPayment linkPayment = (LinkPayment) payment;
        // FetchLinkPaymentDto linkPaymentDto = (FetchLinkPaymentDto) super.convertPaymentToFetchDto(linkPayment);

        //FetchPaymentDto parent = super.convertPaymentToFetchDto(linkPayment);
        FetchLinkPaymentDto linkPaymentDto = new FetchLinkPaymentDto(linkPayment);

        // linkPaymentDto.setAmount(linkPayment.getAmount());
        // linkPaymentDto.setChain(linkPayment.getChain());
        // linkPaymentDto.setCreatedAt(linkPayment.getCreatedAt());
        // linkPaymentDto.setCurrency(linkPayment.getStablecoinCurrency());
        // linkPaymentDto.setId(linkPayment.getId());

        linkPaymentDto.setHasMaxNumberOfPayments(linkPayment.getHasMaxNumberOfPayments());
        linkPaymentDto.setMaxNumberOfPayments(linkPayment.getMaxNumberOfPayments());
        linkPaymentDto.setEnablePromoCodes(linkPayment.getEnablePromoCodes());
        
        if (linkPayment.getValidPromoCodes() != null && Hibernate.isInitialized(linkPayment.getValidPromoCodes())) {
            // Getting the IDs of the valid promo codes for this payment
            List<Long> promoCodeIds = new ArrayList<>();
            for (PromoCode promoCode : linkPayment.getValidPromoCodes()) {
                promoCodeIds.add(promoCode.getId());
            }

            linkPaymentDto.setValidPromoCodeIds(promoCodeIds);
        }

        return linkPaymentDto;
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