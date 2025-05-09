package com.diamond.diamond.controllers.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.wallets.FetchAccountWalletDto;
import com.diamond.diamond.dtos.wallets.FetchTokenBalanceDto;
import com.diamond.diamond.dtos.wallets.NewAccountWalletDto;
import com.diamond.diamond.services.AuthService;
import com.diamond.diamond.services.onchain.evm.EVMWalletCreator;
import com.diamond.diamond.services.onchain.solana.KeypairCreator;
import com.diamond.diamond.services.onchain.solana.SolanaRPCClient;
import com.diamond.diamond.services.user.AccountWalletService;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.Token;
import static com.diamond.diamond.types.Tokens.SOL_STABLECOIN_TOKENS;
import com.diamond.diamond.types.WalletKeypair;
import com.diamond.diamond.types.WalletStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallets")
public class AccountWalletController {

    private final AuthService authService;

    private final EVMWalletCreator evmWalletCreator;

    private final AccountWalletService accountWalletService;
    //private final CircleApiClient circleApiClient;
    private final KeypairCreator solanaKeypairCreator;
    private final SolanaRPCClient solanaRPCClient;

    //private final CircleGrpcClient circleGrpcClient;

    public AccountWalletController(AccountWalletService accountWalletService,/*, AccountService accountService, CircleApiClient circleApiClient , CircleGrpcClient circleGrpcClient*/ KeypairCreator solanaKeypairCreator, SolanaRPCClient solanaRPCClient, EVMWalletCreator EVMWalletCreator, AuthService authService) {
        this.accountWalletService = accountWalletService;
        //this.accountService = accountService;
        //this.circleApiClient = circleApiClient;
        this.solanaKeypairCreator = solanaKeypairCreator;
        this.solanaRPCClient = solanaRPCClient;
        this.evmWalletCreator = EVMWalletCreator;
        this.authService = authService;
        //this.circleGrpcClient = circleGrpcClient;
    }

    @PostMapping("/{id}/new")
    public WalletKeypair createWallet(@PathVariable(value="id") UUID accountId, @Valid @RequestBody NewAccountWalletDto accountWalletDto) {
        //TODO: process POST request
        //Account account = accountService.findAccountById(accountWalletDto.getAccountId());
        // Optional<CreateWalletResponse> optionalWalletObj = circleGrpcClient.createWallet(
        //                                             accountWalletDto.getChain(),
        //                                             account.getWalletSetId(),
        //                                             accountWalletDto.getIdempotencyKey());
        // //JSONObject walletObj = circleApiClient.createWallet(accountWalletDto.getChain(), account.getWalletSetId(), UUID.randomUUID());
        // if (optionalWalletObj.isEmpty()) {
        //     System.out.println("Error - Wallet response object is empty.");
        //     return new FetchAccountWalletDto();
        // }
        // CreateWalletResponse walletObj = optionalWalletObj.get();
        // WalletKeypair keypair = solanaKeypairCreator.generate();
        WalletKeypair keypair = evmWalletCreator.generate();
        // TODO: encrypt private keys before storing them
        try {
            String encryptedPrivateKey = authService.encrypt(keypair.getPrivateKey(), authService.getSecretWalletKey());
            //String encryptedPrivateKey = accountService.encrypt(keypair.getPrivateKey(), AccountService.getSecretWalletKey());
            accountWalletService.saveWallet(accountWalletDto, accountId, keypair.getPublicKey(), encryptedPrivateKey);
            return keypair;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Error encrypting generated private key: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}/wallets")
    public List<FetchAccountWalletDto> getWallets(@RequestBody(required=false) UUID walletId,
                                                  @PathVariable(value="id")    UUID accountId,
                                                  @RequestBody(required=false) Blockchain chain,
                                                  @RequestBody(required=false) WalletStatus status,
                                                  @RequestBody(required=false) Date createdBefore,
                                                  @RequestBody(required=false) Date createdAfter,
                                                  @RequestBody(required=false) Integer pageSize) {
        return accountWalletService.findWalletDtosWithFilters(walletId, accountId, chain, status, createdBefore, createdAfter, pageSize);
    }
    

    @GetMapping("/walletid/{id}")
    public FetchAccountWalletDto getWalletByWalletId(@PathVariable(value = "id") UUID id) {
        return accountWalletService.findWalletDtoById(id);
    }

    /*
     * Get all wallets associated with a Account
     */
    // @GetMapping("/{id}")
    // public List<FetchAccountWalletDto> getWalletsByAccount(@PathVariable(value="id") UUID accountId) {
    //     return accountWalletService.findWalletDtosByAccount(accountService.findAccountById(accountId));
    // }
    
    @GetMapping("/address/{address}")
    public FetchAccountWalletDto getWalletByAddress(@PathVariable(value = "address") String address) {
        return accountWalletService.findWalletDtoByAddress(address);
    }

    /*
     * Retrieves token balances for one user wallet
     */
    // @GetMapping("/id/{id}/tokens")
    // public List<FetchTokenBalanceDto> getTokenBalances(@PathVariable(value="id") UUID walletId, @RequestParam(required=false) Boolean includeAll, @RequestParam(required=false) String tokenName, @RequestParam(required=false) String tokenAddress, @RequestParam(required=false) Integer pageSize) {
    //     JSONArray tokenBalances = circleApiClient.getTokenBalance(
    //         walletId, Optional.ofNullable(includeAll), Optional.ofNullable(tokenName), Optional.ofNullable(tokenAddress), Optional.ofNullable(pageSize));

    //     List<FetchTokenBalanceDto> tokenBalanceDtos = new ArrayList<>();
        
    //     for (int i = 0; i < tokenBalances.length(); i++) {
    //         FetchTokenBalanceDto tokenBalanceDto = new FetchTokenBalanceDto();
    //         Token token = new Token();
    //         JSONObject tokenBalance = tokenBalances.getJSONObject(i);
    //         tokenBalanceDto.setAmount(tokenBalance.getBigDecimal("amount"));

    //         JSONObject tokenObj = new JSONObject(tokenBalance.getJSONObject("token"));

    //         token.setTokenName(tokenObj.getString("name"));
    //         token.setStandard(tokenObj.getString("standard"));
    //         token.setChain(Blockchain.valueOf(tokenObj.getString("blockchain")));
    //         token.setDecimals(tokenObj.getInt("decimals"));
    //         token.setIsNative(tokenObj.getBoolean("isNative"));
    //         token.setSymbol(tokenObj.getString("symbol"));
    //         token.setTokenAddress(tokenObj.getString("tokenAddress"));

    //         tokenBalanceDto.setToken(token);

    //         tokenBalanceDtos.add(tokenBalanceDto);

    //     }
    //     return tokenBalanceDtos;
    // }
    /*
     * Gets token balances for a specific user wallet
     */
    @GetMapping("/walletid/{id}/balances")
    public List<FetchTokenBalanceDto> getTokenBalances(@PathVariable(value="id") UUID id) {
        // Getting this wallet's address based on its ID
        String walletAddress = accountWalletService.findWalletById(id).getAddress();

        List<FetchTokenBalanceDto> balances = List.of();

        for (Token stablecoinToken : SOL_STABLECOIN_TOKENS) {
            balances.add(
                new FetchTokenBalanceDto(
                    solanaRPCClient.getTokenBalance(walletAddress, stablecoinToken),
                    stablecoinToken
                )
            );
        }

        return balances;
    }
    
    @PatchMapping("/walletid/{id}/update-name")
    public FetchAccountWalletDto updateWalletName(@RequestBody String name, @PathVariable(value = "id") UUID id) {
        return accountWalletService.updateWalletName(id, name);
    }

    @PatchMapping("/walletid/{id}/archive")
    public FetchAccountWalletDto archiveWallet(@PathVariable(value="id") UUID id) {
        //TODO: process POST request
        return accountWalletService.archiveWallet(id);
    }

    @PatchMapping("/walletid/{id}/activate")
    public FetchAccountWalletDto reactivateWallet(@PathVariable(value="id") UUID id) {
        //TODO: process POST request
        return accountWalletService.reactivateWallet(id);
    }
    
}
