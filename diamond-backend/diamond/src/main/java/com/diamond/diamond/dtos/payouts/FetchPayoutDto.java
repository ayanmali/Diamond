package com.diamond.diamond.dtos.payouts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.diamond.diamond.entities.payments.Payout;
import com.diamond.diamond.types.FiatCurrency;
import com.diamond.diamond.types.PayoutStatus;
import com.diamond.diamond.types.StablecoinCurrency;

public class FetchPayoutDto {
    private UUID id;
    private UUID accountId;
    private UUID walletId;
    //private String walletAddress;
    private BigDecimal amount;
    private StablecoinCurrency stablecoinCurrency;
    private FiatCurrency fiatCurrency;
    private Date createdAt;
    private Date payoutDate;
    private PayoutStatus status;

    public FetchPayoutDto(Payout payout) {
        this.id = payout.getId();
        this.accountId = payout.getAccountId();
        this.walletId = payout.getOfframpWalletId();
        this.amount = payout.getAmount();
        this.stablecoinCurrency = payout.getStablecoinCurrency();
        this.fiatCurrency = payout.getFiatCurrency();
        this.createdAt = payout.getCreatedAt();
        this.payoutDate = payout.getPayoutDate();
        this.status = payout.getStatus();
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
    public UUID getWalletId() {
        return walletId;
    }
    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public StablecoinCurrency getStablecoinCurrency() {
        return stablecoinCurrency;
    }
    public void setStablecoinCurrency(StablecoinCurrency stablecoinCurrency) {
        this.stablecoinCurrency = stablecoinCurrency;
    }
    public FiatCurrency getFiatCurrency() {
        return fiatCurrency;
    }
    public void setFiatCurrency(FiatCurrency fiatCurrency) {
        this.fiatCurrency = fiatCurrency;
    }
    public Date getPayoutDate() {
        return payoutDate;
    }
    public void setPayoutDate(Date payoutDate) {
        this.payoutDate = payoutDate;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public PayoutStatus getStatus() {
        return status;
    }

    public void setStatus(PayoutStatus status) {
        this.status = status;
    }

    // public String getWalletAddress() {
    //     return walletAddress;
    // }

    // public void setWalletAddress(String walletAddress) {
    //     this.walletAddress = walletAddress;
    // }
    
}
