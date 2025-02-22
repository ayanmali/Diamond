package com.diamond.diamond.dtos.customer;

import java.util.UUID;

public class NewCustomerDto {
    private String name;
    private String email;
    private UUID vendorId;
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

    public UUID getVendorId() {
        return vendorId;
    }

    public void setVendorId(UUID vendorId) {
        this.vendorId = vendorId;
    }
}
