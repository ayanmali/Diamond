package com.diamond.diamond.entities.payments;

import java.util.List;
import java.util.UUID;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.types.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
 * Used for CheckoutPayment transactions and LinkPayment Transactions
 */
@Entity
@Table(name="txns")
public class PaymentTransaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable=false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="payment_id", nullable=false)
    private Payment payment; // the payment configuration created by the vendor that is associated with this transaction
    
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column(name="revenue")
    private Double revenue;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToMany(mappedBy="id")
    private List<PromoCode> codesApplied;

    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Double getRevenue() {
        return revenue;
    }
    public void setTotalRevenue(Double totalRevenue) {
        this.revenue = totalRevenue;
    }
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
    public List<PromoCode> getCodesApplied() {
        return codesApplied;
    }
    public void setCodesApplied(List<PromoCode> codesApplied) {
        this.codesApplied = codesApplied;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
