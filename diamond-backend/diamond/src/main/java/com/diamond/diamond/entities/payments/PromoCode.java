package com.diamond.diamond.entities.payments;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="promo_codes")
public class PromoCode {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable=false)
    private UUID id;

    @ManyToOne
    @Column(name="vendor_id", nullable=false)
    private UUID vendorId;

    @ManyToOne
    @Column(name="payment_id", nullable=false)
    private UUID paymentId;

    @Column(nullable=false)
    private String code;

    private Date expiration;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
}
