package com.diamond.diamond.payments.subscriptions;

import com.diamond.diamond.StablecoinCurrency;
import com.diamond.diamond.VendorWallet;
import com.diamond.diamond.payments.BillingCustomer;
import com.diamond.diamond.payments.Customer;
import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;

public class Subscription implements Payment {

    private double amount;
    private VendorWallet vendorWallet;
    private StablecoinCurrency currency;
    private PaymentStatus currentPeriodPaymentStatus; // the payment status for the current billing period

    private BillingCustomer customer;
    private int billingBasis; // the basis for how often payments should recur (in days)
    private String locationPaid;

    public Subscription(double amount, VendorWallet vendorWallet, StablecoinCurrency currency) {
        this.amount = amount;
        this.vendorWallet = vendorWallet;
        this.currency = currency;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public VendorWallet getVendorWallet() {
        return vendorWallet;
    }

    @Override
    public StablecoinCurrency getStablecoinCurrency() {
        return currency;
    }

    @Override
    public PaymentStatus getPaymentStatus() {
        return currentPeriodPaymentStatus;
    }

    @Override
    public void sendPayment(Customer customer) {
        throw new UnsupportedOperationException("Unimplemented method 'sendPayment'");
    }

    @Override
    public PaymentStatus validatePayment() {
        throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setVendorWallet(VendorWallet vendorWallet) {
        this.vendorWallet = vendorWallet;
    }

    public void setCurrency(StablecoinCurrency currency) {
        this.currency = currency;
    }

    public BillingCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(BillingCustomer customer) {
        this.customer = customer;
    }

    public int getBillingBasis() {
        return billingBasis;
    }

    public void setBillingBasis(int billingBasis) {
        this.billingBasis = billingBasis;
    }

    public String getLocationPaid() {
        return locationPaid;
    }

    public void setLocationPaid(String locationPaid) {
        this.locationPaid = locationPaid;
    }

}
