package com.diamond.diamond.services.payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.diamond.diamond.dtos.payments.txns.FetchPaymentTxnDto;
import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.repositories.payments.PaymentTxnRepository;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.PaymentStatus;
import com.diamond.diamond.types.StablecoinCurrency;

/*
 * Defining service methods for Checkout/Link Payment transactions
 */

public class PaymentTxnService {
    protected final PaymentTxnRepository txnRepository;
    
    public PaymentTxnService(PaymentTxnRepository txnRepository) {
        this.txnRepository = txnRepository;
    }

    // public FetchPaymentTxnDto convertTxnToFetchDto(PaymentTxn txn) {
    //     FetchPaymentTxnDto txnDto = new FetchPaymentTxnDto();
    //     txnDto.setCustomerId(txn.getCustomer().getId());
    //     txnDto.setId(txn.getId());
    //     txnDto.setPaymentId(txn.getPayment().getId());
    //     txnDto.setRevenue(txn.getRevenue());
    //     txnDto.setSignHash(txn.getSignHash());
    //     txnDto.setStatus(txn.getStatus());
    //     txnDto.setTxHash(txn.getTxHash());
    //     txnDto.setTimePaid(txn.getTimePaid());
    //     txnDto.setCurrencyUsed(txn.getCurrencyUsed());
        
    //     if (txn.getCodesApplied() != null && Hibernate.isInitialized(txn.getCodesApplied())) {
    //         List<Long> codesAppliedIds = new ArrayList<>();
    //         for (PromoCode promoCode : txn.getCodesApplied()) {
    //             codesAppliedIds.add(promoCode.getId());
    //         }
    //         txnDto.setPromoCodesAppliedIds(codesAppliedIds);
    //     }

    //     return txnDto;
    // }

    // public static <T extends PaymentTransaction> T createInstance(Class<T> clazz, Payment payment, Customer customer, BigDecimal revenue) {
    //     try {
    //         T instance = clazz.getDeclaredConstructor(Payment.class, Customer.class, BigDecimal.class).newInstance(payment, customer, revenue);
    //         return instance;
    //     } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
    //         throw new RuntimeException("Failed to create PaymentTransaction instance", e);
    //     }
    // }

    public FetchPaymentTxnDto savePaymentTxn(PaymentTxn txn) {
        return new FetchPaymentTxnDto(txnRepository.save(txn));
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
        BigDecimal revenueGreaterThan,
        BigDecimal revenueLessThan,
        PaymentStatus status,
        Date paidBefore,
        Date paidAfter,
        Integer pageSize
    ) {
        Pageable pageable = pageSize != null ? 
            PageRequest.of(0, pageSize) : 
            Pageable.unpaged();
        
        Page<PaymentTxn> paymentTxns = txnRepository.findTxnsWithFilters(
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
            .map(FetchPaymentTxnDto::new)
            .collect(Collectors.toList());

    }

    public FetchPaymentTxnDto findTxnDtoById(UUID id) {
        return new FetchPaymentTxnDto(txnRepository.findById(id).orElseThrow());
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
        return new FetchPaymentTxnDto(txnRepository.findByTxHash(txHash).orElseThrow());
    }

    public PaymentTxn findTxnByTxHash(String txHash) {
        return txnRepository.findByTxHash(txHash).orElseThrow();
    }

    public PaymentTxn updateStatus(UUID id, PaymentStatus status) {
        PaymentTxn txn = txnRepository.findById(id).orElseThrow();
        txn.setStatus(status);
        return txnRepository.save(txn);
    }

    // public PaymentTxn updateCodesApplied(UUID id, List<PromoCode> codesApplied) {
    //     PaymentTxn txn = txnRepository.findById(id).orElseThrow();
    //     txn.setCodesApplied(codesApplied);
    //     return txnRepository.save(txn);
    // }

    public void deleteTxnById(UUID id) {
        txnRepository.deleteById(id);
    }

    public void deleteTxnById(String id) {
        UUID uuidId = UUID.fromString(id);
        this.deleteTxnById(uuidId);
    }

}