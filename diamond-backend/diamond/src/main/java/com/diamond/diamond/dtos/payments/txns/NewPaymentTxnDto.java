package com.diamond.diamond.dtos.payments.txns;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/*
 * Used when sending API requests to add new transaction records (for checkout/link payments) in the database
 */
public class NewPaymentTxnDto {
    private UUID paymentId;
    private UUID customerId;
    @Positive
    private BigDecimal revenue;
    // validates an ETH or SOL transaction hash
    @Pattern(regexp="^(0x[A-Fa-f0-9]{64}|[1-9A-HJ-NP-Za-km-z]{88})$")
    private String signHash;
    private List<Long> codesAppliedIds;
    private StablecoinCurrency currencyUsed;

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
    public BigDecimal getRevenue() {
        return revenue;
    }
    public void setRevenue(BigDecimal revenue) {
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

    public StablecoinCurrency getCurrencyUsed() {
        return currencyUsed;
    }

    public void setCurrencyUsed(StablecoinCurrency currencyUsed) {
        this.currencyUsed = currencyUsed;
    }
    
    
}
