package com.diamond.diamond;

import java.util.List;
import java.util.Arrays;
import java.time.Instant;

public class VendorWallet {

    private final String address;
    private final String privateKey;
    private final List<String> recoveryPhrase;
    private String secondaryKey;
    private String tertiaryKey;
    private final long walletId;
    private String walletName;
    private final Blockchain chain;
    private double usdcBalance;
    private double eurcBalance;
    private double solBalance;
    private double baseEthBalance;
    private final long dateCreated;

    /* Constructor Method */
    public VendorWallet(String address, String walletName, Blockchain chain) {
        this.address = address;
        this.walletName = walletName;
        this.chain = chain;

        this.privateKey = "private_key";
        this.recoveryPhrase = Arrays.asList("recovery", "phrase");
        this.walletId = 0;
        this.usdcBalance = 0;
        this.eurcBalance = 0;
        this.solBalance = 0;
        this.baseEthBalance = 0;
        this.dateCreated = Instant.now().toEpochMilli();
    }

    public String getAddress() {
        return address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public List<String> getRecoveryPhrase() {
        return recoveryPhrase;
    }

    public String getSecondaryKey() {
        return secondaryKey;
    }

    public String getTertiaryKey() {
        return tertiaryKey;
    }

    public long getWalletId() {
        return walletId;
    }

    public String getWalletName() {
        return walletName;
    }

    public Blockchain getChain() {
        return chain;
    }

    public double getUsdcBalance() {
        return usdcBalance;
    }

    public double getEurcBalance() {
        return eurcBalance;
    }

    public double getSolBalance() {
        return solBalance;
    }

    public double getBaseEthBalance() {
        return baseEthBalance;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public void setUsdcBalance(double usdcBalance) {
        this.usdcBalance = usdcBalance;
    }

    public void setEurcBalance(double eurcBalance) {
        this.eurcBalance = eurcBalance;
    }

    public void setSolBalance(double solBalance) {
        this.solBalance = solBalance;
    }

    public void setBaseEthBalance(double baseEthBalance) {
        this.baseEthBalance = baseEthBalance;
    }
}
