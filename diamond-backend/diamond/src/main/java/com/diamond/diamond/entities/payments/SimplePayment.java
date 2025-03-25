package com.diamond.diamond.entities.payments;

import java.math.BigDecimal;
import java.util.List;

import com.diamond.diamond.entities.Account;
import com.diamond.diamond.entities.AccountWallet;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.SimplePaymentCategory;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="simple_payments")
public class SimplePayment extends Payment {

    @Column(name="has_max_num_of_payments", nullable=false)
    private Boolean hasMaxNumberOfPayments;
    
    @Column(name="max_num_of_payments")
    private Integer maxNumberOfPayments;

    @Column(name="promo_codes_enabled", nullable=false)
    private Boolean enablePromoCodes;

    @Column(name="category", nullable=false)
    @Enumerated(EnumType.STRING)
    private SimplePaymentCategory category;

    @OneToMany(mappedBy="payment")
    private List<PromoCode> validPromoCodes;

    public SimplePayment() {}
   
    public SimplePayment(BigDecimal amount, Account account, Blockchain chain, List<AccountWallet> accountWallets,
                        Boolean hasMaxNumberOfPayments, Integer maxNumberOfPayments, Boolean enablePromoCodes, List<PromoCode> validPromoCodes, SimplePaymentCategory category,
                        List<StablecoinCurrency> acceptedCurrencies) {
        super(amount, account, chain, accountWallets, acceptedCurrencies);
        
        this.hasMaxNumberOfPayments = hasMaxNumberOfPayments;
        this.maxNumberOfPayments = maxNumberOfPayments;
        this.enablePromoCodes = enablePromoCodes;
        this.validPromoCodes = validPromoCodes;
        this.category = category;
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

    public SimplePaymentCategory getCategory() {
        return category;
    }

    public void setCategory(SimplePaymentCategory category) {
        this.category = category;
    }

}
