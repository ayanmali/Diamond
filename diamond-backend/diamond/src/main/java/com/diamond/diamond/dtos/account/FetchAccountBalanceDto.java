package com.diamond.diamond.dtos.account;

import java.util.List;

import com.diamond.diamond.dtos.wallets.FetchWalletBalanceDto;

/*
 * Represents the balance of all (relevant) tokens across all wallets for a given user
 */
public class FetchAccountBalanceDto {
   private List<FetchWalletBalanceDto> accountBalance; 

    public List<FetchWalletBalanceDto> getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(List<FetchWalletBalanceDto> accountBalance) {
        this.accountBalance = accountBalance;
    }
}
