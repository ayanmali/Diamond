package com.diamond.diamond.controllers.user;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.account.FetchAccountDto;
import com.diamond.diamond.entities.user.Account;
import com.diamond.diamond.services.solana.SolanaRPCClient;
import com.diamond.diamond.services.user.AccountService;
import com.diamond.diamond.services.user.AccountWalletService;


@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountWalletService accountWalletService;
    private final SolanaRPCClient solanaRpcClient; // TODO: implement controller methods for Solana interactions
    //private final OAuthService oauthService;
    @Value("${solana.rpc.url}")
    private String rpcEndpoint;
    //private final CircleApiClient circleApiClient;
    //private final CircleGrpcClient circleGrpcClient;

    public AccountController(AccountService accountService, AccountWalletService accountWalletService, SolanaRPCClient solanaRpcClient /*,CircleApiClient circleApiClient, CircleGrpcClient circleGrpcClient*/) {
        this.accountService = accountService;
        this.accountWalletService = accountWalletService;
        this.solanaRpcClient = solanaRpcClient;
        //this.oauthService = oauthService;

        //this.circleApiClient = circleApiClient;
        //this.circleGrpcClient = circleGrpcClient;
    }

    // helper method to add wallets to an account DTO object by fetching from the DB
    // private FetchAccountDto loadAccountWallets(FetchAccountDto accountDto) {
    //     Account account = accountService.findAccountById(accountDto.getId());
    //     List<FetchAccountWalletDto> wallets = accountWalletService.findWalletDtosByAccount(
    //                                                             account);
    //     accountDto.setWallets(wallets);
    //     return accountDto;
        
    // }

    // @PostMapping("/signup")
    // public FetchAccountDto signup(@Valid @RequestBody RegisterUserDto registerUserDto) {
    //     // Optional<UUID> optionalWalletSetId = circleGrpcClient.createWalletSet("");
    //     // // todo: error handling
    //     // if (optionalWalletSetId.isEmpty()) {
    //     //     return new FetchAccountDto();
    //     // }
    //     // UUID walletSetId = optionalWalletSetId.get();
    //     FetchAccountDto accountDto = accountService.signUp(registerUserDto);
    //     return accountDto;
    // }

    @PostMapping("/{id}/set-pin")
    public FetchAccountDto setPin(@PathVariable(value="id") UUID id, @RequestBody String pin) throws Exception {
        // Encrypting the user-provided PIN number
        String encryptedPin = accountService.encrypt(pin, AccountService.getSecretPinKey());
        Account account = accountService.findAccountById(id);
        account.setEncryptedPin(encryptedPin);
        return accountService.updatePin(id, encryptedPin);
    }
    
    
    @GetMapping("/id/{id}")
    public FetchAccountDto getAccountById(@PathVariable(value = "id") UUID id) {
        FetchAccountDto accountDto = accountService.findAccountDtoById(id);

        //accountDto = loadAccountWallets(accountDto);

        return accountDto;
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Map.of("name", principal.getAttribute("name"), 
                      "email", principal.getAttribute("email"));
        //return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @GetMapping("/accounts")
    public List<FetchAccountDto> getAccounts(@RequestParam(required=false) UUID id, @RequestParam(required=false) String email, @RequestParam(required=false) Date createdBefore, @RequestParam(required=false) Date createdAfter, @RequestParam(required=false) Integer pageSize) {
        return accountService.findAccountDtosWithFilters(
        id,
        email,
        createdBefore,
        createdAfter,
        pageSize
        );
    }

    @GetMapping("/email/{email}")
    public FetchAccountDto getAccountByEmail(@PathVariable(value="email") String email) {
        // getting the account dto
        FetchAccountDto accountDto = accountService.findAccountDtoByEmail(email);
        
        //accountDto = loadAccountWallets(accountDto);

        return accountDto;
    }

    // @GetMapping("wallets/{id}")
    // List<FetchAccountWalletDto> getWallets(@PathVariable(value="id") String id) {
    //     return accountService.findAccountWallets(UUID.fromString(id));
    // }
    
    @PatchMapping("/id/{id}/update-email")
    public FetchAccountDto updateEmail(@RequestBody String email, @PathVariable(value="id") UUID id) {
        //TODO: process POST request
        FetchAccountDto accountDto = accountService.updateAccountEmail(id, email);
        //accountDto = loadAccountWallets(accountDto);
        return accountDto;
    }
    
    @PatchMapping("/id/{id}/update-name")
    public FetchAccountDto updateBusinessName(@RequestBody String name, @PathVariable(value="id") UUID id) {
        //TODO: process POST request
        FetchAccountDto accountDto = accountService.updateAccountName(id, name);
        //accountDto = loadAccountWallets(accountDto);
        return accountDto;
    }

    @DeleteMapping("/id/{id}/delete")
    public FetchAccountDto deleteAccount(@PathVariable(value="id") UUID id) {
        //TODO: process POST request
        FetchAccountDto accountDto = accountService.findAccountDtoById(id);
        //accountDto = loadAccountWallets(accountDto);
        accountService.deleteAccountById(id);
        return accountDto;
    }

    // TODO: integrate websockets for token transfers
    /*
     * Prepares a message and returns the estimated gas fee associated with the message
     */
    // @MessageMapping("/message")
    // @SendTo("/topic/messages")
    // public String prepareTransferMessage(SampleMessage message) {
    //     //TODO: process POST request
        
    //     return "WebSocket Response";
    // }
    
    // TODO: prevent any transfers for 24 hours if the PIN is not valid within 5 guesses + send email to user
    // @PostMapping("/transfer")
    // public String transferTokens(@RequestBody NewTokenTransferDto transferDto) {
    //     // Authenticate the PIN that the user entered    

    //     // Store this transfer in the DB

    //     // Connect to Solana network
    //     Connection connection = new Connection(rpcEndpoint);
    //     // Create the message for transferring tokens
    //     TransactionMessage message = solanaRpcClient.createTransferSplMessage(connection, fromPrivateKey, toPublicKey, amount, tokenToTransfer);
    //     // Get the estimated network fee for the transfer
    //     Long fee = solanaRpcClient.checkNetworkFee(message, Optional.empty());
    //     // Sign the message and get the signature
    //     String signature = solanaRpcClient.signMessage(connection, message, sender);
    //     return signature;
    // }
    
    // @GetMapping("/email")
    // public String getMethodName(@RequestParam String param) {
    //     return new String();
    // }
    
    
    // public ResponseEntity<Account> getAccountById() {

    // }
}
