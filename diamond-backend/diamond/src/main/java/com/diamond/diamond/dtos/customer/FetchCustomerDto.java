package com.diamond.diamond.dtos.customer;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.entities.user.Customer;

public class FetchCustomerDto {
    private UUID id;
    private String name;
    private String email;
    private List<UUID> walletIds;
    private UUID accountId;
    private Date createdAt;
    private Date updatedAt;

    public FetchCustomerDto(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.walletIds = customer.getWalletIds();
        this.accountId = customer.getAccountId();
        this.createdAt = customer.getCreatedAt();
        this.updatedAt = customer.getUpdatedAt();
    }

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

    public List<UUID> getWalletIds() {
        return walletIds;
    }

    public void setWalletIds(List<UUID> walletIds) {
        this.walletIds = walletIds;
    }

}
