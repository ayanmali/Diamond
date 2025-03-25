package com.diamond.diamond.types;

import java.util.List;

public class Tokens {
    public static final Token USDC_SOL = new Token(StablecoinCurrency.USDC, "SPL", Blockchain.SOL, 6, "EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v");
    public static final Token EURC_SOL = new Token(StablecoinCurrency.EURC, "SPL", Blockchain.SOL, 6, "HzwqbKZw8HxMN6bF2yFZNrht3c2iXXzpKcFu7uBEDKtr");

    public static final List<Token> STABLECOIN_TOKENS = List.of(USDC_SOL, EURC_SOL);
}
