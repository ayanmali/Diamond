package com.diamond.diamond.services.payments;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.payments.fetch_payments.FetchPaymentDto;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.repositories.payments.PaymentRepository;
import com.diamond.diamond.types.StablecoinCurrency;

@Service
public abstract class PaymentService<T extends Payment> {
    protected final PaymentRepository<T> paymentRepository;
    
    public PaymentService(PaymentRepository<T> paymentRepository) {
        this.paymentRepository = paymentRepository;
    }  

    // public FetchPaymentDto convertPaymentToFetchDto(T payment) {
    //     FetchPaymentDto paymentDto = new FetchPaymentDto();
    //     paymentDto.setAmount(payment.getAmount());
    //     paymentDto.setChain(payment.getChain());
    //     paymentDto.setCreatedAt(payment.getCreatedAt());
    //     paymentDto.setCurrency(payment.getStablecoinCurrency());
    //     paymentDto.setId(payment.getId());

    //     return paymentDto;
    // }

    public abstract FetchPaymentDto convertPaymentToFetchDto(T payment);

    public T savePayment(T payment) {
        return paymentRepository.save(payment);
    }

    public T findPaymentById(UUID id) {
        return paymentRepository.findById(id).orElseThrow();
    }

    public FetchPaymentDto findPaymentDtoById(UUID id) {
        return convertPaymentToFetchDto(paymentRepository.findById(id).orElseThrow());
    }

    public T findPaymentById(String id) {
        UUID uuidId = UUID.fromString(id);
        return this.findPaymentById(uuidId);
    }

    public FetchPaymentDto findPaymentDtoById(String id) {
        UUID uuidId = UUID.fromString(id);
        return convertPaymentToFetchDto(this.findPaymentById(uuidId));
    }

    // find all valid promo codes for a given payment
    // public Set<PromoCode> findValidPromoCodes(UUID id) {

    // }

    // // find the list of VendorWallets associated with a payment
    // public List<VendorWallet> findWalletDistribution(UUID id) {

    // }

    public T updateAmount(UUID id, Double amount) {
        T payment = paymentRepository.findById(id).orElseThrow();
        payment.setAmount(amount);
        return paymentRepository.save(payment);
    }

    public T updateCurrency(UUID id, StablecoinCurrency currency) {
        T payment = paymentRepository.findById(id).orElseThrow();
        payment.setCurrency(currency);
        return paymentRepository.save(payment);
    }

    public T updateWalletDistribution(UUID id, List<VendorWallet> wallets) {
        T payment = paymentRepository.findById(id).orElseThrow();
        payment.setWalletDistribution(wallets);
        return paymentRepository.save(payment);
    }
    
    // public T updateCustomer(UUID id, Customer customer) {
    //     Optional<T> optionalPayment = paymentRepository.findById(id);
    //     if (optionalPayment.isPresent()) {
    //         T payment = optionalPayment.get();
    //         payment.setCustomer(customer);
    //         return paymentRepository.save(payment);
    //     }
    //     return null;
    // }

    public void deletePaymentById(UUID id) {
        paymentRepository.deleteById(id);
    }

    public void deletePaymentById(String id) {
        UUID uuidId = UUID.fromString(id);
        this.deletePaymentById(uuidId);
    }

    public void deletePayment(T payment) {
        paymentRepository.delete(payment);
    }

}
