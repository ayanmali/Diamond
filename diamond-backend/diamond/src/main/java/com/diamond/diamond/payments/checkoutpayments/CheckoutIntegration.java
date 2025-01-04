package com.diamond.diamond.payments.checkoutpayments;

import com.diamond.diamond.payments.BasicCustomer;
import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;
import com.diamond.diamond.transactions.StablecoinCurrency;
import com.diamond.diamond.transactions.Vendor;

public class CheckoutIntegration extends Payment {

    public CheckoutIntegration(double amount, Vendor vendor, BasicCustomer customer, StablecoinCurrency currency) throws Exception {
        super(amount, vendor, customer, currency);
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

}
