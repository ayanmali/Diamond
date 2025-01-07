package com.diamond.diamond.payments.checkoutpayments;

import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;
import com.diamond.diamond.payments.walletdistribution.PaymentDistributor;
import com.diamond.diamond.transactions.Blockchain;
import com.diamond.diamond.transactions.StablecoinCurrency;
import com.diamond.diamond.transactions.Vendor;

public class CheckoutIntegration extends Payment {

    public CheckoutIntegration(double amount, Vendor vendor, /*BasicCustomer customer,*/ StablecoinCurrency currency, Blockchain chain, PaymentDistributor distributor) throws Exception {
        super(amount, vendor, /*customer,*/ currency, chain, distributor);
    }

    @Override
    public PaymentStatus validatePayment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    }

}
