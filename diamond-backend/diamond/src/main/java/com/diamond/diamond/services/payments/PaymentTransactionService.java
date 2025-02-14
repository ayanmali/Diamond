package com.diamond.diamond.services.payments;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.PaymentTransaction;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.types.PaymentStatus;

/*
 * Defining service methods for Checkout/Link Payment transactions
 */
@Service
public class PaymentTransactionService<T extends PaymentTransaction> {
    private final JpaRepository<T, UUID> txnRepository;

    public PaymentTransactionService(JpaRepository<T, UUID> txnRepository) {
        this.txnRepository = txnRepository;
    }

    public T newPayment(T txn) {
        return txnRepository.save(txn);
    }

    public T findTxnById(UUID id) {
        return txnRepository.findById(id).orElseThrow();
    }

    public T findTxnById(String id) {
        UUID uuidId = UUID.fromString(id);
        return this.findTxnById(uuidId);
    }

    public T updateStatus(UUID id, PaymentStatus status) {
        T txn = txnRepository.findById(id).orElseThrow();
        txn.setStatus(status);
        return txnRepository.save(txn);
    }

    public T updateCodesApplied(UUID id, List<PromoCode> codesApplied) {
        T txn = txnRepository.findById(id).orElseThrow();
        txn.setCodesApplied(codesApplied);
        return txnRepository.save(txn);
    }

    public void deleteTxnById(UUID id) {
        txnRepository.deleteById(id);
    }

    public void deleteTxnById(String id) {
        UUID uuidId = UUID.fromString(id);
        this.deleteTxnById(uuidId);
    }

    public void deleteTxn(T txn) {
        txnRepository.delete(txn);
    }

}
