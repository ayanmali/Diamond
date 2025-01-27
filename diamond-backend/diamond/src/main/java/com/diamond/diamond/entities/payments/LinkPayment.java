package com.diamond.diamond.entities.payments;

import java.util.List;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="link_payments")
public class LinkPayment extends Payment {
    @Column(name="has_max_num_of_payments", nullable=false)
    private Boolean hasMaxNumberOfPayments;
    
    @Column(name="max_num_of_payments")
    private Integer maxNumberOfPayments;

    @Column(name="promo_codes_enabled", nullable=false)
    private Boolean enablePromoCodes;

    // This variable should be mutable for payment links
    public LinkPayment(double amount, Vendor vendor, Customer customer, StablecoinCurrency currency, Blockchain chain, List<VendorWallet> vendorWallets) {
        super(amount, vendor, customer, currency, chain, vendorWallets);
        // this.amount = amount;
    }

    // @Override
    // public PaymentStatus validatePayment() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
    // }

    // @Override
    // public double getAmount() {
    //     return amount;
    // }
    // public void setAmount(double amount) {
    //     this.amount = amount;
    // }

    public Boolean getHasMaxNumberOfPayments() {
        return hasMaxNumberOfPayments;
    }

    public void setHasMaxNumberOfPayments(Boolean hasMaxNumberOfPayments) {
        this.hasMaxNumberOfPayments = hasMaxNumberOfPayments;
    }

    public Integer getMaxNumberOfPayments() {
        return maxNumberOfPayments;
    }

    public void setMaxNumberOfPayments(Integer maxNumberOfPayments) {
        this.maxNumberOfPayments = maxNumberOfPayments;
    }

    public Boolean getEnablePromoCodes() {
        return enablePromoCodes;
    }

    public void setEnablePromoCodes(Boolean enablePromoCodes) {
        this.enablePromoCodes = enablePromoCodes;
    }
}
