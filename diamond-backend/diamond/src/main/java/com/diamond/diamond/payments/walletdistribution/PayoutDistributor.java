package com.diamond.diamond.payments.walletdistribution;

import java.util.HashMap;
import java.util.Map;

import com.diamond.diamond.transactions.Vendor;
import com.diamond.diamond.transactions.VendorWallet;

/*
 * Defines how payments will be distributed across the vendor's wallets when receiving payments for security purposes
 * How to use:
 * 1a. Define the Payment object (invoice, subscription, etc)
 * 1b. Instantiate the PayoutDistributor and pass in a HashMap for the wallet distribution
 * or 2. Instantiate the PayoutDistributor w/out any wallet distribution
 *  2b. Initialize the PayoutDistribution manually
 * 2c. Call the .setDistribution() method of the PayoutDistributor and pass in the PayoutDistribution object
 */
public class PayoutDistributor {

    private final Vendor vendor;
    private PayoutDistribution distribution;

    /*
     * Instantiates a PayoutDistributor with no wallet distribution.
     */
    public PayoutDistributor(Vendor vendor, String name) throws Exception {
        this.vendor = vendor;
        this.distribution = new PayoutDistribution(new HashMap<>(), name);
    }

    /*
     * Instantiates a PayoutDistributor with a predefined wallet distribution
     */
    public PayoutDistributor(Vendor vendor, Map<VendorWallet, Double> mappings, String name) throws Exception {
        this.vendor = vendor;
        this.distribution = new PayoutDistribution(mappings, name);
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

    public PayoutDistribution getDistribution() {
        return distribution;
    }

    public void setDistribution(PayoutDistribution distribution) throws Exception {
        this.distribution = distribution;
    }
}
