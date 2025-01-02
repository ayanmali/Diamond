package com.diamond.diamond.payments.paymentlinks;

import com.diamond.diamond.StablecoinCurrency;
import com.diamond.diamond.VendorWallet;
import com.diamond.diamond.payments.BasicCustomer;
import com.diamond.diamond.payments.Customer;
import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;

public class PaymentLink implements Payment {

    private double amount;
    private VendorWallet vendorWallet;
    private final StablecoinCurrency currency;
    private PaymentStatus paymentStatus;

    private BasicCustomer customer;

    public PaymentLink(double amount, VendorWallet vendorWallet, StablecoinCurrency stablecoinCurrency) {
        this.amount = amount;
        this.vendorWallet = vendorWallet;
        this.currency = stablecoinCurrency;
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
        return paymentStatus;
    }

    public BasicCustomer getCustomer() {
        return customer;
    }

    @Override
    public void sendPayment(Customer customer) {
        // Converting the provided Customer object into the appropriate subclass
        customer = (BasicCustomer) customer;
        // add api logic to interact w/ the exchange here
    }

    @Override
    public PaymentStatus validatePayment() {
        // check blockchain scanner to see if the transaction was successful
        throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setVendorWallet(VendorWallet vendorWallet) {
        this.vendorWallet = vendorWallet;
    }

}
