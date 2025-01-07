package com.diamond.diamond.payments.invoices;

import java.time.Instant;

import com.diamond.diamond.payments.Payment;
import com.diamond.diamond.payments.PaymentStatus;
import com.diamond.diamond.payments.walletdistribution.PaymentDistributor;
import com.diamond.diamond.transactions.Blockchain;
import com.diamond.diamond.transactions.StablecoinCurrency;
import com.diamond.diamond.transactions.Vendor;

public class Invoice extends Payment {

    final long timeSent;
    long timePaid;
    String locationPaid;
    final String vendorComments;
    String customerComments;

    public Invoice(double amount, Vendor vendor, /*BillingCustomer customer,*/ StablecoinCurrency currency, Blockchain chain, PaymentDistributor distributor, String vendorComments) throws Exception {
        super(amount, vendor, /*customer,*/ currency, chain, distributor);
        this.timeSent = Instant.now().toEpochMilli();
        this.vendorComments = vendorComments;
    }

    @Override
    public PaymentStatus validatePayment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    }

    public long getTimeSent() {
        return timeSent;
    }

    public long getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(long timePaid) {
        this.timePaid = timePaid;
    }

    public String getLocationPaid() {
        return locationPaid;
    }

    public void setLocationPaid(String locationPaid) {
        this.locationPaid = locationPaid;
    }

    public String getVendorComments() {
        return vendorComments;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    public void setCustomerComments(String customerComments) {
        this.customerComments = customerComments;
    }

}

// public static void main(String[] args) throws Exception {
//     Invoice i = new Invoice(100, null, null, null, null);
//     // Option 1 - instantiate both classes manually
//     PaymentDistributor distributor = new PaymentDistributor(null, null);
//     PaymentDistribution distribution = new PaymentDistribution(null, null);
//     distributor.setDistribution(distribution);
//     // Option 2 - pass the HashMap directly into the PaymentDistributor
//     // PaymentDistributor distributor = new PaymentDistributor(null, null, null);
//     i.setPaymentDistributor(distributor);
    // }
