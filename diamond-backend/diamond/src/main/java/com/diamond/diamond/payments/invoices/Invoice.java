package com.diamond.diamond.payments.invoices;

import java.time.Instant;

import com.diamond.diamond.payments.BillingCustomer;
import com.diamond.diamond.payments.Customer;
import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;
// import com.diamond.diamond.payments.walletdistribution.PayoutDistribution;
import com.diamond.diamond.payments.walletdistribution.PayoutDistributor;
import com.diamond.diamond.transactions.StablecoinCurrency;
import com.diamond.diamond.transactions.VendorWallet;

public class Invoice implements Payment {

    private final double amount;
    private final VendorWallet vendorWallet;
    private final StablecoinCurrency currency;
    private PaymentStatus paymentStatus;

    private final long timeSent;
    private long timePaid;
    private BillingCustomer customer;
    // private CustomerWallet billedWallet;
    private String locationPaid;
    private PayoutDistributor distributor; // the tooling to define how payments are allocated between the vendor's wallets
    private final String vendorComments;
    private String customerComments;

    /* Constructor method */
    public Invoice(double amount, VendorWallet vendorWallet, StablecoinCurrency currency, BillingCustomer customer, String vendorComments) {
        this.amount = amount;
        this.vendorWallet = vendorWallet;
        this.currency = currency;
        this.customer = customer;
        this.vendorComments = vendorComments;
        this.timeSent = Instant.now().toEpochMilli();
        this.paymentStatus = PaymentStatus.PENDING;
        this.distributor = null;
    }

    public void sendPayment(BillingCustomer customer, /*CustomerWallet billedWallet,*/ String customerComments) {
        this.locationPaid = "Here"; // replace w/ the actual location from where the request is coming from
        this.customer = customer;
        // this.billedWallet = billedWallet;
        this.customerComments = customerComments;
        this.timePaid = Instant.now().toEpochMilli();
        // this.paymentStatus = PaymentStatus.SUCCEEDED;

        // todo: add payment logic w/ crypto api
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public VendorWallet getVendorWallet() {
        return vendorWallet;
    }

    @Override
    public StablecoinCurrency getStablecoinCurrency() {
        return currency;
    }

    public BillingCustomer getCustomer() {
        return customer;
    }

    // public CustomerWallet getBilledWallet() {
    //     return billedWallet;
    // }
    public long getTimeSent() {
        return timeSent;
    }

    public long getTimePaid() {
        return timePaid;
    }

    @Override
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public String getLocationPaid() {
        return locationPaid;
    }

    public String getVendorComments() {
        return vendorComments;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    /*
     * Handles logic for sending crypto from the customer to the vendor's wallet(s).
     */
    @Override
    public void sendPayment(Customer customer) {
        // Converting the provided Customer object into the appropriate subclass
        customer = (BillingCustomer) customer;
        if (this.distributor == null || this.distributor.getDistribution().getMappings().isEmpty()) {
            // use the VendorWallet object
        } else {
            // route payments to the wallets in this.distributor.getDistribution() according to their respective proportions
        }
        throw new UnsupportedOperationException("Unimplemented method 'sendPayment'");
    }

    @Override
    public PaymentStatus validatePayment() {
        throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public PayoutDistributor getPayoutDistributor() {
        return distributor;
    }

    @Override
    public void setPayoutDistributor(PayoutDistributor distributor) {
        this.distributor = distributor;

    }

    // public static void main(String[] args) throws Exception {
    //     Invoice i = new Invoice(100, null, null, null, null);
    //     // Option 1 - instantiate both classes manually
    //     PayoutDistributor distributor = new PayoutDistributor(null, null);
    //     PayoutDistribution distribution = new PayoutDistribution(null, null);
    //     distributor.setDistribution(distribution);
    //     // Option 2 - pass the HashMap directly into the PayoutDistributor
    //     // PayoutDistributor distributor = new PayoutDistributor(null, null, null);
    //     i.setPayoutDistributor(distributor);
    // }
}
