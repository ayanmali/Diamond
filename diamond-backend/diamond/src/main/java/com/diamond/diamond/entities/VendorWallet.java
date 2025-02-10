package com.diamond.diamond.entities;

// import java.time.Instant;
// import java.util.Arrays;
// import java.util.List;

// public class VendorWallet implements Wallet {

//     private final long id;
//     private final String address;
//     private final String privateKey;
//     private final List<String> recoveryPhrase;
//     private String secondaryKey;
//     private String tertiaryKey;
//     private String walletName;
//     private final long vendorId;
//     private final Blockchain chain;
//     private double USDCBalance;
//     private double EURCBalance;
//     private double USDTBalance;
//     private double spotTokenBalance;
//     private final long dateCreated;

//     /* Constructor Method */
//     public VendorWallet(String address, String walletName, long vendorId, Blockchain chain) {
//         this.address = address;
//         this.walletName = walletName;
//         this.vendorId = vendorId;
//         this.chain = chain;

//         this.privateKey = "private_key";
//         this.recoveryPhrase = Arrays.asList("recovery", "phrase");
//         this.id = 0;
//         this.USDCBalance = 0;
//         this.EURCBalance = 0;
//         this.spotTokenBalance = 0;
//         this.dateCreated = Instant.now().toEpochMilli();
//     }

//     @Override
//     public String getAddress() {
//         return address;
//     }

//     public String getPrivateKey() {
//         return privateKey;
//     }

//     public List<String> getRecoveryPhrase() {
//         return recoveryPhrase;
//     }

//     public String getSecondaryKey() {
//         return secondaryKey;
//     }

//     public String getTertiaryKey() {
//         return tertiaryKey;
//     }

//     public long getId() {
//         return id;
//     }

//     public String getWalletName() {
//         return walletName;
//     }

//     public long getVendorId() {
//         return vendorId;
//     }

//     @Override
//     public Blockchain getChain() {
//         return chain;
//     }

//     public double getUSDCBalance() {
//         return USDCBalance;
//     }

//     public double getEURCBalance() {
//         return EURCBalance;
//     }

//     public double getUSDTBalance() {
//         return USDTBalance;
//     }

//     public double getSpotTokenBalance() {
//         return spotTokenBalance;
//     }

//     public long getDateCreated() {
//         return dateCreated;
//     }

//     public void setWalletName(String walletName) {
//         this.walletName = walletName;
//     }

//     public void setUSDCBalance(double usdcBalance) {
//         this.USDCBalance = usdcBalance;
//     }

//     public void setEURCBalance(double eurcBalance) {
//         this.EURCBalance = eurcBalance;
//     }

//     public void setUSDTBalance(double usdtBalance) {
//         this.USDTBalance = usdtBalance;
//     }

//     public void setSpotTokenBalance(double spotTokenBalance) {
//         this.spotTokenBalance = spotTokenBalance;
//     }

//     public void cashOut(double amount, StablecoinCurrency currency) {
//         // todo: add logic for cashing out stablecoins to a bank account
//     }

// }

// package com.diamond.diamond.entities;


import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;
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

@Entity
@Table(name="vendor_wallets")
public class VendorWallet implements Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(unique=true, nullable=false, updatable=false)
    private String address;

    @Column(updatable=false, nullable=false)
    private Blockchain chain;

    @Column(name="wallet_name")
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
    @JoinColumn(name="vendor_id", nullable=false)
    private Vendor vendor;

    public VendorWallet() {}

    public VendorWallet(String address, String walletName, Vendor vendor, Blockchain chain) {
        this.address = address;
        this.walletName = walletName;
        this.vendor = vendor;
        this.chain = chain;
        this.createdAt = new Date();
    }

    public void cashOut(Double amount, StablecoinCurrency currency) {
        // todo: add logic for cashing out stablecoins to a bank account
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

    public Vendor getVendor() {
        return vendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setName(String name) {
        this.walletName = name;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public WalletStatus getStatus() {
        return status;
    }

    public void setStatus(WalletStatus status) {
        this.status = status;
    }

}