package com.diamond.diamond.repositories.payments.checkoutpayments;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diamond.diamond.entities.payments.checkouts.CheckoutPaymentTransaction;

public interface CheckoutTxnRepository extends JpaRepository<CheckoutPaymentTransaction, UUID>{
    
}
