package com.diamond.diamond.entities.payments.link_payments;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.payments.PaymentTransaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/*
 * Defining a single transaction between a customer and a vendor's payment link
 */
@Entity
@Table(name="link_txns")
public class LinkPaymentTransaction extends PaymentTransaction {
    public LinkPaymentTransaction(LinkPayment payment, Customer customer, Double revenue) {
        super(payment, customer, revenue);
    }
}
