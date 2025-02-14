/*
 * Promo codes can be applied to checkout payments and payment links
 */
package com.diamond.diamond.entities.payments;

import java.util.Date;

import com.diamond.diamond.entities.Vendor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="promo_codes")
public class PromoCode {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="vendor_id", nullable=false)
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name="payment_id", nullable=false)
    private Payment payment;

    @Column(nullable=false, unique=true)
    private String code;

    private Date expiration;

    @Column(nullable=false)
    private Double discount;

    public PromoCode() {}

    public PromoCode(Vendor vendor, Payment payment, String code, Double discount) {
        this.vendor = vendor;
        this.payment = payment;
        this.code = code;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendorId(Vendor vendor) {
        this.vendor = vendor;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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
