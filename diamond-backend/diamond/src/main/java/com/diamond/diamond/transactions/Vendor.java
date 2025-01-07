package com.diamond.diamond.transactions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.diamond.diamond.payments.walletdistribution.PaymentDistributor;

public class Vendor {

    // private final long id;
    // private final List<VendorWallet> wallets;
    private final List<VendorWallet> solWallets;
    private final List<VendorWallet> baseWallets;
    private final List<VendorWallet> bscWallets;
    private String businessName;
    private double totalUSDCBalance;
    private double totalEURCBalance;
    private double totalUSDTBalance;
    private double totalSOLBalance;
    private double totalBaseETHBalance;
    private double totalBNBBalance;
    private final String primaryEmail;
    private PaymentDistributor defaultDistributor;
    private final long dateCreated;

    /* Constructor method */
    public Vendor(String businessName, String email) throws Exception {
        this.businessName = businessName;
        this.primaryEmail = email;

        // this.id = 0;
        // this.wallets = new ArrayList<>();
        this.solWallets = new ArrayList<>();
        this.baseWallets = new ArrayList<>();
        this.bscWallets = new ArrayList<>();
        this.totalUSDCBalance = 0;
        this.totalEURCBalance = 0;
        this.totalSOLBalance = 0;
        this.totalBaseETHBalance = 0;
        this.defaultDistributor = new PaymentDistributor(this, "Default");
        this.dateCreated = Instant.now().toEpochMilli();
    }

    // public void addWallet(VendorWallet wallet) {
    //     this.wallets.add(wallet);
    // }
    // public long getId() {
    //     return id;
    // }
    // public List<VendorWallet> getWallets() {
    //     return wallets;
    // }
    public List<VendorWallet> getSolWallets() {
        return solWallets;
    }

    public List<VendorWallet> getBaseWallets() {
        return baseWallets;
    }

    public List<VendorWallet> getBscWallets() {
        return bscWallets;
    }

    public List<VendorWallet> getWallets(Blockchain chain) {
        switch (chain) {
            case SOL -> {
                return this.getSolWallets();
            }
            case BASE -> {
                return this.getBaseWallets();
            }
            case BSC -> {
                return this.getBscWallets();
            }
            default -> throw new AssertionError();
        }
    }

    public String getBusinessName() {
        return businessName;
    }

    public double getTotalUSDCBalance() {
        return totalUSDCBalance;
    }

    public double getTotalEURCBalance() {
        return totalEURCBalance;
    }

    public double getTotalUSDTBalance() {
        return totalUSDTBalance;
    }

    public double getTotalSOLBalance() {
        return totalSOLBalance;
    }

    public double getTotalBaseETHBalance() {
        return totalBaseETHBalance;
    }

    public double getTotalBNBBalance() {
        return totalBNBBalance;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setTotalUSDCBalance(double totalUSDCBalance) {
        this.totalUSDCBalance = totalUSDCBalance;
    }

    public void setTotalEURCBalance(double totalEURCBalance) {
        this.totalEURCBalance = totalEURCBalance;
    }

    public void setTotalUSDTBalance(double totalUSDTBalance) {
        this.totalUSDTBalance = totalUSDTBalance;
    }

    public void setTotalSOLBalance(double totalSOLBalance) {
        this.totalSOLBalance = totalSOLBalance;
    }

    public void setTotalBaseEthBalance(double totalBaseETHBalance) {
        this.totalBaseETHBalance = totalBaseETHBalance;
    }

    public void setTotalBNBBalance(double totalBNBBalance) {
        this.totalBNBBalance = totalBNBBalance;
    }

    public void addSolWallet(VendorWallet wallet) {
        // add regex validation for the wallet's address to ensure it is on the right chain
        solWallets.add(wallet);
    }

    public void addBaseWallet(VendorWallet wallet) {
        // add regex validation for the wallet's address to ensure it is on the right chain
        baseWallets.add(wallet);
    }

    public void addBscWallet(VendorWallet wallet) {
        // add regex validation for the wallet's address to ensure it is on the right chain
        bscWallets.add(wallet);
    }

    /*
     * Creates a new wallet for the vendor on the specified chain
     */
    public VendorWallet createWallet(Blockchain chain) {
       VendorWallet wallet = new VendorWallet("", "My Wallet", 0, chain);
       addWallet(wallet);
       return wallet;
    }

    public void addWallet(VendorWallet wallet) {
        switch (wallet.getChain()) {
            case SOL -> addSolWallet(wallet);
            case BASE -> addBaseWallet(wallet);
            case BSC -> addBscWallet(wallet);
            default -> throw new AssertionError();
        }
    }

    public PaymentDistributor getDefaultDistributor() {
        return defaultDistributor;
    }

    public void setDefaultDistributor(PaymentDistributor defaultDistributor) {
        this.defaultDistributor = defaultDistributor;
    }

    public void setDefaultDistributor(Map<VendorWallet, Double> mappings) throws Exception {
        this.defaultDistributor = new PaymentDistributor(this, mappings, this.getDefaultDistributor().getDistribution().getName());
    }

    public boolean hasDefaultDistributor() {
        return defaultDistributor.getDistribution().getMappings().size() > 1;
    }

}
