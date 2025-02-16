package com.diamond.diamond.entities.payments.checkouts;

import java.util.List;

import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
 * Defines the fields and attributes of a checkout payment that a vendor can create
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // for the subscription checkout payment subclass
@Table(name="checkout_payments")
public class CheckoutPayment extends Payment {

    @Column(name="has_max_num_of_payments", nullable=false)
    private Boolean hasMaxNumberOfPayments;

    @Column(name="max_num_of_payments")
    private Integer maxNumberOfPayments;

    @Column(name="promo_codes_enabled", nullable=false)
    private Boolean enablePromoCodes;

    @OneToMany(mappedBy="id")
    private List<PromoCode> validPromoCodes;

    // @OneToMany(mappedBy="code")
    // private List<PromoCode> codesApplied;

    public CheckoutPayment() {}

    public CheckoutPayment(Double amount, Vendor vendor, StablecoinCurrency currency, Blockchain chain, List<VendorWallet> vendorWallets) {
        super(amount, vendor, currency, chain, vendorWallets);
    }
    
    // @Override
    // public PaymentStatus validatePayment() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'validatePayment'");
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

    public List<PromoCode> getValidPromoCodes() {
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

}