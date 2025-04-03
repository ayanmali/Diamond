package com.diamond.diamond.dtos.account;

import java.math.BigDecimal;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

public class NewTokenTransferDto {
    private UUID accountId;
    private UUID accountWalletId;
    private StablecoinCurrency token;
    private String receiverAddress;
    private Blockchain chain;
    private BigDecimal amount;
    
    public UUID getAccountId() {
        return accountId;
    }
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
    public UUID getAccountWalletId() {
        return accountWalletId;
    }
    public void setAccountWalletId(UUID accountWalletId) {
        this.accountWalletId = accountWalletId;
    }
    public StablecoinCurrency getToken() {
        return token;
    }
    public void setToken(StablecoinCurrency token) {
        this.token = token;
    }
    public String getReceiverAddress() {
        return receiverAddress;
    }
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
    public Blockchain getChain() {
        return chain;
    }
    public void setChain(Blockchain chain) {
        this.chain = chain;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    

}
