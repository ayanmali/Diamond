package com.diamond.diamond.services.payments;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.payments.Payment;

public class PaymentService<T extends Payment> {
    private final JpaRepository<T, UUID> paymentRepository;

    public PaymentService(JpaRepository<T, UUID> paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public T savePayment(T payment) {
        return paymentRepository.save(payment);
    }

    public Optional<T> findPaymentById(UUID id) {
        return paymentRepository.findById(id);
    }

    public T updatePaymentAmount(UUID id, BigDecimal amount) {
        Optional<T> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            T payment = optionalPayment.get();
            payment.setAmount(amount);
            return paymentRepository.save(payment);
        }
        return null;
    }
    
    public T updateCustomer(UUID id, Customer customer) {
        Optional<T> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            T payment = optionalPayment.get();
            payment.setCustomer(customer);
            return paymentRepository.save(payment);
        }
        return null;
    }

    public void deletePaymentById(UUID id) {
        paymentRepository.deleteById(id);
    }

}
