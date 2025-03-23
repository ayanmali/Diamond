package com.diamond.diamond.entities.payments.link_payments;

import java.math.BigDecimal;
import java.util.List;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.entities.payments.PromoCode;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/*
 * Defining a single transaction between a customer and a Account's payment link
 */
@Entity
@Table(name="link_txns")
public class LinkPaymentTxn extends PaymentTxn {

    public LinkPaymentTxn() {}
    public LinkPaymentTxn(LinkPayment payment, Customer customer, BigDecimal revenue, List<PromoCode> codesApplied) {
        super(payment, customer, revenue, codesApplied);
    }
}
