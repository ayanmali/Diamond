package com.diamond.diamond.entities.payments.checkouts;

import java.util.Set;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.entities.payments.PromoCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/*
 * Defines a transaction between a customer and vendor for a checkout payment
 */
@Entity
@Table(name="checkout_txns")
public class CheckoutPaymentTxn extends PaymentTxn {

    public CheckoutPaymentTxn() {}
    public CheckoutPaymentTxn(CheckoutPayment payment, Customer customer, Double revenue, Set<PromoCode> codesApplied) {
        super(payment, customer, revenue, codesApplied);
    }
    
}
