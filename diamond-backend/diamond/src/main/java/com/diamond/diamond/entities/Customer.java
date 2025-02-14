package com.diamond.diamond.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name="vendor_id", nullable=false)
    private Vendor vendor;

    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

    // @Column(name="total_spend")
    // private Double totalSpend;

    // @Column(name="total_payments")
    // private Integer totalPayments;

    // @Column(name="last_payment_date")
    // private Date lastPaymentDate;

    public Customer() {}

    public Customer(Vendor vendor, String name, String email, CustomerWallet wallet) {
        this.vendor = vendor;
        this.name = name;
        this.email = email;
        this.wallets = new ArrayList<>();
        this.wallets.add(wallet);
    }

    public Customer(Vendor vendor, String name, String email, List<CustomerWallet> wallets) {
        this.vendor = vendor;
        this.name = name;
        this.email = email;
        this.wallets = wallets;
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

    // public Double getTotalSpend() {
    //     return totalSpend;
    // }

    // public void setTotalSpend(Double totalSpend) {
    //     this.totalSpend = totalSpend;
    // }

    // public Integer getTotalPayments() {
    //     return totalPayments;
    // }

    // public void setTotalPayments(Integer totalPayments) {
    //     this.totalPayments = totalPayments;
    // }

    // public Date getLastPaymentDate() {
    //     return lastPaymentDate;
    // }

    // public void setLastPaymentDate(Date lastPaymentDate) {
    //     this.lastPaymentDate = lastPaymentDate;
    // }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

}
