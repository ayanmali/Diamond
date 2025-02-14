package com.diamond.diamond.repositories.payments.linkpayments;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.link_payments.LinkPayment;

@Repository
public interface LinkPaymentRepository extends JpaRepository<LinkPayment, UUID>{
    
}
