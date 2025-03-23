package com.diamond.diamond.entities.payments.checkouts;

import java.math.BigDecimal;
import java.util.List;

import com.diamond.diamond.entities.Account;
import com.diamond.diamond.entities.AccountWallet;
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
 * Defines the fields and attributes of a checkout payment that a account can create
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

    @OneToMany(mappedBy="payment")
    private List<PromoCode> validPromoCodes;

    public CheckoutPayment() {}

    public CheckoutPayment(BigDecimal amount, Account account, StablecoinCurrency currency, Blockchain chain, List<AccountWallet> accountWallets,
                            Boolean hasMaxNumberOfPayments, Integer maxNumberOfPayments, Boolean enablePromoCodes, List<PromoCode> validPromoCodes) {
        super(amount, account, currency, chain, accountWallets);

        this.hasMaxNumberOfPayments = hasMaxNumberOfPayments;
        this.maxNumberOfPayments = maxNumberOfPayments;
        this.enablePromoCodes = enablePromoCodes;
        this.validPromoCodes = validPromoCodes;
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