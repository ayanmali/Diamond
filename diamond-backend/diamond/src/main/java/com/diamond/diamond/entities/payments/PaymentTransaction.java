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
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/*
 * Used for CheckoutPayment transactions and LinkPayment Transactions
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PaymentTransaction {
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

    /*
     * Hash for signing/approving the transaction in the user's wallet
     */
    @Column(name="sign_hash", unique=true)
    private String signHash;

    /*
     * Hash for the token transfer
     */
    @Column(name="tx_hash", unique=true)
    private String txHash;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToMany(mappedBy="id")
    private List<PromoCode> codesApplied;

    public PaymentTransaction() {}

    public PaymentTransaction(Payment payment, Customer customer, Double revenue) {
        this.payment = payment;
        this.customer = customer;
        this.revenue = revenue;
        this.status = PaymentStatus.PENDING;
    }

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

    public String getSignHash() {
        return signHash;
    }

    public void setSignHash(String signHash) {
        this.signHash = signHash;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

}
