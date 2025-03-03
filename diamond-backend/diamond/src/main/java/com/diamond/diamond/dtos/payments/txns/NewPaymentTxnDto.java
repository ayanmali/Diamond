package com.diamond.diamond.dtos.payments.txns;

import java.util.List;
import java.util.UUID;

/*
 * Used when sending API requests to add new transaction records (for checkout/link payments) in the database
 */
public class NewPaymentTxnDto {
    private UUID paymentId;
    private UUID customerId;
    private Double revenue;
    private String signHash;
    private List<Long> codesAppliedIds;

    public NewPaymentTxnDto() {}

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

    public List<Long> getCodesAppliedIds() {
        return codesAppliedIds;
    }

    public void setCodesAppliedIds(List<Long> codesAppliedIds) {
        this.codesAppliedIds = codesAppliedIds;
    }

    public String getSignHash() {
        return signHash;
    }

    public void setSignHash(String signHash) {
        this.signHash = signHash;
    }
    
    
}
