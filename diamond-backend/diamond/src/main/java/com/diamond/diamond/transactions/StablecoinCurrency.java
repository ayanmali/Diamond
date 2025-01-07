package com.diamond.diamond.transactions;

public enum StablecoinCurrency {
    USDC, EURC, USDT;

    @Override
    public String toString() {
        return name();
    }
}
