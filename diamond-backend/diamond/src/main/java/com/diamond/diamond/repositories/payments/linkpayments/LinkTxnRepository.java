package com.diamond.diamond.repositories.payments.linkpayments;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.link_payments.LinkPaymentTransaction;

@Repository
public interface LinkTxnRepository extends JpaRepository<LinkPaymentTransaction, UUID> {
    
}
