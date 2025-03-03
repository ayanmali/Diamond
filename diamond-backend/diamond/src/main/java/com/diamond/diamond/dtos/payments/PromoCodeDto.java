package com.diamond.diamond.dtos.payments;

import java.util.Date;
import java.util.UUID;


public class PromoCodeDto {
    private Long id;
    private UUID vendorId;
    private UUID paymentId;
    private String code;
    private Date expiration;
    private Double discount;

    public PromoCodeDto() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UUID getVendorId() {
        return vendorId;
    }
    public void setVendorId(UUID vendorId) {
        this.vendorId = vendorId;
    }
    public UUID getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Date getExpiration() {
        return expiration;
    }
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

}
