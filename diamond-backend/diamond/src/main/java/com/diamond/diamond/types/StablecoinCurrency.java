package com.diamond.diamond.types;

public enum StablecoinCurrency {
    USDC, EURC, USDT, SOL;

    @Override
    public String toString() {
        return name();
    }
}
