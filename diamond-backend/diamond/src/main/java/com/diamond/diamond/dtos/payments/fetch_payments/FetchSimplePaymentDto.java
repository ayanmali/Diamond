package com.diamond.diamond.dtos.payments.fetch_payments;

import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.entities.payments.SimplePayment;

public class FetchSimplePaymentDto extends FetchPaymentDto {
    private Boolean hasMaxNumberOfPayments;
    private Integer maxNumberOfPayments;
    private Boolean enablePromoCodes;
    //private List<PromoCodeDto> validPromoCodeDtos;

    public FetchSimplePaymentDto(SimplePayment simplePayment) {
        super(simplePayment);
        this.hasMaxNumberOfPayments = simplePayment.getHasMaxNumberOfPayments();
        this.maxNumberOfPayments = simplePayment.getMaxNumberOfPayments();
        // this.enablePromoCodes = simplePayment.getEnablePromoCodes();
        //this.validPromoCodeDtos = simplePayment.getValidPromoCodes().stream().map(PromoCodeDto::new).collect(Collectors.toList());
    }
    public FetchSimplePaymentDto(Payment payment, Boolean hasMaxNumberOfPayments, Integer maxNumberOfPayments, Boolean enablePromoCodes /*,List<PromoCodeDto> validPromoCodeDtos*/) {
        super(payment);
        this.hasMaxNumberOfPayments = hasMaxNumberOfPayments;
        this.maxNumberOfPayments = maxNumberOfPayments;
        this.enablePromoCodes = enablePromoCodes;
        //this.validPromoCodeDtos = validPromoCodeDtos;
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

    // public List<PromoCodeDto> getValidPromoCodeDtos() {
    //     return validPromoCodeDtos;
    // }

    // public void setValidPromoCodeDtos(List<PromoCodeDto> validPromoCodeDtos) {
    //     this.validPromoCodeDtos = validPromoCodeDtos;
    // }
    
}
