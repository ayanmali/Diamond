package com.diamond.diamond.services.payments;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.Payment;

@Service
public class PaymentService<T extends Payment> {
    private final JpaRepository<T, UUID> paymentRepository;
    
    //private final VendorService vendorService;

    public PaymentService(JpaRepository<T, UUID> paymentRepository) {
        this.paymentRepository = paymentRepository;
        //this.vendorService = vendorService;
    }

    public T newPayment(T payment) {
        return paymentRepository.save(payment);
    }

    /*
     * Used by Controllers for Payment subclasses to easily
     * initialize values that are common to all Payment objects
     */
    // public T initializePaymentObject(Map<String, String> map) {
        
    // }

    public T findPaymentById(UUID id) {
        return paymentRepository.findById(id).orElseThrow();
    }

    public T findPaymentById(String id) {
        UUID uuidId = UUID.fromString(id);
        return this.findPaymentById(uuidId);
    }

    public T updatePaymentAmount(UUID id, Double amount) {
        T payment = paymentRepository.findById(id).orElseThrow();
        payment.setAmount(amount);
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
