package com.diamond.diamond.repositories.payments.checkoutpayments;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.checkouts.CheckoutPayment;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutPayment, UUID>{

}
