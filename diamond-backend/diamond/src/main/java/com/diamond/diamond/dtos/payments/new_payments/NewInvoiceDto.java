package com.diamond.diamond.dtos.payments.new_payments;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

/*
 * Defines a user's request to create an Invoice
 */
public class NewInvoiceDto extends NewPaymentDto {
    private UUID customerId;
    private String accountComments;

    public NewInvoiceDto(BigDecimal amount, UUID accountId, List<StablecoinCurrency> currencies, Blockchain chain, List<UUID> accountWalletIds,
                            UUID customerId, String accountComments) {
        super(amount, accountId, currencies, chain, accountWalletIds);
        this.customerId = customerId;
        this.accountComments = accountComments;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getAccountComments() {
        return accountComments;
    }

    public void setAccountComments(String accountComments) {
        this.accountComments = accountComments;
    }
}
