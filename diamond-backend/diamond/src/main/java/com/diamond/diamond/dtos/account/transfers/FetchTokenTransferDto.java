package com.diamond.diamond.dtos.account.transfers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.diamond.diamond.entities.user.TokenTransfer;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;
import com.diamond.diamond.types.TokenTransferStatus;

public class FetchTokenTransferDto {
    private UUID id;
    private UUID accountId;
    private StablecoinCurrency token;
    private String receiverAddress;
    private Blockchain chain;
    private BigDecimal amount;
    private Date createdAt;
    private TokenTransferStatus status;
    private UUID accountWalletId;
    private BigDecimal lamportsFee;
    private String signHash;

    public FetchTokenTransferDto(TokenTransfer transfer) {
        this.id = transfer.getId();
        this.accountId = transfer.getAccountId();
        this.token = transfer.getToken();
        this.receiverAddress = transfer.getReceiverAddress();
        this.chain = transfer.getChain();
        this.amount = transfer.getAmount();
        this.createdAt = transfer.getCreatedAt();
        this.status = transfer.getStatus();
        this.accountWalletId = transfer.getAccountWalletId();
        this.lamportsFee = transfer.getLamportsFee();
        this.signHash = transfer.getSignHash();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public TokenTransferStatus getStatus() {
        return status;
    }

    public void setStatus(TokenTransferStatus status) {
        this.status = status;
    }

    public UUID getAccountWalletId() {
        return accountWalletId;
    }

    public void setAccountWalletId(UUID accountWalletId) {
        this.accountWalletId = accountWalletId;
    }

    public BigDecimal getLamportsFee() {
        return lamportsFee;
    }

    public void setLamportsFee(BigDecimal lamportsFee) {
        this.lamportsFee = lamportsFee;
    }

    public String getSignHash() {
        return signHash;
    }

    public void setSignHash(String signHash) {
        this.signHash = signHash;
    }

}
