package com.diamond.diamond.dtos.payments.new_payments;

import java.util.UUID;

public class NewInvoiceDto extends NewPaymentDto {
    private UUID customerId;
    private String accountComments;

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
