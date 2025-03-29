package com.diamond.diamond.dtos.payments.new_payments;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.SimplePaymentCategory;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.validation.constraints.Positive;

public class NewSimplePaymentDto extends NewPaymentDto {
    private Boolean hasMaxNumberOfPayments;
    @Positive
    private Integer maxNumberOfPayments;
    private Boolean enablePromoCodes;
    private List<Long> validPromoCodeIds;
    private SimplePaymentCategory category;

    /*
     * Defines a user's request to create a customer-facing payment (checkout page, link payment)
     */
    public NewSimplePaymentDto(BigDecimal amount, UUID accountId, List<StablecoinCurrency> currencies, Blockchain chain, List<UUID> accountWalletIds,
                                Boolean hasMaxNumberOfPayments, Integer maxNumberOfPayments, Boolean enablePromoCodes, List<Long> validPromoCodeIds, SimplePaymentCategory category) {
        super(amount, accountId, currencies, chain, accountWalletIds);
        this.hasMaxNumberOfPayments = hasMaxNumberOfPayments;
        this.maxNumberOfPayments = maxNumberOfPayments;
        this.enablePromoCodes = enablePromoCodes;
        this.validPromoCodeIds = validPromoCodeIds;
        this.category = category;
    }
    
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
    public List<Long> getValidPromoCodeIds() {
        return validPromoCodeIds;
    }
    public void setValidPromoCodeIds(List<Long> validPromoCodeIds) {
        this.validPromoCodeIds = validPromoCodeIds;
    }

    public SimplePaymentCategory getCategory() {
        return category;
    }

    public void setCategory(SimplePaymentCategory category) {
        this.category = category;
    }

}