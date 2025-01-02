package com.diamond.diamond;

import java.time.Instant;

public class Invoice {

    private final double amount;
    private final VendorWallet businessWallet;
    private final StablecoinCurrency currency;
    private Customer customer;
    private CustomerWallet customerWallet;
    private final long timeSent;
    private long timePaid;
    private PaymentStatus paymentStatus;
    private String locationPaid;
    private final String vendorComments;
    private String customerComments;

    /* Constructor method */
    public Invoice(double amount, VendorWallet businessWallet, StablecoinCurrency currency, Customer customer, String vendorComments) {
        this.amount = amount;
        this.businessWallet = businessWallet;
        this.currency = currency;
        this.customer = customer;
        this.vendorComments = vendorComments;
        this.timeSent = Instant.now().toEpochMilli();
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void sendPayment(Customer customer, CustomerWallet customerWallet, String customerComments) {
        this.locationPaid = "Here"; // replace w/ the actual location from where the request is coming from
        this.customer = customer;
        this.customerWallet = customerWallet;
        this.customerComments = customerComments;
        this.timePaid = Instant.now().toEpochMilli();
        // this.paymentStatus = PaymentStatus.SUCCEEDED;

        // todo: add payment logic w/ crypto api
    }

    public double getAmount() {
        return amount;
    }

    public VendorWallet getBusinessWallet() {
        return businessWallet;
    }

    public StablecoinCurrency getStablecoinCurrency() {
        return currency;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerWallet getCustomerWallet() {
        return customerWallet;
    }

    public long getTimeSent() {
        return timeSent;
    }

    public long getTimePaid() {
        return timePaid;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public String getLocationPaid() {
        return locationPaid;
    }

    public String getVendorComments() {
        return vendorComments;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    public void cashOut(VendorWallet wallet, double amount, StablecoinCurrency currency) {
        // todo: add logic for cashing out stablecoins to a bank account
    }

}
