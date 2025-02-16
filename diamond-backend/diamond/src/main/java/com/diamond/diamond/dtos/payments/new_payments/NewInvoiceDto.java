package com.diamond.diamond.dtos.payments.new_payments;

import java.util.UUID;

public class NewInvoiceDto extends NewPaymentDto {
    private UUID customerId;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
