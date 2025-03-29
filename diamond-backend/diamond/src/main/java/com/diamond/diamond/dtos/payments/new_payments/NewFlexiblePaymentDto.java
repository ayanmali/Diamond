package com.diamond.diamond.dtos.payments.new_payments;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.SimplePaymentCategory;
import com.diamond.diamond.types.StablecoinCurrency;

/*
 * Defines a user's request for creating a new flex payment
 * Includes same fields as a simple payment, but amount is 0 and promo codes are not used
 */
public class NewFlexiblePaymentDto extends NewSimplePaymentDto {
    public NewFlexiblePaymentDto(BigDecimal minAmount, UUID accountId, List<StablecoinCurrency> currencies, Blockchain chain, List<UUID> accountWalletIds,
    Boolean hasMaxNumberOfPayments, Integer maxNumberOfPayments, /*Boolean enablePromoCodes, List<Long> validPromoCodeIds,*/ SimplePaymentCategory category) {
        super(minAmount, accountId, currencies, chain, accountWalletIds, hasMaxNumberOfPayments, maxNumberOfPayments, false, null, SimplePaymentCategory.FLEXIBLE);
    }
}
