package com.diamond.diamond.dtos.customer;

import java.util.UUID;

public class NewCustomerDto {
    private String name;
    private String email;
    private UUID accountId;
    //private List<CustomerWallet> wallets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // public List<CustomerWallet> getWallets() {
    //     return wallets;
    // }

    // public void setWallets(List<CustomerWallet> wallets) {
    //     this.wallets = wallets;
    // }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }
}
