package com.diamond.diamond.payments;

import com.diamond.diamond.CustomerWallet;

public class BasicCustomer {

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

}
