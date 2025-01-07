package com.diamond.diamond.payments;

import com.diamond.diamond.transactions.CustomerWallet;

/*
 * Customer class used for simple payments to be paid by a large number of people (ex. B2C)
 */
public class BasicCustomer extends Customer {

    public BasicCustomer(String email, CustomerWallet wallet) {
        super(email, wallet);
    }

}
