package com.diamond.diamond.payments.invoices;

import java.time.Instant;

import com.diamond.diamond.StablecoinCurrency;
import com.diamond.diamond.VendorWallet;
import com.diamond.diamond.payments.BillingCustomer;
import com.diamond.diamond.payments.Customer;
import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;

public class Invoice implements Payment {

    private final double amount;
    private final VendorWallet vendorWallet;
    private final StablecoinCurrency currency;
    private PaymentStatus paymentStatus;

    private final long timeSent;
    private long timePaid;
    private BillingCustomer customer;
    // private CustomerWallet billedWallet;
    private String locationPaid;
    private final String vendorComments;
    private String customerComments;

    /* Constructor method */
    public Invoice(double amount, VendorWallet vendorWallet, StablecoinCurrency currency, BillingCustomer customer, String vendorComments) {
        this.amount = amount;
        this.vendorWallet = vendorWallet;
        this.currency = currency;
        this.customer = customer;
        this.vendorComments = vendorComments;
        this.timeSent = Instant.now().toEpochMilli();
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void sendPayment(BillingCustomer customer, /*CustomerWallet billedWallet,*/ String customerComments) {
        this.locationPaid = "Here"; // replace w/ the actual location from where the request is coming from
        this.customer = customer;
        // this.billedWallet = billedWallet;
        this.customerComments = customerComments;
        this.timePaid = Instant.now().toEpochMilli();
        // this.paymentStatus = PaymentStatus.SUCCEEDED;

        // todo: add payment logic w/ crypto api
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

    public BillingCustomer getCustomer() {
        return customer;
    }

    // public CustomerWallet getBilledWallet() {
    //     return billedWallet;
    // }
    public long getTimeSent() {
        return timeSent;
    }

    public long getTimePaid() {
        return timePaid;
    }

    @Override
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

    @Override
    public void sendPayment(Customer customer) {
        // Converting the provided Customer object into the appropriate subclass
        customer = (BillingCustomer) customer;
        throw new UnsupportedOperationException("Unimplemented method 'sendPayment'");
    }

    @Override
    public PaymentStatus validatePayment() {
        throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
