package com.diamond.diamond.entities.payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.diamond.diamond.types.FiatCurrency;
import com.diamond.diamond.types.PayoutStatus;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="payouts")
public class Payout {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable=false)
    private UUID id;

    // @ManyToOne
    // @JoinColumn(name="account_id", referencedColumnName="id")
    // private Account account;
    @Column(name="account_id", nullable=false)
    private UUID accountId;

    // @ManyToMany
    // @JoinTable(
    //     name = "payout_wallets", // Optional: Custom name for the join table
    //     joinColumns = @JoinColumn(name = "payout_id"),
    //     inverseJoinColumns = @JoinColumn(name = "wallet_id")
    // )
    // private List<AccountWallet> offrampWallets;

    // @ManyToOne
    // @JoinColumn(name="wallet_id", referencedColumnName="id")
    // private AccountWallet offrampWallet;
    @Column(name="offramp_wallet_id", nullable=false)
    private UUID offrampWalletId;

    // amount in tokens being off-ramped
    @Column(name="amount")
    @Positive
    private BigDecimal amount;

    // The stablecoin currency being converted into fiat currency
    @Enumerated(EnumType.STRING)
    @Column(name="stablecoin_currency")
    private StablecoinCurrency stablecoinCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name="fiat_currency")
    private FiatCurrency fiatCurrency;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    // Represents the time when the payout was completed
    @Column(name = "payout_date")
    private Date payoutDate;

    // indicates whether the payout was succeeded, failed, cancelled, pending, or fully reversed (undone)
    @Column(name="status")
    @Enumerated
    private PayoutStatus status;

    public Payout() {}

    public Payout(UUID accountId, UUID offrampWalletId, BigDecimal amount, StablecoinCurrency stablecoinCurrency, FiatCurrency fiatCurrency) {
        this.accountId = accountId;
        this.offrampWalletId = offrampWalletId;
        this.amount = amount;
        this.stablecoinCurrency = stablecoinCurrency;
        this.fiatCurrency = fiatCurrency;
        this.status = PayoutStatus.PENDING;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public StablecoinCurrency getStablecoinCurrency() {
        return stablecoinCurrency;
    }

    public void setStablecoinCurrency(StablecoinCurrency stablecoinCurrency) {
        this.stablecoinCurrency = stablecoinCurrency;
    }

    public FiatCurrency getFiatCurrency() {
        return fiatCurrency;
    }

    public void setFiatCurrency(FiatCurrency fiatCurrency) {
        this.fiatCurrency = fiatCurrency;
    }

    public Date getPayoutDate() {
        return payoutDate;
    }

    public void setPayoutDate(Date payoutDate) {
        this.payoutDate = payoutDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public PayoutStatus getStatus() {
        return status;
    }

    public void setStatus(PayoutStatus status) {
        this.status = status;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getOfframpWalletId() {
        return offrampWalletId;
    }

    public void setOfframpWalletId(UUID offrampWalletId) {
        this.offrampWalletId = offrampWalletId;
    }
    
}