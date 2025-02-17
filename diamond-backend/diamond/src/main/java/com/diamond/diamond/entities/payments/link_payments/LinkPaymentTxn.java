package com.diamond.diamond.entities.payments.link_payments;

import java.util.List;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.entities.payments.PromoCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/*
 * Defining a single transaction between a customer and a vendor's payment link
 */
@Entity
@Table(name="link_txns")
public class LinkPaymentTxn extends PaymentTxn {
    public LinkPaymentTxn(LinkPayment payment, Customer customer, Double revenue, List<PromoCode> codesApplied) {
        super(payment, customer, revenue, codesApplied);
    }
}
