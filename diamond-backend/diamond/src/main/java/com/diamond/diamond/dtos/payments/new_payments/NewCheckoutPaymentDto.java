package com.diamond.diamond.dtos.payments.new_payments;

import java.util.List;

public class NewCheckoutPaymentDto extends NewPaymentDto {
    private Boolean hasMaxNumberOfPayments;
    private Integer maxNumberOfPayments;
    private Boolean enablePromoCodes;
    private List<Long> validPromoCodeIds;
    
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
}
