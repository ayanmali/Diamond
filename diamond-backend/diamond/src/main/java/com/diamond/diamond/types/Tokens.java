package com.diamond.diamond.types;

import java.util.List;
import java.util.Map;

public class Tokens {
    public static final Token USDC_SOL = new Token(StablecoinCurrency.USDC.toString(), "SPL", Blockchain.SOL, 6, "EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v");
    public static final Token EURC_SOL = new Token(StablecoinCurrency.EURC.toString(), "SPL", Blockchain.SOL, 6, "HzwqbKZw8HxMN6bF2yFZNrht3c2iXXzpKcFu7uBEDKtr");
    public static final Token SPOT_SOL = new Token("SOL", "SPL", Blockchain.SOL, 6, "So11111111111111111111111111111111111111112");

    public static final Token USDC_BASE = new Token(StablecoinCurrency.USDC.toString(), "ERC-20", Blockchain.BASE, 6, "");
    public static final Token EURC_BASE = new Token(StablecoinCurrency.EURC.toString(), "ERC-20", Blockchain.BASE, 6, "");
    public static final Token WETH_BASE = new Token("WETH", "ERC-20", Blockchain.BASE, 6, "");

    public static final Token USDC_POLYGON = new Token(StablecoinCurrency.USDC.toString(), "ERC-20", Blockchain.POLYGON, 6, "");
    public static final Token EURC_POLYGON = new Token(StablecoinCurrency.EURC.toString(), "ERC-20", Blockchain.POLYGON, 6, "");
    public static final Token SPOT_POLY = new Token("POLY", "ERC-20", Blockchain.POLYGON, 6, "");

    public static final Token USDC_OP = new Token(StablecoinCurrency.USDC.toString(), "ERC-20", Blockchain.OPTIMISM, 6, "");
    public static final Token EURC_OP = new Token(StablecoinCurrency.EURC.toString(), "ERC-20", Blockchain.OPTIMISM, 6, "");
    public static final Token SPOT_OP = new Token("OP", "ERC-20", Blockchain.OPTIMISM, 6, "");

    public static final List<Token> SOL_STABLECOIN_TOKENS = List.of(USDC_SOL, EURC_SOL, SPOT_SOL);

    public static final Map<Blockchain, List<String>> EVM_CONTRACTS = Map.of(
        Blockchain.BASE, List.of(USDC_BASE.getTokenAddress(), EURC_BASE.getTokenAddress(), WETH_BASE.getTokenAddress()),

        Blockchain.POLYGON, List.of(USDC_POLYGON.getTokenAddress(), EURC_POLYGON.getTokenAddress(), SPOT_POLY.getTokenAddress()),
        
        Blockchain.OPTIMISM, List.of(USDC_OP.getTokenAddress(), EURC_OP.getTokenAddress(), SPOT_OP.getTokenAddress())
    );

    public static final List<Token> EVM_STABLECOIN_TOKENS = List.of(USDC_BASE, EURC_BASE, USDC_POLYGON, EURC_POLYGON, USDC_OP, EURC_OP);
}
