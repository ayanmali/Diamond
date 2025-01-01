
package com.diamond.diamond;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;


public class VendorWallet {

    private final long id;
    private final String address;
    private final String privateKey;
    private final List<String> recoveryPhrase;
    private String secondaryKey;
    private String tertiaryKey;
    private String walletName;
    private final long vendorId;
    private final Blockchain chain;
    private double USDCBalance;
    private double EURCBalance;
    private double USDTBalance;
    private double spotTokenBalance;
    private final long dateCreated;

    /* Constructor Method */
    public VendorWallet(String address, String walletName, long vendorId, Blockchain chain) {
        this.address = address;
        this.walletName = walletName;
        this.vendorId = vendorId;
        this.chain = chain;

        this.privateKey = "private_key";
        this.recoveryPhrase = Arrays.asList("recovery", "phrase");
        this.id = 0;
        this.USDCBalance = 0;
        this.EURCBalance = 0;
        this.spotTokenBalance = 0;
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

    public long getId() {
        return id;
    }

    public String getWalletName() {
        return walletName;
    }

    public long getVendorId() {
        return vendorId;
    }

    public Blockchain getChain() {
        return chain;
    }

    public double getUSDCBalance() {
        return USDCBalance;
    }

    public double getEURCBalance() {
        return EURCBalance;
    }

    public double getUSDTBalance() {
        return USDTBalance;
    }

    public double getSpotTokenBalance() {
        return spotTokenBalance;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public void setUSDCBalance(double usdcBalance) {
        this.USDCBalance = usdcBalance;
    }

    public void setEURCBalance(double eurcBalance) {
        this.EURCBalance = eurcBalance;
    }

    public void setUSDTBalance(double usdtBalance) {
        this.USDTBalance = usdtBalance;
    }

    public void setSpotTokenBalance(double spotTokenBalance) {
        this.spotTokenBalance = spotTokenBalance;
    }

}
