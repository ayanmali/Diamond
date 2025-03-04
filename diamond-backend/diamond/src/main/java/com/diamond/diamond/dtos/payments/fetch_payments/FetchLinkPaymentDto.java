package com.diamond.diamond.dtos.payments.fetch_payments;

import java.util.List;

import com.diamond.diamond.dtos.payments.PromoCodeDto;
import com.diamond.diamond.entities.payments.Payment;

public class FetchLinkPaymentDto extends FetchPaymentDto {
    private Boolean hasMaxNumberOfPayments;
    private Integer maxNumberOfPayments;
    private Boolean enablePromoCodes;
    private List<PromoCodeDto> validPromoCodeDtos;

    public FetchLinkPaymentDto() {}
    public FetchLinkPaymentDto(Payment payment) {
        super(payment);
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
    public List<PromoCodeDto> getValidPromoCodes() {
        return validPromoCodeDtos;
    }
    public void setValidPromoCodeDtos(List<PromoCodeDto> validPromoCodeDtos) {
        this.validPromoCodeDtos = validPromoCodeDtos;
    }
}
