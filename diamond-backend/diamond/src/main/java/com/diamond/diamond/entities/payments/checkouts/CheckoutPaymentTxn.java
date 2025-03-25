// package com.diamond.diamond.entities.payments.checkouts;

// import java.math.BigDecimal;
// import java.util.List;

// import com.diamond.diamond.entities.Customer;
// import com.diamond.diamond.entities.payments.PaymentTxn;
// import com.diamond.diamond.entities.payments.PromoCode;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Table;

// /*
//  * Defines a transaction between a customer and Account for a checkout payment
//  */
// @Entity
// @Table(name="checkout_txns")
// public class CheckoutPaymentTxn extends PaymentTxn {

//     public CheckoutPaymentTxn() {}
//     public CheckoutPaymentTxn(CheckoutPayment payment, Customer customer, BigDecimal revenue, List<PromoCode> codesApplied) {
//         super(payment, customer, revenue, codesApplied);
//     }
    
// }
