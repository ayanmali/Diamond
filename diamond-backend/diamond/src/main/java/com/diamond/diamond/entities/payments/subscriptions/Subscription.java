package com.diamond.diamond.entities.payments.subscriptions;
// package com.diamond.diamond.payments.subscriptions;

// import com.diamond.diamond.payments.BillingCustomer;
// import com.diamond.diamond.payments.Payment;
// import com.diamond.diamond.payments.walletdistribution.PaymentDistributor;
// import com.diamond.diamond.transactions.Blockchain;
// import com.diamond.diamond.transactions.PaymentStatus;
// import com.diamond.diamond.transactions.StablecoinCurrency;
// import com.diamond.diamond.transactions.Vendor;

// public class Subscription extends Payment {

//     private int billingBasis; // the basis for how often payments should recur (in days)
//     private SubscriptionStatus subscriptionStatus;
//     private String locationPaid;

//     public Subscription(double periodAmount, Vendor vendor, BillingCustomer customer, StablecoinCurrency currency, Blockchain chain, PaymentDistributor distributor, int billingBasis) throws Exception {
//         super(periodAmount, vendor, customer, currency, chain, distributor);
//         this.billingBasis = billingBasis;
//         this.subscriptionStatus = SubscriptionStatus.ACTIVE;
//     }

//     @Override
//     public PaymentStatus validatePayment() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
//     }

//     public int getBillingBasis() {
//         return billingBasis;
//     }

//     public void setBillingBasis(int billingBasis) {
//         this.billingBasis = billingBasis;
//     }

//     public SubscriptionStatus getSubscriptionStatus() {
//         return subscriptionStatus;
//     }

//     public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
//         this.subscriptionStatus = subscriptionStatus;
//     }

//     public String getLocationPaid() {
//         return locationPaid;
//     }

//     public void setLocationPaid(String locationPaid) {
//         this.locationPaid = locationPaid;
//     }

// }
