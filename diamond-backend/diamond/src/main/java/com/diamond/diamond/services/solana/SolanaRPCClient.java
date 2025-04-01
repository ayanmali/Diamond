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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SolanaRPCClient {
    private final int LAMPORTS_PER_SOL = 1_000_000_000;

    @Value("${solana.rpc.url}")
    private String rpcEndpoint;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    
    public SolanaRPCClient(@Value("${solana.rpc.url}") String rpcEndpoint) {
        this.rpcEndpoint = rpcEndpoint;
        this.httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();
        this.objectMapper = new ObjectMapper();
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
                    .uri(URI.create(rpcEndpoint))
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

    public TransactionMessage createTransferSolMessage(Connection connection, byte[] fromPrivateKey, String toPublicKey, Long amount) {
        try {
            // 1. Establish connection
            // Connection connection = new Connection(rpcEndpoint);
            
            // 2. Get recent blockhash
            String blockhash = connection.getLatestBlockhash();
            
            // 3. Load sender keypair (securely store private key)
            Keypair sender = Keypair.fromSecretKey(fromPrivateKey);
            
            // 4. Define recipient
            PublicKey receiver = new PublicKey(toPublicKey);
            
            // 5. Create transfer instruction
            TransferInstruction instruction = new TransferInstruction(
                sender.getPublicKey(),
                receiver,
                amount * LAMPORTS_PER_SOL
            );
            
            // 6. Build transaction
            System.out.println("Message created successfully");
            return TransactionMessage.newMessage(
                sender.getPublicKey(),
                blockhash,
                instruction
            );
            // VersionedTransaction transaction = new VersionedTransaction(message);
            
            // // 7. Sign transaction
            // transaction.sign(sender);
            
            // // 8. Send transaction
            // String signature = connection.sendTransaction(transaction);
            // System.out.println("Transaction ID: " + signature);
        } catch (Exception e) {
            System.err.println("Transfer Message creation failed: " + e.getMessage());
            return null;
        }
    }

    public TransactionMessage createTransferSplMessage(Connection connection, byte[] fromPrivateKey, String toPublicKey, Long amount, Token tokenToTransfer) {
        try {
            // 1. Establish connection
            // Connection connection = new Connection(rpcEndpoint);
            
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
                6
            );
            
            System.out.println("Message created successfully");
            return TransactionMessage.newMessage(
                sender.getPublicKey(),
                blockhash,
                instruction
            );

            // VersionedTransaction transaction = new VersionedTransaction(message);
            
            // // 7. Sign transaction
            // transaction.sign(sender);
            
            // // 8. Send transaction
            // String signature = connection.sendTransaction(transaction);
            // System.out.println("Transaction ID: " + signature);
        } catch (Exception e) {
            System.err.println("Transfer Message creation failed: " + e.getMessage());
            return null;
        }
    }

    public String signMessage(Connection connection, TransactionMessage message, Keypair sender) {
        try {
            VersionedTransaction transaction = new VersionedTransaction(message);
                
            // 7. Sign transaction
            transaction.sign(sender);
            
            // 8. Send transaction
            String signature = connection.sendTransaction(transaction);
            System.out.println("Transaction ID: " + signature);
            return signature;
        }
         catch (Exception e) {
            System.out.println("Error signing message: " + e.getMessage());
            return null;
         }
    }

    /**
     * Gets the signature status for a single transaction signature.
     * 
     * @param signature The transaction signature to check
     * @param searchTransactionHistory Whether to search transaction history
     * @return A SignatureStatus object containing error and confirmation status
     * @throws IOException If there's an error with the HTTP request or JSON parsing
     * @throws InterruptedException If the HTTP request is interrupted
     */
    public SignatureStatus getSignatureStatus(String signature, boolean searchTransactionHistory) 
            throws IOException, InterruptedException {
        
        // Construct the request JSON
        Map<String, Object> requestMap = Map.of(
            "jsonrpc", "2.0",
            "id", 1,
            "method", "getSignatureStatuses",
            "params", List.of(
                List.of(signature),
                Map.of("searchTransactionHistory", searchTransactionHistory)
            )
        );
        
        String requestBody = objectMapper.writeValueAsString(requestMap);
        
        // Create and send HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(rpcEndpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Parse response
        JsonNode rootNode = objectMapper.readTree(response.body());
        JsonNode resultNode = rootNode.get("result");
        
        if (resultNode != null && resultNode.has("value") && !resultNode.get("value").isEmpty()) {
            JsonNode firstValue = resultNode.get("value").get(0);
            
            if (firstValue != null && !firstValue.isNull()) {
                // Extract err and confirmationStatus
                String err = (firstValue.has("err") && !firstValue.get("err").isNull()) 
                        ? firstValue.get("err").asText() 
                        : null;
                
                String confirmationStatus = (firstValue.has("confirmationStatus") && !firstValue.get("confirmationStatus").isNull()) 
                        ? firstValue.get("confirmationStatus").asText() 
                        : null;
                
                return new SignatureStatus(err, confirmationStatus);
            }
        }
        
        // Return empty status if no valid result was found
        return new SignatureStatus(null, null);
    }
    
    /**
     * Gets the signature statuses for multiple transaction signatures.
     * 
     * @param signatures List of transaction signatures to check
     * @param searchTransactionHistory Whether to search transaction history
     * @return A list of SignatureStatus objects
     * @throws IOException If there's an error with the HTTP request or JSON parsing
     * @throws InterruptedException If the HTTP request is interrupted
     */
    public List<SignatureStatus> getSignatureStatuses(List<String> signatures, boolean searchTransactionHistory) 
            throws IOException, InterruptedException {
        
        // Construct the request JSON
        Map<String, Object> requestMap = Map.of(
            "jsonrpc", "2.0",
            "id", 1,
            "method", "getSignatureStatuses",
            "params", List.of(
                signatures,
                Map.of("searchTransactionHistory", searchTransactionHistory)
            )
        );
        
        String requestBody = objectMapper.writeValueAsString(requestMap);
        
        // Create and send HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(rpcEndpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Parse response
        JsonNode rootNode = objectMapper.readTree(response.body());
        JsonNode resultNode = rootNode.get("result");
        List<SignatureStatus> results = new ArrayList<>();
        
        if (resultNode != null && resultNode.has("value")) {
            JsonNode values = resultNode.get("value");
            
            for (JsonNode value : values) {
                if (value != null && !value.isNull()) {
                    // Extract err and confirmationStatus
                    String err = (value.has("err") && !value.get("err").isNull()) 
                            ? value.get("err").asText() 
                            : null;
                    
                    String confirmationStatus = (value.has("confirmationStatus") && !value.get("confirmationStatus").isNull()) 
                            ? value.get("confirmationStatus").asText() 
                            : null;
                    
                    results.add(new SignatureStatus(err, confirmationStatus));
                } else {
                    results.add(null); // Maintain the same structure as the response
                }
            }
        }
        
        return results;
    }

    // public Long checkNetworkFee(String senderPublicKey, String receiverPublicKey, Long numLamports, Optional<String> commitment) {
        public Long checkNetworkFee(TransactionMessage message, Optional<String> commitment) {
        try {
            // 1. Establish connection
            //Connection connection = new Connection(rpcEndpoint);
            
            // 2. Define sender and receiver public keys
            //PublicKey sender = new PublicKey(senderPublicKey);
            //PublicKey receiver = new PublicKey(receiverPublicKey);
            
            // 3. Get recent blockhash
            //String blockhash = connection.getLatestBlockhash();
            
            // 4. Create a dummy transaction message
            // TransactionMessage message = TransactionMessage.newMessage(
            //     sender,
            //     blockhash,
            //     new TransferInstruction(sender, receiver, numLamports) // Example transfer of 1000 lamports
            // );

            // Create the message to check the fee for
            byte[] serializedMessage = message.serialize();
            String base64Message = Base64.getEncoder().encodeToString(serializedMessage);
            
            Long fee = getFeeForMessage(base64Message, commitment);
            
            // 6. Print the fee in lamports
            System.out.println("Transaction Fee: " + fee + " lamports");
            return fee;
        } catch (IOException | InterruptedException e) {
            System.err.println("Error calculating transaction fee: " + e.getMessage());
            return Long.valueOf(-1);
        }
    }

    /**
     * Gets the fee for a serialized message.
     * 
     * @param encodedMessage The base64 encoded message
     * @param commitment The commitment level to use (e.g., "processed", "confirmed", "finalized")
     * @return The fee in lamports for the message, or null if the call fails
     * @throws IOException If there's an error with the HTTP request or JSON parsing
     * @throws InterruptedException If the HTTP request is interrupted
     */
    public Long getFeeForMessage(String encodedMessage, Optional<String> commitment) 
            throws IOException, InterruptedException {
        
        List<Object> params = List.of(encodedMessage);
        if (commitment.isPresent()) { 
            params.add(Map.of("commitment", commitment.get())); 
        }

        // Construct the request JSON
        Map<String, Object> requestMap = Map.of(
            "jsonrpc", "2.0",
            "id", 1,
            "method", "getFeeForMessage",
            "params", params
        );
        
        String requestBody = objectMapper.writeValueAsString(requestMap);
        
        // Create and send HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(rpcEndpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Parse response
        JsonNode rootNode = objectMapper.readTree(response.body());
        
        if (rootNode.has("result") && rootNode.get("result").has("value")) {
            return rootNode.get("result").get("value").asLong();
        }
        
        return null; // Return null if no valid result was found
    }

    /**
     * A simple class to hold the signature status information.
     */
    public static class SignatureStatus {
        private final String err;
        private final String confirmationStatus;
        
        public SignatureStatus(String err, String confirmationStatus) {
            this.err = err;
            this.confirmationStatus = confirmationStatus;
        }
        
        public String getErr() {
            return err;
        }
        
        public String getConfirmationStatus() {
            return confirmationStatus;
        }
        
        @Override
        public String toString() {
            return "SignatureStatus{" +
                   "err=" + (err == null ? "null" : "'" + err + "'") +
                   ", confirmationStatus='" + confirmationStatus + "'" +
                   '}';
        }

    }

}
