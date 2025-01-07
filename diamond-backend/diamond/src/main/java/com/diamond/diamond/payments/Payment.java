package com.diamond.diamond.payments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.diamond.diamond.payments.walletdistribution.PaymentDistributor;
import com.diamond.diamond.transactions.Blockchain;
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
    Customer customer;
    final StablecoinCurrency currency;
    final Blockchain chain;
    PaymentStatus paymentStatus;
    // used to define how payments are allocated between the vendor's wallets, if desired
    PaymentDistributor distributor;

    // /*
    //  * Constructor method that uses a single wallet for routing payments by default
    //  */
    // public Payment(double amount, Vendor vendor, Customer customer, StablecoinCurrency currency, Blockchain chain) throws Exception {
    //     this.amount = amount;
    //     this.vendor = vendor;
    //     this.customer = customer;
    //     this.currency = currency;
    //     this.chain = chain;
    //     this.paymentStatus = PaymentStatus.PENDING;

    //     // retrieves the vendor's first wallet on the speciied chain
    //     VendorWallet wallet = this.vendor.getWallets(chain).get(0);
    //     // Allocates all incoming payments to the vendor's primary wallet by default
    //     this.distributor = new PaymentDistributor(vendor,
    //             Map.of(wallet, 1.0),
    //             "");
    // }

    /*
     * Constructor method that uses a provided Map to route payments to multiple wallets
     */
    public Payment(double amount, Vendor vendor, /*Customer customer,*/ StablecoinCurrency currency, Blockchain chain, PaymentDistributor distributor) throws Exception {
        this.amount = amount;
        this.vendor = vendor;
        //this.customer = customer;
        this.currency = currency;
        this.chain = chain;
        this.paymentStatus = PaymentStatus.PENDING;
        this.distributor = distributor;
        // this.distributor = new PaymentDistributor(vendor, mappings, "");
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

    // public abstract void pay();

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

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Blockchain getChain() {
        return chain;
    }

}
