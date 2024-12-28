package com.diamond.diamond;

import java.util.List;
import java.util.ArrayList;

public class Customer {

    // private final long id;
    private String name;
    private String email;
    private final List<CustomerWallet> wallets;

    /* Constructor method */
    public Customer(String name, String email) {
        // this.id = 0;
        this.name = name;
        this.email = email;
        this.wallets = new ArrayList<>();
    }

    public void setCustomerEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setCustomerName(String newName) {
        this.name = newName;
    }

    public void addCustomerWallet(CustomerWallet wallet) {
        this.wallets.add(wallet);
    }

    // public long getId() {
    //     return id;
    // }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<CustomerWallet> getWallets() {
        return wallets;
    }

}
