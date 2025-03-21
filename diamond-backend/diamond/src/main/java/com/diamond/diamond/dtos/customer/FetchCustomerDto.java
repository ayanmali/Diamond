package com.diamond.diamond.dtos.customer;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.dtos.wallets.FetchCustomerWalletDto;

public class FetchCustomerDto {
    private UUID id;
    private String name;
    private String email;
    private List<FetchCustomerWalletDto> wallets;
    private UUID accountId;
    private Date createdAt;
    private Date updatedAt;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<FetchCustomerWalletDto> getWallets() {
        return wallets;
    }
    public void setWallets(List<FetchCustomerWalletDto> wallets) {
        this.wallets = wallets;
    }
    public UUID getAccountId() {
        return accountId;
    }
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
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
