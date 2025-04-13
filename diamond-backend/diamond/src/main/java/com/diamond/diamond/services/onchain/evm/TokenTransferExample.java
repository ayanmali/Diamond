package com.diamond.diamond.services.onchain.evm;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import java.math.BigInteger;

public class TokenTransferExample {

    public static void main(String[] args) {
        try {
            // Connect to Ethereum network
            Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/YOUR_INFURA_PROJECT_ID"));
            
            // Load wallet credentials
            Credentials credentials = WalletUtils.loadCredentials("your-wallet-password", "/path/to/wallet.json");
            
            // Load the token contract
            String tokenContractAddress = "0xYourTokenContractAddress";
            Erc20 token = Erc20.load(tokenContractAddress, web3, credentials, new DefaultGasProvider());
            
            // Define transfer details
            String recipientAddress = "0xRecipientWalletAddress";
            // For an 18-decimal token, transfer 1 token as an example
            BigInteger tokenAmount = new BigInteger("1000000000000000000");
            
            // Perform transfer
            TransactionReceipt receipt = token.transfer(recipientAddress, tokenAmount).send();
            System.out.println("Transfer successful, transaction hash: " + receipt.getTransactionHash());
        
        } catch (Exception e) {
            System.out.println("Error transferring tokens " + e.getMessage());
        }
    }
}

