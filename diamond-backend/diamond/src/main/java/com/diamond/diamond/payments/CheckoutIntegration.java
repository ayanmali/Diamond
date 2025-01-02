package com.diamond.diamond.payments;

import com.diamond.diamond.StablecoinCurrency;
import com.diamond.diamond.VendorWallet;

public class CheckoutIntegration implements Payment {

    @Override
    public double getAmount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAmount'");
    }

    @Override
    public VendorWallet getVendorWallet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getVendorWallet'");
    }

    @Override
    public StablecoinCurrency getStablecoinCurrency() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStablecoinCurrency'");
    }

    @Override
    public PaymentStatus getPaymentStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPaymentStatus'");
    }

    @Override
    public void sendPayment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendPayment'");
    }

}
