package com.diamond.diamond.payments;

import java.util.ArrayList;
import java.util.List;

import com.diamond.diamond.CustomerWallet;

public class InvoiceCustomer {

    // private final long id;
    private String name;
    private String email;
    private final List<CustomerWallet> wallets;

    /* Constructor methods */
    public InvoiceCustomer(String name, String email) {
        // this.id = 0;
        this.name = name;
        this.email = email;
        this.wallets = new ArrayList<>();
    }

    /* Alternate constructor method */
    public InvoiceCustomer(String email) {
        // this.id = 0;
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
