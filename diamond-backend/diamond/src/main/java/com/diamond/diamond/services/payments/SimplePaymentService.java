package com.diamond.diamond.services.payments;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.payments.fetch_payments.FetchSimplePaymentDto;
import com.diamond.diamond.entities.payments.SimplePayment;
import com.diamond.diamond.repositories.payments.SimplePaymentRepository;


@Service
public class SimplePaymentService extends PaymentService<SimplePayment> {

    public SimplePaymentService(SimplePaymentRepository paymentRepository) {
        super(paymentRepository);
    }

    @Override
    public FetchSimplePaymentDto convertPaymentToFetchDto(SimplePayment simplePayment) {
        FetchSimplePaymentDto simplePaymentDto = new FetchSimplePaymentDto(simplePayment);

        // checkoutPaymentDto.setAmount(checkoutPayment.getAmount());
        // checkoutPaymentDto.setChain(checkoutPayment.getChain());
        // checkoutPaymentDto.setCreatedAt(checkoutPayment.getCreatedAt());
        // checkoutPaymentDto.setCurrency(checkoutPayment.getStablecoinCurrency());
        // checkoutPaymentDto.setId(checkoutPayment.getId());

        // simplePaymentDto.setHasMaxNumberOfPayments(simplePayment.getHasMaxNumberOfPayments());
        // simplePaymentDto.setMaxNumberOfPayments(simplePayment.getMaxNumberOfPayments());
        // simplePaymentDto.setEnablePromoCodes(simplePayment.getEnablePromoCodes());
        
        // if (simplePayment.getValidPromoCodes() != null && Hibernate.isInitialized(simplePayment.getValidPromoCodes())) {
        //     // Getting the IDs of the valid promo codes for this payment
        //     // List<Long> promoCodeIds = new ArrayList<>();
        //     // for (PromoCode promoCode : checkoutPayment.getValidPromoCodes()) {
        //     //     promoCodeIds.add(promoCode.getId());
        //     // }
        //     simplePaymentDto.setValidPromoCodeDtos(
        //         simplePayment.getValidPromoCodes().stream() // Convert the List<Customer> to a Stream<Customer>
        //         .map(PromoCodeService::convertPromoCodeToDto) // Map each Customer to FetchCustomerDto
        //         .collect(Collectors.toList())
        //     );
        // }

        return simplePaymentDto;
    }
    
    public SimplePayment updateHasMaxNumberOfPayments(UUID id, Boolean hasMaxNumberOfPayments) {
        SimplePayment payment = this.paymentRepository.findById(id).orElseThrow();
        payment.setHasMaxNumberOfPayments(hasMaxNumberOfPayments);
        return this.paymentRepository.save(payment);
    }

    public SimplePayment updateMaxNumberOfPayments(UUID id, Integer maxNumberOfPayments) {
        SimplePayment payment = this.paymentRepository.findById(id).orElseThrow();
        payment.setMaxNumberOfPayments(maxNumberOfPayments);
        return this.paymentRepository.save(payment);
    }

    /*
     * not to be used w/ flexible payments
     */
    // public SimplePayment updateEnablePromoCodes(UUID id, Boolean enablePromoCodes) {
    //     SimplePayment payment = this.paymentRepository.findById(id).orElseThrow();
    //     payment.setEnablePromoCodes(enablePromoCodes);
    //     return this.paymentRepository.save(payment);
    // }

    /*
     * not to be used w/ flexible payments
     */
    // public SimplePayment updatePromoCodes(UUID id, List<PromoCode> promoCodes) {
    //     SimplePayment payment = this.paymentRepository.findById(id).orElseThrow();
    //     payment.setPromoCodes(promoCodes);
    //     return this.paymentRepository.save(payment);
    // }

}
