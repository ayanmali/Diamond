package com.diamond.diamond.services.payments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.diamond.diamond.dtos.payments.txns.FetchPaymentTxnDto;
import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.repositories.payments.PaymentTxnRepository;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.PaymentStatus;
import com.diamond.diamond.types.StablecoinCurrency;

/*
 * Defining service methods for Checkout/Link Payment transactions
 */

public class PaymentTxnService<T extends PaymentTxn> {
    protected final PaymentTxnRepository<T> txnRepository;
    
    public PaymentTxnService(PaymentTxnRepository<T> txnRepository) {
        this.txnRepository = txnRepository;
    }

    public FetchPaymentTxnDto convertTxnToFetchDto(T txn) {
        FetchPaymentTxnDto txnDto = new FetchPaymentTxnDto();
        txnDto.setCustomerId(txn.getCustomer().getId());
        txnDto.setId(txn.getId());
        txnDto.setPaymentId(txn.getPayment().getId());
        txnDto.setRevenue(txn.getRevenue());
        txnDto.setSignHash(txn.getSignHash());
        txnDto.setStatus(txn.getStatus());
        txnDto.setTxHash(txn.getTxHash());
        
        if (txn.getCodesApplied() != null && Hibernate.isInitialized(txn.getCodesApplied())) {
            List<Long> codesAppliedIds = new ArrayList<>();
            for (PromoCode promoCode : txn.getCodesApplied()) {
                codesAppliedIds.add(promoCode.getId());
            }
            txnDto.setPromoCodesAppliedIds(codesAppliedIds);
        }

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

    public List<FetchPaymentTxnDto> findTxnDtosWithFilters(
        UUID id,
        UUID paymentId,
        UUID accountId,
        UUID customerId,
        StablecoinCurrency currency,
        Blockchain chain,
        Double revenueGreaterThan,
        Double revenueLessThan,
        PaymentStatus status,
        Date paidBefore,
        Date paidAfter,
        Integer pageSize
    ) {
        Pageable pageable = pageSize != null ? 
            PageRequest.of(0, pageSize) : 
            Pageable.unpaged();
        
        Page<T> paymentTxns = txnRepository.findTxnsWithFilters(
            id,
            paymentId,
            accountId,
            customerId,
            currency,
            chain,
            revenueGreaterThan,
            revenueLessThan,
            status,
            paidBefore,
            paidAfter,
            pageable
        );

        return paymentTxns.getContent()
            .stream()
            .map(this::convertTxnToFetchDto)
            .collect(Collectors.toList());

    }

    public FetchPaymentTxnDto findTxnDtoById(UUID id) {
        T txn = txnRepository.findById(id).orElseThrow();
        return convertTxnToFetchDto(txn);
    }

    public PaymentTxn findTxnById(UUID id) {
        return txnRepository.findById(id).orElseThrow();
    }

    public FetchPaymentTxnDto findTxnDtoById(String id) {
        UUID uuidId = UUID.fromString(id);
        return this.findTxnDtoById(uuidId);
    }

    public PaymentTxn findTxnById(String id) {
        UUID uuidId = UUID.fromString(id);
        return this.findTxnById(uuidId);
    }

    public FetchPaymentTxnDto findTxnDtoByTxHash(String txHash) {
        return convertTxnToFetchDto(txnRepository.findByTxHash(txHash).orElseThrow());
    }

    public PaymentTxn findTxnByTxHash(String txHash) {
        return txnRepository.findByTxHash(txHash).orElseThrow();
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
