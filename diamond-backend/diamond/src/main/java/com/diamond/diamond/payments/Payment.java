package com.diamond.diamond.payments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.diamond.diamond.payments.walletdistribution.PaymentDistributor;
import com.diamond.diamond.transactions.StablecoinCurrency;
import com.diamond.diamond.transactions.Vendor;
import com.diamond.diamond.transactions.VendorWallet;

/*
 * Used to define the generic attributes and methods across all types of payments.
 */
public abstract class Payment {

    final double amount;
    //final VendorWallet businessWallet;
    final Vendor vendor;
    final Customer customer;
    final StablecoinCurrency currency;
    PaymentStatus paymentStatus;
    // used to define how payments are allocated between the vendor's wallets, if desired
    PaymentDistributor distributor;

    public Payment(double amount, Vendor vendor, Customer customer, StablecoinCurrency currency) throws Exception {
        this.amount = amount;
        this.vendor = vendor;
        this.customer = customer;
        this.currency = currency;
        this.paymentStatus = PaymentStatus.PENDING;

        VendorWallet wallet = this.vendor.getWallets().get(0);
        // Allocates all incoming payments to the vendor's primary wallet by default
        this.distributor = new PaymentDistributor(vendor,
                Map.of(wallet, 1.0),
                "");
    }

    /*
     * Distributes payments to multiple wallets according to the 
     */
    public void distributePayment() {
        Map<VendorWallet, Double> mappings = this.distributor.getDistribution().getMappings();
        List<VendorWallet> keyList = new ArrayList<>(mappings.keySet());

        double remainingAmount = this.amount;

        for (int i = 0; i < keyList.size(); i++) {
            double transferAmount;

            if (i == keyList.size() - 1) {
                transferAmount = remainingAmount;
            } else {
                double percentage = mappings.get(keyList.get(i));
                transferAmount = (this.amount * Math.floor(percentage * 10000)) / 10000;
                remainingAmount -= transferAmount;
            }
            // Execute the transfer
            // try {
            //     String address = keyList.get(i).getAddress();
            //     // transfer a quantity equivalent to transferAmount in tokens to the address

            // } catch () {
            // }
        }
    }

    public abstract void pay();

    public abstract PaymentStatus validatePayment();

    public double getAmount() {
        return amount;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public StablecoinCurrency getStablecoinCurrency() {
        return currency;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentDistributor getDistributor() {
        return distributor;
    }

    public void setDistributor(PaymentDistributor distributor) {
        this.distributor = distributor;
    }

    public void setDistributor(Map<VendorWallet, Double> mappings) throws Exception {
        this.distributor = new PaymentDistributor(this.vendor, mappings, "");
    }

}
