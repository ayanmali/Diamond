package com.diamond.diamond.entities.user;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.Wallet;
import com.diamond.diamond.types.WalletStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="account_wallets")
public class AccountWallet implements Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    // public key
    @Column(unique=true, nullable=false, updatable=false)
    @Pattern(regexp="^(0x[a-fA-F0-9]{40}|[1-9A-HJ-NP-Za-km-z]{32,44})$")
    private String address;

    @Column(unique=true, nullable=false, updatable=false)
    private String encryptedPrivateKey;

    @Column(updatable=false, nullable=false)
    @Enumerated(EnumType.STRING)
    private Blockchain chain;

    @Column(name="wallet_name")
    @Size(min=1, max=50)
    private String walletName;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private WalletStatus status;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    //@ManyToOne
    //@JoinColumn(name="account_id", referencedColumnName="id", nullable=false)
    //private Account account;
    @Column(name="account_id", nullable=false)
    private UUID accountId;

    // join table stores the account wallets used for a given payment
    // @ManyToMany(mappedBy = "walletDistribution", cascade=CascadeType.ALL)
    // private List<Payment> payments;
    //private List<UUID> paymentIds;

    // @ManyToMany(mappedBy = "offrampWallet")
    // private List<Payout> payouts;

    public AccountWallet() {}

    public AccountWallet(String address, String encryptedPrivateKey, String walletName, UUID accountId, Blockchain chain) {
        this.address = address;
        this.encryptedPrivateKey = encryptedPrivateKey;
        this.walletName = walletName;
        this.accountId = accountId;
        this.chain = chain;
        this.createdAt = new Date();
        this.status = WalletStatus.ACTIVE;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public Blockchain getChain() {
        return chain;
    }

    public String getWalletName() {
        return walletName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setName(String name) {
        this.walletName = name;
    }

    public WalletStatus getStatus() {
        return status;
    }

    public void setStatus(WalletStatus status) {
        this.status = status;
    }

    // public List<Payment> getPayments() {
    //     return payments;
    // }

    // public void setPayments(List<Payment> payments) {
    //     this.payments = payments;
    // }

    // public List<Payout> getPayouts() {
    //     return payouts;
    // }

    // public void setPayouts(List<Payout> payouts) {
    //     this.payouts = payouts;
    // }

    public String getEncryptedPrivateKey() {
        return encryptedPrivateKey;
    }

    public void setEncryptedPrivateKey(String encryptedPrivateKey) {
        this.encryptedPrivateKey = encryptedPrivateKey;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

}