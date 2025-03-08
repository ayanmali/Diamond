package com.diamond.diamond.types;

public class Token {
    private String tokenName;
    // E.g. ERC20, Fungible, etc.
    private String standard;
    private Blockchain chain;
    private Integer decimals;
    private Boolean isNative;
    private String symbol;
    private String tokenAddress;

    public Token() {}

    public String getTokenName() {
        return tokenName;
    }
    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
    public String getStandard() {
        return standard;
    }
    public void setStandard(String standard) {
        this.standard = standard;
    }
    public Blockchain getChain() {
        return chain;
    }
    public void setChain(Blockchain chain) {
        this.chain = chain;
    }
    public Integer getDecimals() {
        return decimals;
    }
    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }
    public Boolean getIsNative() {
        return isNative;
    }
    public void setIsNative(Boolean isNative) {
        this.isNative = isNative;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getTokenAddress() {
        return tokenAddress;
    }
    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

    

}
