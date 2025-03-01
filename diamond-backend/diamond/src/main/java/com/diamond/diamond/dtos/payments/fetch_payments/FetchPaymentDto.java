package com.diamond.diamond.dtos.payments.fetch_payments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Hibernate;

import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

public class FetchPaymentDto {
    private UUID id;
    private Double amount;
    private UUID vendorId;
    private StablecoinCurrency currency;
    private Blockchain chain;
    private List<Long> walletDistribution;
    private Date createdAt;
    private Date updatedAt;

    public FetchPaymentDto() {}

    public FetchPaymentDto(Payment payment) {
        this.id = payment.getId();
        this.amount = payment.getAmount();
        this.vendorId = payment.getVendor().getId();
        this.currency = payment.getStablecoinCurrency();
        this.chain = payment.getChain();

        if (payment.getWalletDistribution() != null && Hibernate.isInitialized(payment.getWalletDistribution())) {
            List<Long> walletIds = new ArrayList<>();
            for (VendorWallet vw : payment.getWalletDistribution()) {
                walletIds.add(vw.getId());
            }
            this.walletDistribution = walletIds;
        }

        this.createdAt = payment.getCreatedAt();
        this.updatedAt = payment.getUpdatedAt();
    }

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
        return walletDistribution;
    }
    public void setVendorWalletIds(List<Long> vendorWalletIds) {
        this.walletDistribution = vendorWalletIds;
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
