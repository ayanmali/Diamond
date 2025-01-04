package com.diamond.diamond.payments.subscriptions;

import com.diamond.diamond.payments.BillingCustomer;
import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;
import com.diamond.diamond.transactions.StablecoinCurrency;
import com.diamond.diamond.transactions.Vendor;

public class Subscription extends Payment {

    private int billingBasis; // the basis for how often payments should recur (in days)
    private SubscriptionStatus subscriptionStatus;
    private String locationPaid;

    public Subscription(double periodAmount, Vendor vendor, BillingCustomer customer, StablecoinCurrency currency, int billingBasis) throws Exception {
        super(periodAmount, vendor, customer, currency);
        this.billingBasis = billingBasis;
        this.subscriptionStatus = SubscriptionStatus.ACTIVE;
    }

    @Override
    public void pay() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pay'");
    }

    @Override
    public PaymentStatus validatePayment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    }

    public int getBillingBasis() {
        return billingBasis;
    }

    public void setBillingBasis(int billingBasis) {
        this.billingBasis = billingBasis;
    }

    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(SubscriptionStatus subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getLocationPaid() {
        return locationPaid;
    }

    public void setLocationPaid(String locationPaid) {
        this.locationPaid = locationPaid;
    }

}
