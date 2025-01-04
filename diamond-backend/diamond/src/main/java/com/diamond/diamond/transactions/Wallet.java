package com.diamond.diamond.transactions;

public interface Wallet {
    // public String walletAddress;
    // public Blockchain chain;

    public String getAddress();

    public Blockchain getChain();
}
