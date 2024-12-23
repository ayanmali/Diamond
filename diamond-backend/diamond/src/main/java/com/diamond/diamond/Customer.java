package com.diamond.diamond;

import java.util.List;

public class Customer {

    private long id;
    private String name;
    private String email;
    private List<CustomerWallet> wallets;

    /* Constructor method */
    public Customer(String name, String email) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.wallets = new ArrayList<>();
    }

    public void editCustomerEmail(String newEmail) {
        this.email = newEmail;
    }

    public void editCustomerName(String newName) {
        this.name = newName;
    }

    public void addCustomerWallet(CustomerWallet wallet) {
        this.wallets.add(wallet);
    }

}
