package com.diamond.diamond.payments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.diamond.diamond.transactions.CustomerWallet;

/*
 * Customer class used for invoices and subscription payments (ex. B2B)
 */
public class BillingCustomer implements Customer {

    // private final long id;
    private String name;
    private String email;
    private final List<CustomerWallet> wallets;

    /* Constructor methods */
    public BillingCustomer(String name, String email) {
        // this.id = 0;
        this.name = name;
        this.email = email;
        this.wallets = new ArrayList<>();
    }

    /* Alternate constructor method */
    public BillingCustomer(String email) {
        // this.id = 0;
        this.email = email;
        this.wallets = new ArrayList<>();
    }

    public void setCustomerEmail(String email) {
        this.email = email;
    }

    public void setCustomerName(String name) {
        this.name = name;
    }

    public void addCustomerWallet(CustomerWallet wallet) {
        // Setting the priority for the new wallet
        wallet.setWalletPriority(wallets.size());
        // Adding the wallet to this customer's wallets list
        wallets.add(wallet);
        // Sorting the Customer's wallets list by priority
        wallets.sort(Comparator.comparing(CustomerWallet::getWalletPriority));
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
