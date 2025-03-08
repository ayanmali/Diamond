package com.diamond.diamond.utils;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.diamond.diamond.types.Blockchain;
import com.google.gson.Gson;

import io.github.cdimascio.dotenv.Dotenv;

public class CircleClient {
    private final Dotenv dotenv;
    private final AsyncHttpClient client;

    public CircleClient() {
        // loads environment variables defined in .env file
        dotenv = Dotenv.configure().load();
        client = new DefaultAsyncHttpClient();
    }

    // /*
    //  * Returns formatted URL with query parameters
    //  */
    // private String addQueryParameters(String baseUrl, Map<String, Object> parameters) {
    //     // Build query parameters
    //     StringBuilder queryParams = new StringBuilder();
    //     queryParams.append("?"); // Start query parameters

    //     // Add parameters if present
    //     for (String key : parameters.keySet()) {
    //         Object value = parameters.get(key);
            
    //     }

    //     address.ifPresent(a -> queryParams.append("address=").append(a).append("&"));
    //     blockchain.ifPresent(b -> queryParams.append("blockchain=").append(b.toString()).append("&"));
    //     walletSetId.ifPresent(w -> queryParams.append("walletSetId=").append(w).append("&"));
    //     refId.ifPresent(r -> queryParams.append("refId=").append(r).append("&"));
    //     fromDate.ifPresent(f -> queryParams.append("fromDate=").append(convertDateToISO(f)).append("&"));
    //     toDate.ifPresent(t -> queryParams.append("toDate=").append(convertDateToISO(t)).append("&"));
    //     pageSize.ifPresent(p -> queryParams.append("pageSize=").append(p).append("&"));

    //     // Remove the last '&' if present
    //     if (queryParams.length() > 1) {
    //         queryParams.setLength(queryParams.length() - 1); // Remove the last '&'
    //     }

    //     // Complete URL
    //     String url = baseUrl + queryParams.toString();
    // }
    
    // private String createWalletSet(String walletName) {
    //     try (AsyncHttpClient client = new DefaultAsyncHttpClient()) {

    //         String response = client.prepare("POST", "https://api.circle.com/v1/w3s/developer/walletSets")
    //                 .setHeader("Content-Type", "application/json")
    //                 .setHeader("Authorization", String.format("Bearer %s", dotenv.get("CIRCLE_API_KEY")))
    //                 .setBody(String.format("{\"idempotencyKey\":\"%s\",\"entitySecretCipherText\":\"%s\",\"name\":\"%s\"}", generateUUID(), dotenv.get("CIRCLE_ENTITY_SECRET_CIPHERTEXT"), walletName))
    //                 .execute()
    //                 .toCompletableFuture()
    //                 .thenApply(resp -> {return resp.getResponseBody();})
    //                 .join();
    //         client.close();
    //         return response;
    //     } catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    /* Wallet Sets */

    /*
     * Creates a Wallet Set and returns the ID for the Wallet Set
     */
    private String createWalletSet(String walletSetName) {
        BoundRequestBuilder req = buildRequest("POST",
                                               "https://api.circle.com/v1/w3s/developer/walletSets",
                                               Map.of("name", walletSetName));
        String resp = getResponse(req);
        final JSONObject obj = new JSONObject(resp);
        // extracting necessary values from the JSON object
        return obj.getJSONObject("data").getJSONObject("walletSet").getString("id");
        
    }

    /*
     * Returns a List containing the IDs of all wallet sets based on the provided parameters
     */
    private List<String> getAllWalletSets(Optional<Date> fromDate, Optional<Date> toDate, Optional<Integer> pageSize) {
        // Base URL for the API request
        String baseUrl = "https://api.circle.com/v1/w3s/wallets";
                
        // Build query parameters
        StringBuilder queryParams = new StringBuilder();
        queryParams.append("?"); // Start query parameters

        // Add parameters if present
        fromDate.ifPresent(f -> queryParams.append("fromDate=").append(convertDateToISO(f)).append("&"));
        toDate.ifPresent(t -> queryParams.append("toDate=").append(convertDateToISO(t)).append("&"));
        pageSize.ifPresent(p -> queryParams.append("pageSize=").append(p).append("&"));

        // Remove the last '&' if present
        if (queryParams.length() > 1) {
            queryParams.setLength(queryParams.length() - 1); // Remove the last '&'
        }

        // Complete URL
        String url = baseUrl + queryParams.toString();

        // Send the HTTP request
        BoundRequestBuilder req = buildRequest("GET", url, null);
        String resp = getResponse(req);

        List<String> walletSetIds = new ArrayList<>();
        final JSONObject obj = new JSONObject(resp);
        // extracting necessary values from the JSON object
        JSONArray walletSetArray = obj.getJSONObject("data").getJSONArray("walletSets");
        for (int i = 0; i < walletSetArray.length(); i++) {
            JSONObject walletSetObj = walletSetArray.getJSONObject(i);
            walletSetIds.add(walletSetObj.getString("id"));
        }

        return walletSetIds;
    }

    /*
     * Returns a single wallet set ID
     */
    private String getWalletSet(String walletSetId) {
        BoundRequestBuilder req = buildRequest("GET", String.format("https://api.circle.com/v1/w3s/walletSets/%s", walletSetId), null);
        String resp = getResponse(req);

        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONObject("data").getJSONObject("walletSet").getString("id");
    }

    /* Wallets */

    /*
     * Creates a wallet (developer-controlled wallet) and returns a JSON array containing wallet metadata for each wallet created
     */
    private JSONArray createWallets(Integer count, Blockchain blockchain, String walletSetId) {
       BoundRequestBuilder req = buildRequest("POST",
                                              "https://api.circle.com/v1/w3s/developer/wallets",
                                              Map.of("count", Integer.toString(count),
                                                     "walletSetId", walletSetId,
                                                     "blockchains", Stream.generate(() -> blockchain.toString())
                                                     .limit(count)
                                                     .collect(Collectors.toList())
                                                     ));
        String resp = getResponse(req);
        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONArray("wallets");

    }

    /*
     * Creates a wallet (developer-controlled wallet) and returns a JSON object containing wallet metadata
     */
    private JSONArray createWallets(Integer count, List<Blockchain> blockchains, String walletSetId) {
        BoundRequestBuilder req = buildRequest("POST",
                                               "https://api.circle.com/v1/w3s/developer/wallets",
                                               Map.of("count", Integer.toString(count),
                                                      "walletSetId", walletSetId,
                                                      "blockchains", blockchains
                                                      ));
        String resp = getResponse(req);
        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONArray("wallets");
    }

    /*
     * Creates a wallet (developer-controlled wallet) and returns a JSON object containing wallet metadata
     */
    private JSONObject createWallet(Blockchain blockchain, String walletSetId) {
        BoundRequestBuilder req = buildRequest("POST",
                                               "https://api.circle.com/v1/w3s/developer/wallets",
                                               Map.of("count", "1",
                                                      "walletSetId", walletSetId,
                                                      "blockchains", List.of(blockchain.toString())
                                                      ));
         String resp = getResponse(req);
         JSONObject jsonObj = new JSONObject(resp);
         return jsonObj.getJSONArray("wallets").getJSONObject(0);
     }

    // private String getWallets(Optional<String> address, Optional<Blockchain> blockchain, Optional<String> scaCore, Optional<UUID> walletSetId, Optional<String> refId, Optional<Date> fromDate, Optional<Date> toDate) {
    //     BoundRequestBuilder req = buildRequest("GET", "https://api.circle.com/v1/w3s/wallets", null);
    //     return getResponse(req);
    // }
    private JSONArray getWallets(Optional<String> address, 
                          Optional<Blockchain> blockchain, 
                          Optional<String> walletSetId, 
                          Optional<String> refId, 
                          Optional<Date> fromDate, 
                          Optional<Date> toDate,
                          Optional<Integer> pageSize) {
        // Base URL for the API request
        String baseUrl = "https://api.circle.com/v1/w3s/wallets";
        
        // Build query parameters
        StringBuilder queryParams = new StringBuilder();
        queryParams.append("?"); // Start query parameters

        // Add parameters if present
        address.ifPresent(a -> queryParams.append("address=").append(a).append("&"));
        blockchain.ifPresent(b -> queryParams.append("blockchain=").append(b.toString()).append("&"));
        walletSetId.ifPresent(w -> queryParams.append("walletSetId=").append(w).append("&"));
        refId.ifPresent(r -> queryParams.append("refId=").append(r).append("&"));
        fromDate.ifPresent(f -> queryParams.append("fromDate=").append(convertDateToISO(f)).append("&"));
        toDate.ifPresent(t -> queryParams.append("toDate=").append(convertDateToISO(t)).append("&"));
        pageSize.ifPresent(p -> queryParams.append("pageSize=").append(p).append("&"));

        // Remove the last '&' if present
        if (queryParams.length() > 1) {
            queryParams.setLength(queryParams.length() - 1); // Remove the last '&'
        }

        // Complete URL
        String url = baseUrl + queryParams.toString();

        // Send the HTTP request
        BoundRequestBuilder req = buildRequest("GET", url, null);
        String resp = getResponse(req);
        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONObject("data").getJSONArray("wallets");
    }

    /*
     * Retrieves a JSON object containing metadata for a specified wallet
     */
    private JSONObject retrieveWallet(String walletId) {
        BoundRequestBuilder req = buildRequest("GET", String.format("https://api.circle.com/v1/w3s/wallets/%s", walletId), null);
        String resp = getResponse(req);
        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONObject("data").getJSONObject("wallet");
    }

    /*
     * Returns a JSON array containing JSON objects, each representing a balance for a particular token in the specified wallet
     */
    private JSONArray getTokenBalance(String walletId,
                                   Optional<Boolean> includeAll,
                                   Optional<String> tokenName,
                                   Optional<String> tokenAddress,
                                   Optional<Integer> pageSize) {
        // Base URL for the API request
        String baseUrl = String.format("https://api.circle.com/v1/w3s/wallets/%s/balances", walletId);
        
        // Build query parameters
        StringBuilder queryParams = new StringBuilder();
        queryParams.append("?"); // Start query parameters

        // Add parameters if present
        includeAll.ifPresent(i -> queryParams.append("includeAll=").append(i).append("&"));
        tokenName.ifPresent(n -> queryParams.append("name=").append(n).append("&"));
        tokenAddress.ifPresent(a -> queryParams.append("address=").append(a).append("&"));
        pageSize.ifPresent(p -> queryParams.append("pageSize=").append(p).append("&"));

        // Remove the last '&' if present
        if (queryParams.length() > 1) {
            queryParams.setLength(queryParams.length() - 1); // Remove the last '&'
        }

        // Complete URL
        String url = baseUrl + queryParams.toString();

        BoundRequestBuilder req = buildRequest("GET", baseUrl, null);
        String resp = getResponse(req);
        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONObject("data").getJSONArray("tokenBalances");
    }

    /* Helper methods */

    // Converts a Date into an ISO 8601 timestamp
    private String convertDateToISO(Date date) {
        // Convert Date to Instant
        Instant instant = date.toInstant();

        // Format as ISO 8601 string
        return DateTimeFormatter.ISO_INSTANT.format(instant);
    }

    private BoundRequestBuilder buildRequest(String method, String url, Map<String, Object> bodyParams) {
        try {
            // initializing the request object and adding Content-Type and API key headers for authorization
            BoundRequestBuilder req = client.prepare(method, url)
            .setHeader("Content-Type", "application/json");
            if (method.equals("GET")) { return req; }

            req.setHeader("Authorization", String.format("Bearer %s", dotenv.get("CIRCLE_API_KEY")));

            String body = "{";
            // POST requests must include an idempotency key to prevent duplicate requests
            if (method.equals("POST")) {
                body = body.concat(String.format("{\"idempotencyKey\":\"%s\",\"entitySecretCipherText\":\"%s\"}", generateUUID(), Encryption.generateEntityCiphertext()));
                //req.setBody(String.format("{\"idempotencyKey\":\"%s\",\"entitySecretCipherText\":\"%s\"}", generateUUID(), Encryption.generateEntityCiphertext()));
            }

            if (bodyParams != null) {
                // adding parameters to request body as desired
                for (String key : bodyParams.keySet()) {
                    Gson gson = new Gson();
                    Object value = bodyParams.get(key);
                    // Converting Lists into valid JSON strings
                    if (value instanceof List) {
                        String listAsString = gson.toJson(value);
                        body = body.concat(String.format("\"%s\":\"%s,\"", key, listAsString));
                        continue;
                    }
                    // if the given parameter is a string and not a list, then add it directly to the request body
                    body = body.concat(String.format("\"%s\":\"%s,\"", key, value));
                }
            }

            if (body.charAt(body.length()-1) == ',') {
                body = body.substring(0, body.length()-1);
            }
            body = body.concat("}");
            req.setBody(body);
            return req;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getResponse(BoundRequestBuilder req) {
        try {
            String response = req.execute()
            .toCompletableFuture()
            .thenApply(resp -> {
                try {
                    int statusCode = resp.getStatusCode();
                    if(statusCode < 200 || statusCode >= 300) {
                        throw new Exception(String.format("HTTP request failed with status %d -- %s", statusCode, resp.getStatusText()));
                    }
                    return resp.getResponseBody();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            })
            .join();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    // private parseResponse(String jsonString) {
    //     final JSONObject resp = new JSONObject(jsonString);
    //     resp.get

    // }

}
