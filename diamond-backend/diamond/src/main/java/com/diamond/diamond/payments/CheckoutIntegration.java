package com.diamond.diamond.payments;

import com.diamond.diamond.StablecoinCurrency;
import com.diamond.diamond.VendorWallet;

public class CheckoutIntegration implements Payment {

    @Override
    public double getAmount() {
        throw new UnsupportedOperationException("Unimplemented method 'getAmount'");
    }

    @Override
    public VendorWallet getVendorWallet() {
        throw new UnsupportedOperationException("Unimplemented method 'getVendorWallet'");
    }

    @Override
    public StablecoinCurrency getStablecoinCurrency() {
        throw new UnsupportedOperationException("Unimplemented method 'getStablecoinCurrency'");
    }

    @Override
    public PaymentStatus getPaymentStatus() {
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentStatus'");
    }

    @Override
    public void sendPayment(Customer customer) {
        // Converting the provided Customer object into the appropriate subclass
        customer = (BasicCustomer) customer;
        throw new UnsupportedOperationException("Unimplemented method 'sendPayment'");
    }

    @Override
    public PaymentStatus validatePayment() {
        throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    }

}
