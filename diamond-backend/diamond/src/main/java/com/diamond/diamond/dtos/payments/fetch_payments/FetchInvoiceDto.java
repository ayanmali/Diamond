package com.diamond.diamond.dtos.payments.fetch_payments;

import java.util.Date;
import java.util.UUID;

import com.diamond.diamond.entities.payments.Payment;

public class FetchInvoiceDto extends FetchPaymentDto {
    private UUID customerId;
    private Date timeSent;
    private Date timePaid;
    private String vendorComments;
    private String customerComments;

    public FetchInvoiceDto() {}
    public FetchInvoiceDto(Payment payment) {
        super(payment);
    }

    public UUID getCustomerId() {
        return customerId;
    }
    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
    public Date getTimeSent() {
        return timeSent;
    }
    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }
    public Date getTimePaid() {
        return timePaid;
    }
    public void setTimePaid(Date timePaid) {
        this.timePaid = timePaid;
    }
    public String getVendorComments() {
        return vendorComments;
    }
    public void setVendorComments(String vendorComments) {
        this.vendorComments = vendorComments;
    }
    public String getCustomerComments() {
        return customerComments;
    }
    public void setCustomerComments(String customerComments) {
        this.customerComments = customerComments;
    }

}
