package com.diamond.diamond;

public class CustomerWallet {

    private final String walletAddress;
    private final Blockchain chain;
    // private double usdcBalance;
    // private double eurcBalance;
    // private double solBalance;
    // private double baseEthBalance;

    /* Constructor method */
    public CustomerWallet(String address, Blockchain chain) {
        this.walletAddress = address;
        this.chain = chain;

        // this.usdcBalance = 0;
        // this.eurcBalance = 0;
        // this.solBalance = 0;
        // this.baseEthBalance = 0;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public Blockchain getChain() {
        return chain;
    }

    // public double getUsdcBalance() {
    //     return usdcBalance;
    // }
    // public double getEurcBalance() {
    //     return eurcBalance;
    // }
    // public double getSolBalance() {
    //     return solBalance;
    // }
    // public double getBaseEthBalance() {
    //     return baseEthBalance;
    // }
}
