package com.diamond.diamond.dtos.wallets;

import java.util.Date;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.WalletStatus;

public class FetchAccountWalletDto {
    private UUID id;
    private String address;
    private Blockchain chain;
    private String walletName;
    private WalletStatus status;
    private Date createdAt;
    private Date updatedAt;
    private UUID accountId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Blockchain getChain() {
        return chain;
    }

    public void setChain(Blockchain chain) {
        this.chain = chain;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public WalletStatus getStatus() {
        return status;
    }

    public void setStatus(WalletStatus status) {
        this.status = status;
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

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
}
