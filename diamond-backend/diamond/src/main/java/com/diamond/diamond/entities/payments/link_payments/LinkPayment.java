package com.diamond.diamond.entities.payments.link_payments;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy="id")
    private List<PromoCode> validPromoCodes;

    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

    public LinkPayment() {}
    // This variable should be mutable for payment links
    public LinkPayment(Double amount, Vendor vendor, StablecoinCurrency currency, Blockchain chain, List<VendorWallet> vendorWallets) {
        super(amount, vendor, currency, chain, vendorWallets);
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

    public List<PromoCode> getPromoCodes() {
        return validPromoCodes;
    }

    public void addPromoCode(PromoCode promoCode) {
        validPromoCodes.add(promoCode);
    }

    public void removePromoCode(PromoCode promoCode) {
        validPromoCodes.remove(promoCode);
    }

    public void setPromoCodes(List<PromoCode> promoCodes) {
        this.validPromoCodes = promoCodes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
