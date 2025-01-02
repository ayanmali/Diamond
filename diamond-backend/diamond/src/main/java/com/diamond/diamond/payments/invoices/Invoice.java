package com.diamond.diamond.payments.invoices;

import java.time.Instant;

import com.diamond.diamond.CustomerWallet;
import com.diamond.diamond.StablecoinCurrency;
import com.diamond.diamond.VendorWallet;
import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;

public class Invoice implements Payment {

    private final double amount;
    private final VendorWallet vendorWallet;
    private final StablecoinCurrency currency;
    private PaymentStatus paymentStatus;

    private final long timeSent;
    private long timePaid;
    private InvoiceCustomer customer;
    private CustomerWallet customerWallet;
    private String locationPaid;
    private final String vendorComments;
    private String customerComments;

    /* Constructor method */
    public Invoice(double amount, VendorWallet vendorWallet, StablecoinCurrency currency, InvoiceCustomer customer, String vendorComments) {
        this.amount = amount;
        this.vendorWallet = vendorWallet;
        this.currency = currency;
        this.customer = customer;
        this.vendorComments = vendorComments;
        this.timeSent = Instant.now().toEpochMilli();
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void sendPayment(InvoiceCustomer customer, CustomerWallet customerWallet, String customerComments) {
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

    public VendorWallet getVendorWallet() {
        return vendorWallet;
    }

    public StablecoinCurrency getStablecoinCurrency() {
        return currency;
    }

    public InvoiceCustomer getCustomer() {
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

    @Override
    public void sendPayment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendPayment'");
    }

}
