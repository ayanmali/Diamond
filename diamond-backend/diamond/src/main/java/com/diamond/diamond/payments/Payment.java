package com.diamond.diamond.payments;

import com.diamond.diamond.payments.walletdistribution.PayoutDistributor;
import com.diamond.diamond.transactions.StablecoinCurrency;
import com.diamond.diamond.transactions.VendorWallet;

/*
 * Defining the generic attributes and methods across all types of payments
 */
public interface Payment {

    // public final double amount = 0;
    // public final VendorWallet businessWallet = null;
    // public final StablecoinCurrency currency = null;
    // public PaymentStatus paymentStatus = null;
    // // public final long timeSent = 0;
    // // public long timePaid = 0;
    public double getAmount();

    public VendorWallet getVendorWallet();

    public StablecoinCurrency getStablecoinCurrency();

    public PaymentStatus getPaymentStatus();

    public PayoutDistributor getPayoutDistributor();

    public void setPayoutDistributor(PayoutDistributor distributor);

    public void sendPayment(Customer customer);

    public PaymentStatus validatePayment(); // inspects the blockchain and sees if the transaction was successful

    // public void setAmount();
    // public void setBusinessWallet();
    // public void setCurrency();
    // public void setTimePaid();
}
