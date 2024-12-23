package com.diamond.diamond;

public class SimplePayment {

    private double amount;
    private VendorWallet businessWallet;
    private Currency currency;
    private Customer customer;
    private CustomerWallet customerWallet;
    private long timestamp;
    private PaymentStatus paymentStatus;
    private String locationPaid;
    private String vendorComments;
    private String customerComments;

    /* Constructor method */
    public SimplePayment(double amount, VendorWallet businessWallet, Currency currency, Customer customer, String vendorComments) {
        this.amount = amount;
        this.businessWallet = businessWallet;
        this.currency = currency;
        this.customer = customer;
        this.vendorComments = vendorComments;
        this.timestamp = Instant.now().toEpochMilli();
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void sendPayment(Customer customer, CustomerWallet customerWallet, String customerComments) {
        this.locationPaid = "Here"; // replace w/ the actual location from where the request is coming from
        this.customer = customer;
        this.customerWallet = customerWallet;
        this.customerComments = customerComments;

        // todo: add payment logic w/ crypto api
    }
}
