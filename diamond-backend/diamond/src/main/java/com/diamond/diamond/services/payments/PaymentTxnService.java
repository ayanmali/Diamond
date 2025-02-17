package com.diamond.diamond.services.payments;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.payments.txns.FetchPaymentTxnDto;
import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.repositories.payments.PaymentTxnRepository;
import com.diamond.diamond.types.PaymentStatus;

/*
 * Defining service methods for Checkout/Link Payment transactions
 */
@Service
public class PaymentTxnService<T extends PaymentTxn> {
    protected final PaymentTxnRepository<T> txnRepository;
    
    public PaymentTxnService(PaymentTxnRepository<T> txnRepository) {
        this.txnRepository = txnRepository;
    }

    public FetchPaymentTxnDto convertTxnToFetchDto(T txn) {
        List<Long> codesAppliedIds = new ArrayList<>();
        for (PromoCode promoCode : txn.getCodesApplied()) {
            codesAppliedIds.add(promoCode.getId());
        }

        FetchPaymentTxnDto txnDto = new FetchPaymentTxnDto();
        txnDto.setCustomerId(txn.getCustomer().getId());
        txnDto.setId(txn.getId());
        txnDto.setPaymentId(txn.getPayment().getId());
        txnDto.setPromoCodesAppliedIds(codesAppliedIds);
        txnDto.setRevenue(txn.getRevenue());
        txnDto.setSignHash(txn.getSignHash());
        txnDto.setStatus(txn.getStatus());
        txnDto.setTxHash(txn.getTxHash());
        return txnDto;
    }

    // public static <T extends PaymentTransaction> T createInstance(Class<T> clazz, Payment payment, Customer customer, Double revenue) {
    //     try {
    //         T instance = clazz.getDeclaredConstructor(Payment.class, Customer.class, Double.class).newInstance(payment, customer, revenue);
    //         return instance;
    //     } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
    //         throw new RuntimeException("Failed to create PaymentTransaction instance", e);
    //     }
    // }

    public FetchPaymentTxnDto savePaymentTxn(T txn) {
        return convertTxnToFetchDto(txnRepository.save(txn));
    }

    // public T newPayment(NewPaymentTxnDto txnDto, Payment payment, Customer customer) {
    //     T txn = PaymentTransaction.createInstance((Class<T>) this.getClass(), payment, customer, txnDto.getRevenue());
    //     return txnRepository.save(txn);
    // }

    public FetchPaymentTxnDto findTxnById(UUID id) {
        return convertTxnToFetchDto(txnRepository.findById(id).orElseThrow());
    }

    public FetchPaymentTxnDto findTxnById(String id) {
        UUID uuidId = UUID.fromString(id);
        return this.findTxnById(uuidId);
    }

    public FetchPaymentTxnDto findTxnByTxHash(String txHash) {
        return convertTxnToFetchDto(txnRepository.findByTxHash(txHash).orElseThrow());
    }

    public FetchPaymentTxnDto updateStatus(UUID id, PaymentStatus status) {
        T txn = txnRepository.findById(id).orElseThrow();
        txn.setStatus(status);
        return convertTxnToFetchDto(txnRepository.save(txn));
    }

    public FetchPaymentTxnDto updateCodesApplied(UUID id, List<PromoCode> codesApplied) {
        T txn = txnRepository.findById(id).orElseThrow();
        txn.setCodesApplied(codesApplied);
        return convertTxnToFetchDto(txnRepository.save(txn));
    }

    public void deleteTxnById(UUID id) {
        txnRepository.deleteById(id);
    }

    public void deleteTxnById(String id) {
        UUID uuidId = UUID.fromString(id);
        this.deleteTxnById(uuidId);
    }

}
