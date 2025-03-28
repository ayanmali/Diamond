/*
 * Defines a payment without any fixed amount to be paid (e.g. donations and tips)
 */
package com.diamond.diamond.entities.payments;

import java.util.List;

import com.diamond.diamond.entities.user.Account;
import com.diamond.diamond.entities.user.AccountWallet;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.SimplePaymentCategory;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="flexible_payments")
// Inherits from the Simple Payment class. Does not include an explicit 'amount' value.
public class FlexiblePayment extends SimplePayment {
    public FlexiblePayment(Account account, List<StablecoinCurrency> acceptedCurrencies, Blockchain chain, List<AccountWallet> accountWallets, Boolean hasMaxNumberOfPayments, Integer maxNumberOfPayments) {
        super(null, account, chain, accountWallets, hasMaxNumberOfPayments, maxNumberOfPayments, false, null, SimplePaymentCategory.FLEXIBLE, acceptedCurrencies);
    }

}