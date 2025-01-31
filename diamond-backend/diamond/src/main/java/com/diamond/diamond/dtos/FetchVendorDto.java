package com.diamond.diamond.dtos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.entities.VendorWallet;

public class FetchVendorDto {
    private String email;
    private UUID id;
    private String businessName;
    private Date createdAt;
    private Date updatedAt;
    private List<VendorWallet> wallets;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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

    public List<VendorWallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<VendorWallet> wallets) {
        this.wallets = wallets;
    }
}
