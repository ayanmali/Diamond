package com.diamond.diamond.entities.payments.subscriptions;

import java.util.List;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.Invoice;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name="subscription_invoices")
public class SubscriptionInvoice extends Invoice {
    @Column(name="billing_basis")
    private Long billingBasis; // the number of days after which to bill the customer
    
    @Column(name="status", nullable=false)
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    public SubscriptionInvoice() {}

    public SubscriptionInvoice(Double amount, Vendor vendor, Customer customer, StablecoinCurrency currency, Blockchain chain, List<VendorWallet> vendorWallets, String vendorComments) {
        super(amount, vendor, customer, currency, chain, vendorWallets, vendorComments);
        this.billingBasis = 30L; // defaults to 30 day subscription
        this.status = SubscriptionStatus.PAUSED;
    }

    public Long getBillingBasis() {
        return billingBasis;
    }

    public void setBillingBasis(Long billingBasis) {
        this.billingBasis = billingBasis;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }
}

