package com.diamond.diamond.dtos.payments.txns;

import java.util.List;
import java.util.UUID;

import com.diamond.diamond.entities.payments.PromoCode;

/*
 * Used when sending API requests to add new transaction records (for checkout/link payments) in the database
 */
public class NewPaymentTxnDto {
    private UUID paymentId;
    private UUID customerId;
    private Double revenue;
    private List<PromoCode> codesApplied;

    public UUID getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }
    public UUID getCustomerId() {
        return customerId;
    }
    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
    public Double getRevenue() {
        return revenue;
    }
    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }
    public List<PromoCode> getCodesApplied() {
        return codesApplied;
    }
    public void setCodesApplied(List<PromoCode> codesApplied) {
        this.codesApplied = codesApplied;
    }
    
    
}
