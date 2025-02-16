package com.diamond.diamond.dtos.payments.fetch_payments;

import java.util.Date;
import java.util.UUID;

public class FetchInvoiceDto extends FetchPaymentDto {
    private UUID customerId;
    private Date timeSent;
    private Date timePaid;
    private String vendorComments;
    private String customerComments;
    private Date createdAt;
    private Date updatedAt;
    
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
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
