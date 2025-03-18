package com.diamond.diamond.entities;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="shipping_info")
public class ShippingInfo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    // Shipping address
    @Column
    private String address;

    // FedEx, UPS, USPS, etc
    @Column
    private String carrier;

    // Recipient name
    @Column
    private String name;

    // Recipient phone (including extension)
    @Column
    private String phone;

    // Tracking number for a physical product (obtained from delivery service
    // If multiple tracking numbers were generated for a purchase, please separate them with commas
    @Column(name="tracking_number")
    private String trackingNumber;

    // The ID of the payment txn for which this ShippingInfo applies to
    @JoinColumn(name="payment_txn_id", referencedColumnName="id")
    private UUID paymentTxnId;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    public ShippingInfo() {}

    public ShippingInfo(UUID paymentTxnId, String address, String carrier, String name, String phone, String trackingNumber) {
        this.paymentTxnId = paymentTxnId;
        this.address = address;
        this.carrier = carrier;
        this.name = name;
        this.phone = phone;
        this.trackingNumber = trackingNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    } 

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPaymentTxnId() {
        return paymentTxnId;
    }

    public void setPaymentTxnId(UUID paymentTxnId) {
        this.paymentTxnId = paymentTxnId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
    
}
