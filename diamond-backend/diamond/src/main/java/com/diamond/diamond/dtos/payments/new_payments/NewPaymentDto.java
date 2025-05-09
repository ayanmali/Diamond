/*
 * To be used when requesting to create a new Payment
 */
package com.diamond.diamond.dtos.payments.new_payments;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

/*
 * Abstract class that represents a user's request to define any kind of Payment
 */
public abstract class NewPaymentDto {

    //private UUID id;
    @DecimalMin(value="1.00", inclusive=true)
    @DecimalMax(value="10000.00", inclusive=true)
    private BigDecimal amount;
    private UUID accountId;
    private List<StablecoinCurrency> currencies;
    private Blockchain chain;
    private List<UUID> accountWalletIds;

    public NewPaymentDto(BigDecimal amount, UUID accountId, List<StablecoinCurrency> currencies, Blockchain chain, List<UUID> accountWalletIds) {
        this.amount = amount;
        this.accountId = accountId;
        this.currencies = currencies;
        this.chain = chain;
        this.accountWalletIds = accountWalletIds;
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
    public List<UUID> getAccountWalletIds() {
        return accountWalletIds;
    }
    public void setAccountWalletIds(List<UUID> accountWalletIds) {
        this.accountWalletIds = accountWalletIds;
    }

}
