package com.diamond.diamond.types;

import java.util.Map;

public enum Blockchain {
    SOL, SOL_DEVNET, BASE, POLYGON, OPTIMISM, BSC, TON, TRON, APTOS;
    
    private static final Map<Blockchain, String> SPOT_TICKERS = Map.of(
            SOL, "SOL",
            SOL_DEVNET, "SOL",
            BASE, "WETH",
            POLYGON, "POLY",
            OPTIMISM, "OP",
            BSC, "BNB",
            TON, "TON",
            TRON, "TRON",
            APTOS, "APT"
    );

    @Override
    public String toString() {
        if (this.equals(SOL_DEVNET)) {
            return "SOL-DEVNET";
        }
        return name();
    }

    /*
     * Returns the name of the spot token for the given chain
     */
    public String getSpotTicker() {
        return SPOT_TICKERS.get(this);
    }
}
