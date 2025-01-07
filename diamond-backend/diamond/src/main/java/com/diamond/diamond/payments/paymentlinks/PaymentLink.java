package com.diamond.diamond.payments.paymentlinks;

import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;
import com.diamond.diamond.payments.walletdistribution.PaymentDistributor;
import com.diamond.diamond.transactions.Blockchain;
import com.diamond.diamond.transactions.StablecoinCurrency;
import com.diamond.diamond.transactions.Vendor;

public class PaymentLink extends Payment {

    // This variable should be mutable for payment links
    // double amount;
    public PaymentLink(double amount, Vendor vendor, /*BasicCustomer customer, */StablecoinCurrency currency, Blockchain chain, PaymentDistributor distributor) throws Exception {
        super(amount, vendor, /*customer, */currency, chain, distributor);
        // this.amount = amount;
    }

    @Override
    public PaymentStatus validatePayment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    }

    // @Override
    // public double getAmount() {
    //     return amount;
    // }
    // public void setAmount(double amount) {
    //     this.amount = amount;
    // }
}
