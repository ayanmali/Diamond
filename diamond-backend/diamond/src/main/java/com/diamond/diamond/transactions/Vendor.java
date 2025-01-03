
package com.diamond.diamond;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class Vendor {

    private final long id;
    private final List<VendorWallet> wallets;
    private String businessName;
    private double totalUSDCBalance;
    private double totalEURCBalance;
    private double totalUSDTBalance;
    private double totalSOLBalance;
    private double totalBaseETHBalance;
    private final String primaryEmail;
    private final long dateCreated;

    /* Constructor method */
    public Vendor(String businessName, String email) {
        this.businessName = businessName;
        this.primaryEmail = email;

        this.id = 0;
        this.wallets = new ArrayList<>();
        this.totalUSDCBalance = 0;
        this.totalEURCBalance = 0;
        this.totalSOLBalance = 0;
        this.totalBaseETHBalance = 0;
        this.dateCreated = Instant.now().toEpochMilli();
    }

    public void addWallet(VendorWallet wallet) {
        this.wallets.add(wallet);
    }

    public long getId() {
        return id;
    }

    public List<VendorWallet> getWallets() {
        return wallets;
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
}
