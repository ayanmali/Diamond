package com.diamond.diamond;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Vendor {

    private final long id;
    private final List<VendorWallet> wallets;
    private String businessName;
    private double totalUsdcBalance;
    private double totalEurcBalance;
    private double totalSolBalance;
    private double totalBaseEthBalance;
    private final String primaryEmail;
    private final long dateCreated;

    /* Constructor method */
    public Vendor(String businessName, String email) {
        this.businessName = businessName;
        this.primaryEmail = email;

        this.id = 0;
        this.wallets = new ArrayList<>();
        this.businessName = businessName;
        this.totalUsdcBalance = 0;
        this.totalEurcBalance = 0;
        this.totalSolBalance = 0;
        this.totalBaseEthBalance = 0;
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

    public double getTotalUsdcBalance() {
        return totalUsdcBalance;
    }

    public double getTotalEurcBalance() {
        return totalEurcBalance;
    }

    public double getTotalSolBalance() {
        return totalSolBalance;
    }

    public double getTotalBaseEthBalance() {
        return totalBaseEthBalance;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setTotalUsdcBalance(double totalUsdcBalance) {
        this.totalUsdcBalance = totalUsdcBalance;
    }

    public void setTotalEurcBalance(double totalEurcBalance) {
        this.totalEurcBalance = totalEurcBalance;
    }

    public void setTotalSolBalance(double totalSolBalance) {
        this.totalSolBalance = totalSolBalance;
    }

    public void setTotalBaseEthBalance(double totalBaseEthBalance) {
        this.totalBaseEthBalance = totalBaseEthBalance;
    }
}
