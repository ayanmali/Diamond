package com.diamond.diamond.dtos.payouts;

import java.math.BigDecimal;
import java.util.UUID;

import com.diamond.diamond.types.FiatCurrency;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

public class NewPayoutDto {
    private UUID accountId;
    private UUID walletId;

    @DecimalMin(value="1.00", inclusive=true)
    @DecimalMax(value="10000.000, inclusive=true")
    private BigDecimal amount;
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

    
}
