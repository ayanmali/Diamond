// package com.diamond.diamond.entities.payments.subscriptions;

// import java.util.List;

// import com.diamond.diamond.entities.Account;
// import com.diamond.diamond.entities.AccountWallet;
// import com.diamond.diamond.entities.payments.PromoCode;
// import com.diamond.diamond.entities.payments.checkouts.CheckoutPayment;
// import com.diamond.diamond.types.Blockchain;
// import com.diamond.diamond.types.StablecoinCurrency;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.Table;

// @Entity
// @Table(name="subscription_checkouts")
// public class SubscriptionCheckout extends CheckoutPayment {
//     @Column(name="billing_basis")
//     private Long billingBasis; // the number of days after which to bill the customer
    
//     @Column(name="status", nullable=false)
//     @Enumerated(EnumType.STRING)
//     private SubscriptionStatus status;

//     public SubscriptionCheckout() {}

//     public SubscriptionCheckout(BigDecimal amount, Account Account, StablecoinCurrency currency, Blockchain chain, List<AccountWallet> AccountWallets,
//                                 Boolean hasMaxNumberOfPayments, Integer maxNumberOfPayments, Boolean enablePromoCodes, List<PromoCode> validPromoCodes) {
//         super(amount, Account, currency, chain, AccountWallets, hasMaxNumberOfPayments, maxNumberOfPayments, enablePromoCodes, validPromoCodes);
//         this.billingBasis = 30L;
//         this.status = SubscriptionStatus.PAUSED;

//     }

//     public Long getBillingBasis() {
//         return billingBasis;
//     }

//     public void setBillingBasis(Long billingBasis) {
//         this.billingBasis = billingBasis;
//     }

//     public SubscriptionStatus getStatus() {
//         return status;
//     }

//     public void setStatus(SubscriptionStatus status) {
//         this.status = status;
//     }
// }
