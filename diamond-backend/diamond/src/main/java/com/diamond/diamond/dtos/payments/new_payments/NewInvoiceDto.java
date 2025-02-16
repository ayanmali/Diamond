package com.diamond.diamond.dtos.payments.new_payments;

import java.util.UUID;

public class NewInvoiceDto extends NewPaymentDto {
    private UUID customerId;
    private String vendorComments;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getVendorComments() {
        return vendorComments;
    }

    public void setVendorComments(String vendorComments) {
        this.vendorComments = vendorComments;
    }
}
