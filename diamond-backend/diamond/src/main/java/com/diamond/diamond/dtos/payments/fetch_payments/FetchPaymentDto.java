package com.diamond.diamond.dtos.payments.fetch_payments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Hibernate;

import com.diamond.diamond.dtos.wallets.FetchAccountWalletDto;
import com.diamond.diamond.entities.AccountWallet;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.services.AccountWalletService;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

public class FetchPaymentDto {
    private UUID id;
    private Double amount;
    private UUID accountId;
    private StablecoinCurrency currency;
    private Blockchain chain;
    private List<FetchAccountWalletDto> walletDistribution;
    private Date createdAt;
    private Date updatedAt;

    public FetchPaymentDto() {}

    public FetchPaymentDto(Payment payment) {
        this.id = payment.getId();
        this.amount = payment.getAmount();
        this.accountId = payment.getAccount().getId();
        this.currency = payment.getStablecoinCurrency();
        this.chain = payment.getChain();

        if (payment.getWalletDistribution() != null && Hibernate.isInitialized(payment.getWalletDistribution())) {
            List<FetchAccountWalletDto> walletDtos = new ArrayList<>();
            for (AccountWallet vw : payment.getWalletDistribution()) {
                walletDtos.add(AccountWalletService.convertAccountWalletToFetchDto(vw));
            }
            this.walletDistribution = walletDtos;
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
    public UUID getAccountId() {
        return accountId;
    }
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
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
    public List<FetchAccountWalletDto> getAccountWalletDtos() {
        return walletDistribution;
    }
    public void setAccountWalletDtos(List<FetchAccountWalletDto> accountWalletDtos) {
        this.walletDistribution = accountWalletDtos;
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
