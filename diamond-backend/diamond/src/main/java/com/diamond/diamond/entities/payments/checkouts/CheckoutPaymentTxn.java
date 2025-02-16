package com.diamond.diamond.entities.payments.checkouts;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.payments.PaymentTxn;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/*
 * Defines a transaction between a customer and vendor for a checkout payment
 */
@Entity
@Table(name="checkout_txns")
public class CheckoutPaymentTxn extends PaymentTxn {
    public CheckoutPaymentTxn(CheckoutPayment payment, Customer customer, Double revenue) {
        super(payment, customer, revenue);
    }
    
}
