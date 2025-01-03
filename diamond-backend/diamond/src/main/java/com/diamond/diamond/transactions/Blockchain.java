package com.diamond.diamond.transactions;

import java.util.Map;

public enum Blockchain {
    SOL, BASE, BSC, TON, TRON;

    private static final Map<Blockchain, String> SPOT_TICKERS = Map.of(
            SOL, "SOL",
            BASE, "WETH",
            BSC, "BNB",
            TON, "TON",
            TRON, "TRON"
    );

    @Override

    public String toString() {
        return name();
    }

    /*
     * Returns the name of the spot token for the given chain
     */
    public String getSpotTicker() {
        return SPOT_TICKERS.get(this);
    }
}
