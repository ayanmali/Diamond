package com.diamond.diamond;

public class CustomerWallet {

    private String walletAddress;
    private Blockchain chain;
    private double usdcBalance;
    private double eurcBalance;
    private double solBalance;
    private double baseEthBalance;

    /* Constructor method */
    public CustomerWallet(String address, Blockchain chain) {
        this.walletAddress = address;
        this.chain = chain;

        this.usdcBalance = 0;
        this.eurcBalance = 0;
        this.solBalance = 0;
        this.baseEthBalance = 0;
    }
}
