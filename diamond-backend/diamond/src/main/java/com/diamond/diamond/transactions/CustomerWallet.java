package com.diamond.diamond.transactions;

public class CustomerWallet implements Wallet {

    private final String walletAddress;
    private final Blockchain chain;
    private int priority;
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

    @Override
    public String getAddress() {
        return walletAddress;
    }

    @Override
    public Blockchain getChain() {
        return chain;
    }

    public int getWalletPriority() {
        return priority;
    }

    public void setWalletPriority(int priority) {
        this.priority = priority;
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
