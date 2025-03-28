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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(unique=true, nullable=false, updatable=false)
    @Pattern(regexp="^(0x[a-fA-F0-9]{40}|[1-9A-HJ-NP-Za-km-z]{32,44})$")
    private String address;

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

    @ManyToOne
    @JoinColumn(name="account_id", referencedColumnName="id", nullable=false)
    private Account account;

    // @ManyToMany(mappedBy = "walletDistribution", cascade=CascadeType.ALL)
    // private List<Payment> payments;

    // @ManyToMany(mappedBy = "offrampWallet")
    // private List<Payout> payouts;

    public AccountWallet() {}

    public AccountWallet(String address, String walletName, Account account, Blockchain chain) {
        this.address = address;
        this.walletName = walletName;
        this.account = account;
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

    public Account getAccount() {
        return account;
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

    public void setAccount(Account account) {
        this.account = account;
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

}