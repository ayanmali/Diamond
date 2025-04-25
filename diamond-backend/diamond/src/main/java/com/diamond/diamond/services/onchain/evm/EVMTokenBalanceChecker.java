package com.diamond.diamond.services.onchain.evm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;

import com.diamond.diamond.types.Blockchain;
import static com.diamond.diamond.types.Tokens.EVM_CONTRACTS;

// TODO: refactor this
@Service
public class EVMTokenBalanceChecker {
    // Map each chain with its RPC endpoint and USDC contract address.
    private final Map<Blockchain, String> RPC_ENDPOINTS = new HashMap<>();
    //private static final Map<Blockchain, String> USDC_CONTRACTS = new HashMap<>();
    @Value("${base.rpc.url}")
    private String BASE_RPC_URL;
    @Value("${polygon.rpc.url}")
    private String POLYGON_RPC_URL;
    @Value("${optimism.rpc.url}")
    private String OPTIMISM_RPC_URL;

    // static {
    //     // Configure your RPC endpoints (replace with your actual endpoints)
    //     RPC_ENDPOINTS.put(Blockchain.BASE, BASE_RPC_URL);
    //     RPC_ENDPOINTS.put(Blockchain.OPTIMISM, "https://optimism-mainnet.infura.io/v3/YOUR-PROJECT-ID");
    //     RPC_ENDPOINTS.put(Blockchain.POLYGON, "https://polygon-mainnet.infura.io/v3/YOUR-PROJECT-ID");

    //     // Set the USDC contract addresses for each chain.
    //     // USDC_CONTRACTS.put("Base", "0x<USDC_ADDRESS_FOR_BASE>");        // Replace with actual contract address on Base
    //     // USDC_CONTRACTS.put("Optimism", "0x<USDC_ADDRESS_FOR_OPTIMISM>");  // e.g., "0x0b2C639c533813f4Aa9D7837CAf62653d097Ff85"
    //     // USDC_CONTRACTS.put("Polygon", "0x2791Bca1f2de4661ED88A30C99A7a9449Aa84174");
    // }

    public EVMTokenBalanceChecker() {
        // Injecting EVM chain RPC endpoint URLs
        RPC_ENDPOINTS.put(Blockchain.BASE, BASE_RPC_URL);
        RPC_ENDPOINTS.put(Blockchain.POLYGON, POLYGON_RPC_URL);
        RPC_ENDPOINTS.put(Blockchain.OPTIMISM, OPTIMISM_RPC_URL);
    }

    // ERC20 balanceOf function ABI: function balanceOf(address owner) public view returns (uint256)
    // gets balance of an EVM token given a wallet address.
    public static BigInteger getTokenBalance(Web3j web3j, String contractAddress, String walletAddress) throws Exception {
        Function balanceOfFunction = new Function(
                "balanceOf",
                Collections.singletonList(new Address(walletAddress)),
                Collections.singletonList(new org.web3j.abi.TypeReference<Uint256>() {})
        );

        String encodedFunction = FunctionEncoder.encode(balanceOfFunction);
        // For the 'from' address, you can use the wallet address or any valid address.
        Transaction transaction = Transaction.createEthCallTransaction(walletAddress, contractAddress, encodedFunction);
        
        String value = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST)
                            .send()
                            .getValue();
                            
        List<Type> results = FunctionReturnDecoder.decode(value, balanceOfFunction.getOutputParameters());
        
        if (results.isEmpty()) {
            throw new RuntimeException("Empty result from balanceOf call");
        }
        return (BigInteger) results.get(0).getValue();
    }

    // main method
    // gets balances for all supported stablecoins on all supported EVM chains
    public void getAllBalances() {
        // The wallet address whose USDC balance you want to check.
        String walletAddress = "0xYourWalletAddressHere"; // Replace with the desired wallet address
        
        // Loop through the chains.
        for (Blockchain chain : RPC_ENDPOINTS.keySet()) {
            String rpcUrl = RPC_ENDPOINTS.get(chain);
            Web3j web3j = Web3j.build(new HttpService(rpcUrl));

            // loop through contract addresses for each chain (USDC, EURC, etc)
            for (String contractAddress : EVM_CONTRACTS.get(chain)) {
                try {
                    BigInteger rawBalance = getTokenBalance(web3j, contractAddress, walletAddress);
                    
                    // USDC has 6 decimals so perform a conversion:
                    BigDecimal balanceInUSDC = new BigDecimal(rawBalance).divide(BigDecimal.TEN.pow(6));
    
                    System.out.printf("USDC balance on %s: %s%n", chain, balanceInUSDC.toPlainString());
    
                    web3j.shutdown(); // Clean up the client connection
    
                } catch (Exception e) {
                    System.err.printf("Error fetching balance for chain %s: %s%n", chain, e.getMessage());
                }
            }
            //tring contractAddress = USDC_CONTRACTS.get(chain);
        }
    }
}