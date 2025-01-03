package com.diamond.diamond.payments;

import com.diamond.diamond.transactions.CustomerWallet;

/*
 * Customer class used for simple payments en masse (ex. B2C)
 */
public class BasicCustomer implements Customer {

    private String email;
    private final CustomerWallet wallet;

    public BasicCustomer(String email, CustomerWallet wallet) {
        this.email = email;
        this.wallet = wallet;
    }

    public String getEmail() {
        return email;
    }

    public CustomerWallet getCustomerWallet() {
        return wallet;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
