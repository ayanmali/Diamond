package com.diamond.diamond.types;

public class Token {
    //private String tokenName;
    // E.g. ERC20, Fungible, etc.
    private StablecoinCurrency currency;
    private String standard;
    private Blockchain chain;
    private Integer decimals;
    //private Boolean isNative;
    private String tokenAddress;

    public Token() {}
    public Token(StablecoinCurrency currency, String standard, Blockchain chain, Integer decimals, String tokenAddress) {
        this.currency = currency;
        this.standard = standard;
        this.chain = chain;
        this.decimals = decimals;
        //this.isNative = isNative;
        this.tokenAddress = tokenAddress;
    }

    // public String getTokenName() {
    //     return tokenName;
    // }
    // public void setTokenName(String tokenName) {
    //     this.tokenName = tokenName;
    // }
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
    // public Boolean getIsNative() {
    //     return isNative;
    // }
    // public void setIsNative(Boolean isNative) {
    //     this.isNative = isNative;
    // }
    public StablecoinCurrency getCurrency() {
        return currency;
    }
    public void setCurrency(StablecoinCurrency currency) {
        this.currency = currency;
    }
    public String getTokenAddress() {
        return tokenAddress;
    }
    public void setTokenAddress(String tokenAddress) {
        this.tokenAddress = tokenAddress;
    }

}