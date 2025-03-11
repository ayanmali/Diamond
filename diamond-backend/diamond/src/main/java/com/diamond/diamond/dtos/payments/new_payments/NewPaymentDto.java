/*
 * To be used when requesting to create a new Payment
 */
package com.diamond.diamond.dtos.payments.new_payments;

import java.util.List;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

public abstract class NewPaymentDto {

    //private UUID id;
    private Double amount;
    private String accountId;
    private StablecoinCurrency currency;
    private Blockchain chain;
    private List<UUID> accountWalletIds;

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
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
    public List<UUID> getAccountWalletIds() {
        return accountWalletIds;
    }
    public void setAccountWalletIds(List<UUID> accountWalletIds) {
        this.accountWalletIds = accountWalletIds;
    }

}
