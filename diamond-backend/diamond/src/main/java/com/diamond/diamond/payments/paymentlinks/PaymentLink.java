package com.diamond.diamond.payments.paymentlinks;

import com.diamond.diamond.CustomerWallet;
import com.diamond.diamond.StablecoinCurrency;
import com.diamond.diamond.VendorWallet;
import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;

public class PaymentLink implements Payment {

    private double amount;
    private VendorWallet vendorWallet;
    private final StablecoinCurrency currency;
    private PaymentStatus paymentStatus;

    public PaymentLink(double amount, VendorWallet vendorWallet, StablecoinCurrency stablecoinCurrency) {
        this.amount = amount;
        this.vendorWallet = vendorWallet;
        this.currency = stablecoinCurrency;
    }

    @Override
    public double getAmount() {
        // TODO Auto-generated method stub
        return amount;
    }

    @Override
    public VendorWallet getVendorWallet() {
        // TODO Auto-generated method stub
        return vendorWallet;
    }

    @Override
    public StablecoinCurrency getStablecoinCurrency() {
        // TODO Auto-generated method stub
        return currency;
    }

    @Override
    public PaymentStatus getPaymentStatus() {
        // TODO Auto-generated method stub
        return paymentStatus;
    }

    @Override
    public void sendPayment(CustomerWallet customerWallet) {
        // TODO Auto-generated method stub
        // add api logic to interact w/ the exchange here
    }

}
