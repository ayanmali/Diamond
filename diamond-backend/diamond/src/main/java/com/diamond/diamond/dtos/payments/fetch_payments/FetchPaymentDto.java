package com.diamond.diamond.dtos.payments.fetch_payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

public class FetchPaymentDto {
    private UUID id;
    private BigDecimal amount;
    private UUID accountId;
    private List<StablecoinCurrency> currencies;
    private Blockchain chain;
    private List<UUID> walletDistribution;
    private Date createdAt;
    private Date updatedAt;

    public FetchPaymentDto(UUID id, BigDecimal amount, UUID accountId, List<StablecoinCurrency> currencies, Blockchain chain, List<UUID> walletDistribution, Date createdAt, Date updatedAt) {
        this.id = id;
        this.amount = amount;
        this.accountId = accountId;
        this.currencies = currencies;
        this.chain = chain;
        this.walletDistribution = walletDistribution;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // public FetchPaymentDto(Payment payment) {
    //     this.id = payment.getId();
    //     this.amount = payment.getAmount();
    //     this.currencies = payment.getAcceptedCurrencies();
    //     this.chain = payment.getChain();

    //     if (payment.getAccount() != null && Hibernate.isInitialized(payment.getAccount())) {
    //         this.accountId = payment.getAccount().getId();
    //     }

    //     if (payment.getWalletDistribution() != null && Hibernate.isInitialized(payment.getWalletDistribution())) {
    //         List<FetchAccountWalletDto> walletDtos = new ArrayList<>();
    //         for (AccountWallet vw : payment.getWalletDistribution()) {
    //             walletDtos.add(AccountWalletService.convertAccountWalletToFetchDto(vw));
    //         }
    //         this.walletDistribution = walletDtos;
    //     }

    //     this.createdAt = payment.getCreatedAt();
    //     this.updatedAt = payment.getUpdatedAt();
    // }

    public FetchPaymentDto(Payment payment) {
        this.id = payment.getId();
        this.amount = payment.getAmount();
        this.accountId = payment.getAccountId();
        this.walletDistribution = payment.getWalletDistribution();
        this.currencies = payment.getAcceptedCurrencies();
        this.chain = payment.getChain();
        this.createdAt = payment.getCreatedAt();
        this.updatedAt = payment.getUpdatedAt();
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public UUID getAccountId() {
        return accountId;
    }
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
    public List<StablecoinCurrency> getCurrencies() {
        return currencies;
    }
    public void setCurrencies(List<StablecoinCurrency> currencies) {
        this.currencies = currencies;
    }
    public Blockchain getChain() {
        return chain;
    }
    public void setChain(Blockchain chain) {
        this.chain = chain;
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

    public List<UUID> getWalletDistribution() {
        return walletDistribution;
    }

    public void setWalletDistribution(List<UUID> walletDistribution) {
        this.walletDistribution = walletDistribution;
    }

}
