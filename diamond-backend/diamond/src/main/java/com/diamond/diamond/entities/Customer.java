package com.diamond.diamond.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.diamond.diamond.entities.payments.Payment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable=false)
    private UUID id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String email;

    @OneToMany(mappedBy="address", cascade=CascadeType.ALL)
    private List<CustomerWallet> wallets;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="total_spend", precision=8, scale=2)
    private BigDecimal totalSpend;

    @Column(name="total_payments")
    private Integer totalPayments;

    @Column(name="last_payment_date")
    private Date lastPaymentDate;

    public Customer() {}

    public Customer(String email, CustomerWallet wallet) {
        this.email = email;
        this.wallets.add(wallet);
    }

    public Customer(String email, List<CustomerWallet> wallets) {
        this.email = email;
        this.wallets = wallets;
    }

    /*
     * Allows customers to send crypto from their wallet to the vendor's to complete a payment
     */
    public void pay(Payment payment) {
        payment.setCustomer(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CustomerWallet> getWallets() {
        return wallets;
    }

    public void addWallet(CustomerWallet wallet) {
        // connect and authorize the specified wallet
        this.wallets.add(wallet);
    }

    public void deleteWallet(CustomerWallet wallet) throws Exception {
        if (wallets.contains(wallet)) {
            wallets.remove(wallet);
        } else {
            throw new Exception("Not found");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(BigDecimal totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Integer getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Integer totalPayments) {
        this.totalPayments = totalPayments;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

}
