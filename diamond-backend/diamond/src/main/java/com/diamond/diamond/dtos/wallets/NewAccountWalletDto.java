package com.diamond.diamond.dtos.wallets;

import java.util.UUID;

import com.diamond.diamond.types.Blockchain;

public class NewAccountWalletDto {
    //private String address;
    private Blockchain chain;
    private String walletName;
    private UUID accountId;
    private UUID idempotencyKey;

    public UUID getAccountId() {
        return accountId;
    }
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
    // public String getAddress() {
    //     return address;
    // }
    // public void setAddress(String address) {
    //     this.address = address;
    // }
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

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(UUID idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

}
