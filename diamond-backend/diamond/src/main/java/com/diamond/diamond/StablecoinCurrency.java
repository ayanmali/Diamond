package com.diamond.diamond;

public enum StablecoinCurrency {
    USDC, EURC, USDT;

    @Override
    public String toString() {
        return name();
    }
}
