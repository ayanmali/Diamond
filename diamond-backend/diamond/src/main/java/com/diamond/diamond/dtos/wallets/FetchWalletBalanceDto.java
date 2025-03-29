package com.diamond.diamond.dtos.wallets;

import java.util.List;

/*
 * Represents the balance of all (relevant) tokens present in a single user wallet
 */
public class FetchWalletBalanceDto {
    private List<FetchTokenBalanceDto> tokenBalances;

    public List<FetchTokenBalanceDto> getTokenBalances() {
        return tokenBalances;
    }

    public void setTokenBalances(List<FetchTokenBalanceDto> tokenBalances) {
        this.tokenBalances = tokenBalances;
    }
}
