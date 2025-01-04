package com.diamond.diamond.payments.walletdistribution;

import java.util.HashMap;
import java.util.Map;

import com.diamond.diamond.transactions.Vendor;
import com.diamond.diamond.transactions.VendorWallet;

/*
 * Defines how payments will be distributed across the vendor's wallets when receiving payments for security purposes
 * How to use:
 * 1a. Define the Payment object (invoice, subscription, etc)
 * 1b. Instantiate the PaymentDistributor and pass in a HashMap for the wallet distribution
 * or 2. Instantiate the PaymentDistributor w/out any wallet distribution
 *  2b. Initialize the PaymentDistribution manually
 * 2c. Call the .setDistribution() method of the PaymentDistributor and pass in the PaymentDistribution object
 */
public class PaymentDistributor {

    private final Vendor vendor;
    private PaymentDistribution distribution;

    /*
     * Instantiates a PaymentDistributor with no wallet distribution.
     */
    public PaymentDistributor(Vendor vendor, String name) throws Exception {
        this.vendor = vendor;
        this.distribution = new PaymentDistribution(new HashMap<>(), name);
    }

    /*
     * Instantiates a PaymentDistributor with a predefined wallet distribution
     * This one is probably more convenient
     */
    public PaymentDistributor(Vendor vendor, Map<VendorWallet, Double> mappings, String name) throws Exception {
        this.vendor = vendor;
        this.distribution = new PaymentDistribution(mappings, name);
    }

    /*
     * Adds a wallet and it's corresponding payout allocation 
     * Note that every wallet in the walletDistribution must be of the same blockchain
     */
    // public void addWallet(VendorWallet vendorWallet, Double allocation) {
    //     walletDistribution.put(vendorWallet, allocation);
    //     if (!validateDistribution()) {
    //         throw new Error();
    //     }
    //     walletDistribution.remove(vendorWallet);
    // }
    public Vendor getVendor() {
        return vendor;
    }

    public PaymentDistribution getDistribution() {
        return distribution;
    }

    public void setDistribution(PaymentDistribution distribution) throws Exception {
        this.distribution = distribution;
    }
}
