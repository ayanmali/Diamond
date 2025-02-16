package com.diamond.diamond.entities.payments;

import java.util.Date;
import java.util.List;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
 * This class defines the structure of as well as records of invoices
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // for the subscription invoice subclass
@Table(name="invoices")
public class Invoice extends Payment {

    @ManyToOne
    @JoinColumn(name="id")
    private Customer customer;

    @Column(name="time_sent")
    private Date timeSent;

    @Column(name="time_paid")
    private Date timePaid;

    @Column(name="location_paid")
    private String locationPaid;

    @Column(name="vendor_comments")
    private String vendorComments;

    @Column(name="customer_comments")
    private String customerComments;

    public Invoice() {}

    public Invoice(Double amount, Vendor vendor, Customer customer, StablecoinCurrency currency, Blockchain chain, List<VendorWallet> vendorWallets, String vendorComments) {
        super(amount, vendor, currency, chain, vendorWallets);
        this.customer = customer;
        this.timeSent = new Date();
        this.vendorComments = vendorComments;
    }

    // @Override
    // public PaymentStatus validatePayment() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    // }

    public Date getTimeSent() {
        return timeSent;
    }

    public Date getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(Date timePaid) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
