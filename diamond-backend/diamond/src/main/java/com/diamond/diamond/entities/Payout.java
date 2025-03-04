package com.diamond.diamond.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.diamond.diamond.types.FiatCurrency;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="payouts")
public class Payout {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable=false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="vendor_id", referencedColumnName="id")
    private Vendor vendor;

    @ManyToMany
    @JoinTable(
        name = "payout_wallets", // Optional: Custom name for the join table
        joinColumns = @JoinColumn(name = "payout_id"),
        inverseJoinColumns = @JoinColumn(name = "wallet_id")
    )
    private List<VendorWallet> offrampWallets;

    // amount in tokens being off-ramped
    @Column(name="amount")
    private Double amount;

    // The stablecoin currency being converted into fiat currency
    @Enumerated(EnumType.STRING)
    @Column(name="stablecoin_currency")
    private StablecoinCurrency stablecoinCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name="fiat_currency")
    private FiatCurrency fiatCurrency;

    @CreationTimestamp
    @Column(updatable = false, name = "payout_date")
    private Date payoutDate;

    // indicates whether the payout was fully reversed (undone) or not
    @Column(name="reversed")
    private Boolean reversed;

    public Payout() {}

    public Payout(Vendor vendor, List<VendorWallet> offrampWallets, Double amount, StablecoinCurrency stablecoinCurrency, FiatCurrency fiatCurrency) {
        this.vendor = vendor;
        this.offrampWallets = offrampWallets;
        this.amount = amount;
        this.stablecoinCurrency = stablecoinCurrency;
        this.fiatCurrency = fiatCurrency;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public Boolean getReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    public List<VendorWallet> getOfframpWallets() {
        return offrampWallets;
    }

    public void setOfframpWallets(List<VendorWallet> offrampWallets) {
        this.offrampWallets = offrampWallets;
    }

    

}
