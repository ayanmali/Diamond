package com.diamond.diamond.repositories.payments;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.Payment;

@Repository
public interface PaymentRepository<T extends Payment> extends JpaRepository<T, UUID>{
    
}
