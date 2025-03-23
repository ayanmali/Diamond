package com.diamond.diamond.dtos.wallets;

import java.math.BigDecimal;

import com.diamond.diamond.types.Token;

/*
 * Represents metadata and the balance of a particular token for a single user wallet
 */
public class FetchTokenBalanceDto {
    private BigDecimal amount;
    private Token token;

    public FetchTokenBalanceDto() {}

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
