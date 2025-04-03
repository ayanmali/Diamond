package com.diamond.diamond.dtos.wallets;

import com.diamond.diamond.types.Blockchain;

import jakarta.validation.constraints.Size;

public class NewAccountWalletDto {
    //private String address;
    private Blockchain chain;
    @Size(min=1, max=40)
    private String walletName;
    //private UUID accountId;
    //private UUID idempotencyKey;
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

    // public UUID getIdempotencyKey() {
    //     return idempotencyKey;
    // }

    // public void setIdempotencyKey(UUID idempotencyKey) {
    //     this.idempotencyKey = idempotencyKey;
    // }

}
