package com.diamond.diamond.dtos.payments.fetch_payments;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

public class FetchPaymentDto {
    private UUID id;
    private Double amount;
    private UUID vendorId;
    private StablecoinCurrency currency;
    private Blockchain chain;
    private List<Long> vendorWalletIds;
    private Date createdAt;
    private Date updatedAt;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public UUID getVendorId() {
        return vendorId;
    }
    public void setVendorId(UUID vendorId) {
        this.vendorId = vendorId;
    }
    public StablecoinCurrency getCurrency() {
        return currency;
    }
    public void setCurrency(StablecoinCurrency currency) {
        this.currency = currency;
    }
    public Blockchain getChain() {
        return chain;
    }
    public void setChain(Blockchain chain) {
        this.chain = chain;
    }
    public List<Long> getVendorWalletIds() {
        return vendorWalletIds;
    }
    public void setVendorWalletIds(List<Long> vendorWalletIds) {
        this.vendorWalletIds = vendorWalletIds;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
