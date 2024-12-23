package com.diamond.diamond;

import java.util.List;
import java.time.Instant;

public class VendorWallet {

    private String address;
    private String privateKey;
    private List<String> recoveryPhrase;
    private String secondaryKey;
    private String tertiaryKey;
    private long walletId;
    private String walletName;
    private Blockchain chain;
    private double usdcBalance;
    private double eurcBalance;
    private double solBalance;
    private double baseEthBalance;
    private long dateCreated;

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
}
