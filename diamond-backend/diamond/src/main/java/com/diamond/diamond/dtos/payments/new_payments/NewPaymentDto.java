/*
 * To be used when requesting to create a new Payment
 */
package com.diamond.diamond.dtos.payments.new_payments;

import java.util.List;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

public abstract class NewPaymentDto {

    //private UUID id;
    private Double amount;
    private String accountId;
    private StablecoinCurrency currency;
    private Blockchain chain;
    private List<Long> accountWalletIds;

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
    public List<Long> getAccountWalletIds() {
        return accountWalletIds;
    }
    public void setAccountWalletIds(List<Long> accountWalletIds) {
        this.accountWalletIds = accountWalletIds;
    }

}
