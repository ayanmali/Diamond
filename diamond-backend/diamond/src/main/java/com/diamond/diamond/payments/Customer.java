package com.diamond.diamond.payments;

import com.diamond.diamond.transactions.CustomerWallet;

public abstract class Customer {
    private String email;
    private CustomerWallet wallet;

    public Customer(String email, CustomerWallet wallet) {
        this.email = email;
        this.wallet = wallet;
    }

    /*
     * Allows customers to send crypto from their wallet to the vendor's to complete a payment
     */
    public void pay(Payment payment) {
        payment.setCustomer(this);


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerWallet getWallet() {
        return wallet;
    }

    public void setWallet(CustomerWallet wallet) {
        // connect and authorize the specified wallet
        this.wallet = wallet;
    }

}
