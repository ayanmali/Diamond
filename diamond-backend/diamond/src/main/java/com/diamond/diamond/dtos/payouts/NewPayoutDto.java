package com.diamond.diamond.dtos.payouts;

import java.util.Date;
import java.util.UUID;

import com.diamond.diamond.types.FiatCurrency;
import com.diamond.diamond.types.StablecoinCurrency;

public class NewPayoutDto {
    private UUID accountId;
    private UUID walletId;
    private Double amount;
    private StablecoinCurrency stablecoinCurrency;
    private FiatCurrency fiatCurrency;

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
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
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

    
}
