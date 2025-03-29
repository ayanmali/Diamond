package com.diamond.diamond.dtos.payments.txns;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.types.PaymentStatus;
import com.diamond.diamond.types.StablecoinCurrency;

/*
 * Defining the DTO for retrieved transactions
 */
public class FetchPaymentTxnDto {
    private UUID id;
    private UUID paymentId;
    private UUID customerId;
    private BigDecimal revenue;
    private StablecoinCurrency currencyUsed;
    private String signHash;
    private String txHash;
    private PaymentStatus status;
    private Date timePaid;
    private List<Long> promoCodesAppliedIds;

    public FetchPaymentTxnDto(PaymentTxn txn) {
        this.customerId = txn.getCustomer().getId();
        this.id = txn.getId();
        this.paymentId = txn.getPayment().getId();
        this.revenue = txn.getRevenue();
        this.signHash = txn.getSignHash();
        this.txHash = txn.getTxHash();
        this.timePaid = txn.getTimePaid();
        this.status = txn.getStatus();
        this.currencyUsed = txn.getCurrencyUsed();

        // if (txn.getCodesApplied() != null && Hibernate.isInitialized(txn.getCodesApplied())) {
        //     List<Long> codesAppliedIds = new ArrayList<>();
        //     for (PromoCode promoCode : txn.getCodesApplied()) {
        //         codesAppliedIds.add(promoCode.getId());
        //     }
        //     txnDto.setPromoCodesAppliedIds(codesAppliedIds);
        // }

    }
    
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
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
    public String getSignHash() {
        return signHash;
    }
    public void setSignHash(String signHash) {
        this.signHash = signHash;
    }
    public String getTxHash() {
        return txHash;
    }
    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public List<Long> getPromoCodesAppliedIds() {
        return promoCodesAppliedIds;
    }
    public void setPromoCodesAppliedIds(List<Long> promoCodesAppliedIds) {
        this.promoCodesAppliedIds = promoCodesAppliedIds;
    }

    public Date getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(Date timePaid) {
        this.timePaid = timePaid;
    }

    public StablecoinCurrency getCurrencyUsed() {
        return currencyUsed;
    }

    public void setCurrencyUsed(StablecoinCurrency currencyUsed) {
        this.currencyUsed = currencyUsed;
    }
    

}
