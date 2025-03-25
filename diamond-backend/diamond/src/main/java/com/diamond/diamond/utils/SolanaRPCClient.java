/*
 * Class to interact with the Solana API
 */
package com.diamond.diamond.utils;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SolanaRPCClient {
    private final String rpcEndpoint;
    private final HttpClient httpClient;
    
    public SolanaRPCClient(@Value("${solana.rpc.url}") String rpcEndpoint) {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        this.rpcEndpoint = rpcEndpoint;
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
}
