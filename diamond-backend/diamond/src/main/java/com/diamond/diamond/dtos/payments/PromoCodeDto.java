package com.diamond.diamond.dtos.payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class PromoCodeDto {
    @PositiveOrZero
    private Long id;
    private UUID accountId;
    private UUID paymentId;
    @Size(min=3, max=50)
    private String code;
    @FutureOrPresent
    private Date expiration;
    @DecimalMin(value="0.0", inclusive=false)
    @DecimalMax(value="1.0", inclusive=true)
    private BigDecimal discount;

    public PromoCodeDto() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UUID getAccountId() {
        return accountId;
    }
    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
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
    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

}
