package com.diamond.diamond.entities.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable=false)
    private UUID id;

    @Column(nullable=false)
    @Size(min=1, max=50)
    private String name;

    @Column(nullable=false)
    @Email
    private String email;

    //@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
    //private List<CustomerWallet> wallets;
    @ElementCollection
    @CollectionTable(name = "customer_wallet_ids", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "wallet_ids")
    private List<UUID> walletIds;

    //@ManyToOne
    //@JoinColumn(name="account_id", referencedColumnName="id", nullable=false)
    //private Account account;
    @Column(name="account_id", nullable=false)
    private UUID accountId;

    @ElementCollection
    @CollectionTable(
        name = "customer_metadata",
        joinColumns = @JoinColumn(name = "customer_id")
    )
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> metadata;

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

    public Customer(UUID accountId, String name, String email, CustomerWallet wallet) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.walletIds = new ArrayList<>();
        this.walletIds.add(wallet.getId());

        this.metadata = new HashMap<>();
    }

    public Customer(UUID accountId, String name, String email, UUID customerWalletId) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.walletIds = new ArrayList<>();
        this.walletIds.add(customerWalletId);

        this.metadata = new HashMap<>();
    }

    public Customer(UUID accountId, String name, String email) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;

        this.walletIds = new ArrayList<>();
        this.metadata = new HashMap<>();
    }

    public Customer(UUID accountId, String name, String email, List<UUID> customerWalletIds) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.walletIds = customerWalletIds;

        this.metadata = new HashMap<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addWallet(UUID walletId) {
        // connect and authorize the specified wallet
        this.walletIds.add(walletId);
    }

    public void deleteWallet(UUID walletId) throws Exception {
        if (walletIds.contains(walletId)) {
            walletIds.remove(walletId);
        } else {
            throw new Exception("Wallet ID " + walletId + " not found");
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

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public List<UUID> getWalletIds() {
        return walletIds;
    }

    public void setWalletIds(List<UUID> walletIds) {
        this.walletIds = walletIds;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

}
