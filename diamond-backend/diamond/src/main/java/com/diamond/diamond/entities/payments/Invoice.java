/*
 * TODO: smart invoices for better explainability, easy re-sends and edits
 */
package com.diamond.diamond.entities.payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;

/*
 * This class defines the structure of as well as records of invoices
 */
@Entity
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) // for the subscription invoice subclass
@Table(name="invoices")
public class Invoice extends Payment {

    // @ManyToOne
    // @JoinColumn(name="customer_id", referencedColumnName="id")
    // private Customer customer;
    @Column(name="customer_id", nullable=false)
    private UUID customerId;

    @Column(name="time_sent")
    @PastOrPresent
    private Date timeSent;

    @Column(name="time_paid")
    @PastOrPresent
    private Date timePaid;

    @Column(name="location_paid")
    private String locationPaid;

    // the coupons that are applied across this payment object
    // @ManyToMany(mappedBy="invoicesAppliedTo", cascade=CascadeType.ALL)
    // @Column(name="coupons_applied")
    // private Set<Coupon> couponsApplied;

    @Column(name="account_comments")
    private String accountComments;

    @Column(name="customer_comments")
    private String customerComments;

    public Invoice() {}

    public Invoice(BigDecimal amount, UUID accountId, UUID customerId, Blockchain chain, List<UUID> accountWalletIds, String accountComments, List<StablecoinCurrency> acceptedCurrencies) {
        //super(amount, account, chain, accountWallets, acceptedCurrencies);
        super(amount, accountId, chain, accountWalletIds, acceptedCurrencies);
        this.customerId = customerId;
        this.timeSent = new Date();
        this.accountComments = accountComments;
    }

    public Invoice(BigDecimal amount, UUID accountId, UUID customerId, Blockchain chain, UUID accountWalletId, String accountComments, List<StablecoinCurrency> acceptedCurrencies) {
        super(amount, accountId, chain, accountWalletId, acceptedCurrencies);
        this.customerId = customerId;
        this.timeSent = new Date();
        this.accountComments = accountComments;
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

    public String getAccountComments() {
        return accountComments;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    public void setCustomerComments(String customerComments) {
        this.customerComments = customerComments;
    }

    // public Set<Coupon> getCouponsApplied() {
    //     return couponsApplied;
    // }

    // public void setCouponsApplied(Set<Coupon> couponsApplied) {
    //     this.couponsApplied = couponsApplied;
    // }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
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
