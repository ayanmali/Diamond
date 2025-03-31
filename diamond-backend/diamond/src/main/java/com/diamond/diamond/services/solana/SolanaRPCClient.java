/*
 * Class to interact with the Solana API
 */
package com.diamond.diamond.services.solana;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sol4k.Connection;
import org.sol4k.Keypair;
import org.sol4k.ProgramDerivedAddress;
import org.sol4k.PublicKey;
import org.sol4k.TransactionMessage;
import org.sol4k.VersionedTransaction;
import org.sol4k.instruction.SplTransferInstruction;
import org.sol4k.instruction.TransferInstruction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.diamond.diamond.types.Token;

@Service
public class SolanaRPCClient {
    private final int LAMPORTS_PER_SOL = 1000000;
    private final String RPC_ENDPOINT;
    private final HttpClient httpClient;
    
    public SolanaRPCClient(@Value("${solana.rpc.url}") String rpcEndpoint) {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.RPC_ENDPOINT = rpcEndpoint;
    }
    
    public BigDecimal getTokenBalance(String walletAddress, String tokenAddress) {
        try {
            String requestBody = String.format(
                "{" +
                "\"jsonrpc\": \"2.0\"," +
                "\"id\": 1," +
                "\"method\": \"getTokenAccountsByOwner\"," +
                "\"params\": [" +
                "\"%s\"," +
                "{" +
                "\"mint\": \"%s\"" +
                "}," +
                "{" +
                "\"encoding\": \"jsonParsed\"" +
                "}" +
                "]" +
                "}", walletAddress, tokenAddress);
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(RPC_ENDPOINT))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray accounts = jsonResponse.getJSONObject("result").getJSONArray("value");
            
            if (accounts.length() > 0) {
                JSONObject accountInfo = accounts.getJSONObject(0)
                                              .getJSONObject("account")
                                              .getJSONObject("data")
                                              .getJSONObject("parsed")
                                              .getJSONObject("info")
                                              .getJSONObject("tokenAmount");
                
                return BigDecimal.valueOf(accountInfo.getDouble("uiAmount"));
            }
            
            return BigDecimal.ZERO;
        } catch (IOException | InterruptedException | JSONException e) {
            throw new RuntimeException("Failed to get token balance: " + e.getMessage(), e);
        }
    }

    public void transferSol(String publicKey, byte[] privateKey, BigDecimal amount) {
        try {
            // 1. Establish connection
            Connection connection = new Connection(RPC_ENDPOINT);
            
            // 2. Get recent blockhash
            String blockhash = connection.getLatestBlockhash();
            
            // 3. Load sender keypair (securely store private key)
            Keypair sender = Keypair.fromSecretKey(privateKey);
            
            // 4. Define recipient
            PublicKey receiver = new PublicKey(publicKey);
            
            // 5. Create transfer instruction
            TransferInstruction instruction = new TransferInstruction(
                sender.getPublicKey(),
                receiver,
                amount.multiply(BigDecimal.valueOf(LAMPORTS_PER_SOL)).longValue()
            );
            
            // 6. Build transaction
            TransactionMessage message = TransactionMessage.newMessage(
                sender.getPublicKey(),
                blockhash,
                instruction
            );
            VersionedTransaction transaction = new VersionedTransaction(message);
            
            // 7. Sign transaction
            transaction.sign(sender);
            
            // 8. Send transaction
            String signature = connection.sendTransaction(transaction);
            System.out.println("Transaction ID: " + signature);
        } catch (Exception e) {
            System.err.println("Transaction failed: " + e.getMessage());
        }
    }

    public void transferSplToken(byte[] fromPrivateKey, String toPublicKey, long amount, Token tokenToTransfer) {
        try {
            // 1. Establish connection
            Connection connection = new Connection(RPC_ENDPOINT);
            
            // 2. Get recent blockhash
            String blockhash = connection.getLatestBlockhash();
            
            // 3. Load sender keypair (securely store private key)
            Keypair sender = Keypair.fromSecretKey(fromPrivateKey);
            
            // 4. Define recipient
            PublicKey receiver = new PublicKey(toPublicKey);
            
            // 5. Create transfer instruction
            // TransferInstruction instruction = new TransferInstruction(
            //     sender.getPublicKey(),
            //     receiver,
            //     amount.multiply(BigDecimal.valueOf(LAMPORTS_PER_SOL)).longValue()
            // );

            PublicKey tokenMint = new PublicKey(tokenToTransfer.getTokenAddress());
            ProgramDerivedAddress senderTokenAccount = PublicKey.findProgramDerivedAddress(sender.getPublicKey(), tokenMint);
            ProgramDerivedAddress receiverTokenAccount = PublicKey.findProgramDerivedAddress(receiver, tokenMint);

            // SplTransferInstruction instruction = new SplTransferInstruction(
            //     sender.getPublicKey(),
            //     receiver,
            //     tokenMint,
            //     PublicKey.findProgramDerivedAddress(sender.getPublicKey(), new PublicKey(tokenToTransfer.getTokenAddress())),
            //     amount,
            //     6
            // );

            SplTransferInstruction instruction = new SplTransferInstruction(
                senderTokenAccount.getPublicKey(), 
                receiverTokenAccount.getPublicKey(), 
                tokenMint, 
                sender.getPublicKey(), 
                amount, 
                6);
            
            // 6. Build transaction
            TransactionMessage message = TransactionMessage.newMessage(
                sender.getPublicKey(),
                blockhash,
                instruction
            );
            VersionedTransaction transaction = new VersionedTransaction(message);
            
            // 7. Sign transaction
            transaction.sign(sender);
            
            // 8. Send transaction
            String signature = connection.sendTransaction(transaction);
            System.out.println("Transaction ID: " + signature);
        } catch (Exception e) {
            System.err.println("Transaction failed: " + e.getMessage());
        }
    }

    // public Boolean confirmTransfer() {

    // }

    // public Double checkNetworkFee() {

    // }
}
