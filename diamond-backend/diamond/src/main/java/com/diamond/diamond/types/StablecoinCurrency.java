package com.diamond.diamond.types;

public enum StablecoinCurrency {
    USDC, EURC, USDT;

    @Override
    public String toString() {
        return name();
    }
}
