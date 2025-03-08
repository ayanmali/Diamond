package com.diamond.diamond.dtos.wallets;

import com.diamond.diamond.types.Token;

/*
 * Represents metadata and the balance of a particular token for a single user wallet
 */
public class FetchTokenBalanceDto {
    private Double amount;
    private Token token;

    public FetchTokenBalanceDto() {}

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
