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
import jakarta.validation.constraints.Email;

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
    @Email
    private String email;

    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
    private List<CustomerWallet> wallets;

    @ManyToOne
    @JoinColumn(name="account_id", referencedColumnName="id", nullable=false)
    private Account account;

    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

    // @Column(name="total_spend")
    // private BigDecimal totalSpend;

    // @Column(name="total_payments")
    // private Integer totalPayments;

    // @Column(name="last_payment_date")
    // private Date lastPaymentDate;

    public Customer() {}

    public Customer(Account account, String name, String email, CustomerWallet wallet) {
        this.account = account;
        this.name = name;
        this.email = email;
        this.wallets = new ArrayList<>();
        this.wallets.add(wallet);
    }

    public Customer(Account account, String name, String email) {
        this.account = account;
        this.name = name;
        this.email = email;
    }

    public Customer(Account account, String name, String email, List<CustomerWallet> wallets) {
        this.account = account;
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

    // public BigDecimal getTotalSpend() {
    //     return totalSpend;
    // }

    // public void setTotalSpend(BigDecimal totalSpend) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
