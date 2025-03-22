package com.diamond.diamond.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.wallets.FetchAccountWalletDto;
import com.diamond.diamond.dtos.wallets.FetchTokenBalanceDto;
import com.diamond.diamond.dtos.wallets.NewAccountWalletDto;
import com.diamond.diamond.entities.Account;
import com.diamond.diamond.grpc_client.CircleGrpcClient;
import com.diamond.diamond.grpc_client.CreateWalletResponse;
import com.diamond.diamond.services.AccountService;
import com.diamond.diamond.services.AccountWalletService;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.Token;
import com.diamond.diamond.types.WalletStatus;
import com.diamond.diamond.utils.CircleApiClient;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/wallets")
public class AccountWalletController {
    private final AccountWalletService accountWalletService;
    private final AccountService accountService;
    private final CircleApiClient circleApiClient;
    private final CircleGrpcClient circleGrpcClient;

    public AccountWalletController(AccountWalletService accountWalletService, AccountService accountService, CircleApiClient circleApiClient, CircleGrpcClient circleGrpcClient) {
        this.accountWalletService = accountWalletService;
        this.accountService = accountService;
        this.circleApiClient = circleApiClient;
        this.circleGrpcClient = circleGrpcClient;
    }

    @PostMapping("/new")
    public FetchAccountWalletDto createWallet(@RequestBody NewAccountWalletDto accountWalletDto) {
        //TODO: process POST request
        Account account = accountService.findAccountById(accountWalletDto.getAccountId());
        Optional<CreateWalletResponse> optionalWalletObj = circleGrpcClient.createWallet(
                                                    accountWalletDto.getChain(),
                                                    account.getWalletSetId(),
                                                    accountWalletDto.getIdempotencyKey());
        //JSONObject walletObj = circleApiClient.createWallet(accountWalletDto.getChain(), account.getWalletSetId(), UUID.randomUUID());
        if (optionalWalletObj.isEmpty()) {
            System.out.println("Error - Wallet response object is empty.");
            return new FetchAccountWalletDto();
        }
        CreateWalletResponse walletObj = optionalWalletObj.get();

        return accountWalletService.saveWallet(accountWalletDto,
                                               account,
                                               walletObj.getAddress(),
                                               UUID.fromString(walletObj.getId()));
    }

    @GetMapping("/wallets")
    public List<FetchAccountWalletDto> getWallets(@RequestBody(required=false) UUID walletId,
                                                  @RequestBody(required=false) UUID accountId,
                                                  @RequestBody(required=false) Blockchain chain,
                                                  @RequestBody(required=false) WalletStatus status,
                                                  @RequestBody(required=false) Date createdBefore,
                                                  @RequestBody(required=false) Date createdAfter,
                                                  @RequestBody(required=false) Integer pageSize) {
        return accountWalletService.findWalletDtosWithFilters(walletId, accountId, chain, status, createdBefore, createdAfter, pageSize);
    }
    

    @GetMapping("/id/{id}")
    public FetchAccountWalletDto getWalletByWalletId(@PathVariable(value = "id") UUID id) {
        return accountWalletService.findWalletDtoById(id);
    }

    /*
     * Get all wallets associated with a Account
     */
    @GetMapping("/accountid/{id}")
    public List<FetchAccountWalletDto> getWalletsByAccount(@PathVariable(value="id") String accountId) {
        return accountWalletService.findWalletDtosByAccount(accountService.findAccountById(accountId));
    }
    
    @GetMapping("/address/{address}")
    public FetchAccountWalletDto getWalletByAddress(@PathVariable(value = "address") String address) {
        return accountWalletService.findWalletDtoByAddress(address);
    }

    /*
     * Retrieves token balances for one user wallet
     */
    @GetMapping("/id/{id}/tokens")
    public List<FetchTokenBalanceDto> getTokenBalances(@PathParam(value="id") String walletId, @RequestParam(required=false) Boolean includeAll, @RequestParam(required=false) String tokenName, @RequestParam(required=false) String tokenAddress, @RequestParam(required=false) Integer pageSize) {
        JSONArray tokenBalances = circleApiClient.getTokenBalance(
            walletId, Optional.ofNullable(includeAll), Optional.ofNullable(tokenName), Optional.ofNullable(tokenAddress), Optional.ofNullable(pageSize));

        List<FetchTokenBalanceDto> tokenBalanceDtos = new ArrayList<>();
        
        for (int i = 0; i < tokenBalances.length(); i++) {
            FetchTokenBalanceDto tokenBalanceDto = new FetchTokenBalanceDto();
            Token token = new Token();
            JSONObject tokenBalance = tokenBalances.getJSONObject(i);
            tokenBalanceDto.setAmount(tokenBalance.getDouble("amount"));

            JSONObject tokenObj = new JSONObject(tokenBalance.getJSONObject("token"));

            token.setTokenName(tokenObj.getString("name"));
            token.setStandard(tokenObj.getString("standard"));
            token.setChain(Blockchain.valueOf(tokenObj.getString("blockchain")));
            token.setDecimals(tokenObj.getInt("decimals"));
            token.setIsNative(tokenObj.getBoolean("isNative"));
            token.setSymbol(tokenObj.getString("symbol"));
            token.setTokenAddress(tokenObj.getString("tokenAddress"));

            tokenBalanceDto.setToken(token);

            tokenBalanceDtos.add(tokenBalanceDto);

        }
        return tokenBalanceDtos;
    }
    
    
    @PatchMapping("id/{id}/update-name")
    public FetchAccountWalletDto updateWalletName(@RequestBody String name, @PathVariable(value = "id") UUID id) {
        return accountWalletService.updateWalletName(id, name);
    }

    @PatchMapping("/id/{id}/archive")
    public FetchAccountWalletDto archiveWallet(@PathVariable(value="id") UUID id) {
        //TODO: process POST request
        return accountWalletService.archiveWallet(id);
    }

    @PatchMapping("/id/{id}/activate")
    public FetchAccountWalletDto reactivateWallet(@PathVariable(value="id") UUID id) {
        //TODO: process POST request
        return accountWalletService.reactivateWallet(id);
    }
    
}
