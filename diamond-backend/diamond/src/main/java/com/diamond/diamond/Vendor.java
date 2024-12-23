package com.diamond.diamond;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Vendor {

    private long id;
    private List<VendorWallet> wallets;
    private String businessName;
    private double totalUsdcBalance;
    private double totalEurcBalance;
    private double totalSolBalance;
    private double totalBaseEthBalance;
    private String primaryEmail;
    private long dateCreated;

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

}
