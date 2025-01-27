package com.diamond.diamond.repositories.payments;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diamond.diamond.entities.payments.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{
    
}
