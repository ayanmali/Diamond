/*
 * Defines a payment without any fixed amount to be paid (e.g. donations and tips)
 */
package com.diamond.diamond.entities.payments;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.SimplePaymentCategory;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="flexible_payments")
// Inherits from the Simple Payment class. Does not include an explicit 'amount' value.
public class FlexiblePayment extends SimplePayment {
    public FlexiblePayment(BigDecimal minAmount, UUID accountId, List<StablecoinCurrency> acceptedCurrencies, Blockchain chain, List<UUID> accountWalletIds, Boolean hasMaxNumberOfPayments, Integer maxNumberOfPayments) {
        super(minAmount, accountId, chain, accountWalletIds, hasMaxNumberOfPayments, maxNumberOfPayments, SimplePaymentCategory.FLEXIBLE, acceptedCurrencies);
    }

    public FlexiblePayment(BigDecimal minAmount, UUID accountId, List<StablecoinCurrency> acceptedCurrencies, Blockchain chain, UUID accountWalletId, Boolean hasMaxNumberOfPayments, Integer maxNumberOfPayments) {
        super(minAmount, accountId, chain, accountWalletId, hasMaxNumberOfPayments, maxNumberOfPayments, SimplePaymentCategory.FLEXIBLE, acceptedCurrencies);
    }

}