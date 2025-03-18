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
import org.springframework.stereotype.Component;

import static com.Keys.CIRCLE_API_KEY;
import com.diamond.diamond.types.Blockchain;
import com.google.gson.Gson;

@Component
public class CircleClient {
    //private final Dotenv dotenv;
    private final AsyncHttpClient client;

    public CircleClient() {
        // loads environment variables defined in .env file
        //dotenv = Dotenv.load();
        client = new DefaultAsyncHttpClient();
    }

    // /*
    //  * Returns formatted URL with query parameters
    //  */
    // public String addQueryParameters(String baseUrl, Map<String, Object> parameters) {
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
    
    // public String createWalletSet(String walletName) {
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
    public String createWalletSet(String walletSetName, UUID idempotencyKey) {
        BoundRequestBuilder req = buildRequest("POST",
                                               "https://api.circle.com/v1/w3s/developer/walletSets",
                                               null,
                                               Optional.of(idempotencyKey));
        String resp = getResponse(req);
        final JSONObject obj = new JSONObject(resp);
        // extracting necessary values from the JSON object
        return obj.getJSONObject("data").getJSONObject("walletSet").getString("id");
    }

    /*
     * Returns a List containing the IDs of all wallet sets based on the provided parameters
     */
    public List<String> getAllWalletSets(Optional<Date> fromDate, Optional<Date> toDate, Optional<Integer> pageSize) {
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
        BoundRequestBuilder req = buildRequest("GET", url, null, Optional.empty());
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
    public String getWalletSet(String walletSetId) {
        BoundRequestBuilder req = buildRequest("GET", String.format("https://api.circle.com/v1/w3s/walletSets/%s", walletSetId), null, Optional.empty());
        String resp = getResponse(req);

        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONObject("data").getJSONObject("walletSet").getString("id");
    }

    /* Wallets */

    /*
     * Creates a wallet (developer-controlled wallet) and returns a JSON array containing wallet metadata for each wallet created
     */
    public JSONArray createWallets(Integer count, Blockchain blockchain, String walletSetId, UUID idempotencyKey) {
       BoundRequestBuilder req = buildRequest("POST",
                                              "https://api.circle.com/v1/w3s/developer/wallets",
                                              Map.of("count", Integer.toString(count),
                                                     "walletSetId", walletSetId,
                                                     "blockchains", Stream.generate(() -> blockchain.toString())
                                                     .limit(count)
                                                     .collect(Collectors.toList())
                                                     ),
                                              Optional.of(idempotencyKey));
        String resp = getResponse(req);
        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONArray("wallets");

    }

    /*
     * Creates a wallet (developer-controlled wallet) and returns a JSON object containing wallet metadata
     */
    public JSONArray createWallets(Integer count, List<Blockchain> blockchains, String walletSetId, UUID idempotencyKey) {
        BoundRequestBuilder req = buildRequest("POST",
                                               "https://api.circle.com/v1/w3s/developer/wallets",
                                               Map.of("count", Integer.toString(count),
                                                      "walletSetId", walletSetId,
                                                      "blockchains", blockchains
                                                      ),
                                               Optional.of(idempotencyKey));
        String resp = getResponse(req);
        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONArray("wallets");
    }

    /*
     * Creates a wallet (developer-controlled wallet) and returns a JSON object containing wallet metadata
     */
    public JSONObject createWallet(Blockchain blockchain, UUID walletSetId, UUID idempotencyKey) {
        BoundRequestBuilder req = buildRequest("POST",
                                               "https://api.circle.com/v1/w3s/developer/wallets",
                                               Map.of("count", "1",
                                                      "walletSetId", walletSetId,
                                                      "blockchains", List.of(blockchain.toString())
                                                      ),
                                               Optional.of(idempotencyKey));
         String resp = getResponse(req);
         JSONObject jsonObj = new JSONObject(resp);
         return jsonObj.getJSONArray("wallets").getJSONObject(0);
     }

    // public String getWallets(Optional<String> address, Optional<Blockchain> blockchain, Optional<String> scaCore, Optional<UUID> walletSetId, Optional<String> refId, Optional<Date> fromDate, Optional<Date> toDate) {
    //     BoundRequestBuilder req = buildRequest("GET", "https://api.circle.com/v1/w3s/wallets", null);
    //     return getResponse(req);
    // }
    public JSONArray getWallets(Optional<String> address, 
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
        BoundRequestBuilder req = buildRequest("GET", url, null, Optional.empty());
        String resp = getResponse(req);
        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONObject("data").getJSONArray("wallets");
    }

    /*
     * Retrieves a JSON object containing metadata for a specified wallet
     */
    public JSONObject retrieveWallet(String walletId) {
        BoundRequestBuilder req = buildRequest("GET", String.format("https://api.circle.com/v1/w3s/wallets/%s", walletId), null, Optional.empty());
        String resp = getResponse(req);
        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONObject("data").getJSONObject("wallet");
    }

    /*
     * Returns a JSON array containing JSON objects, each representing a balance for a particular token in the specified wallet
     */
    public JSONArray getTokenBalance(String walletId,
                                   Optional<Boolean> includeAll,
                                   Optional<String> tokenName,
                                   Optional<String> tokenAddress,
                                   Optional<Integer> pageSize) {
        // Base URL for the API request
        String baseUrl = String.format("https://api.circle.com/v1/w3s/wallets/%s/balances", walletId);
        
        // Build query parameters
        StringBuilder queryParams = new StringBuilder();

        // Add parameters if present
        queryParams.append("?includeAll=");
        String toAppend = includeAll.isPresent() ? "true" : "false";
        queryParams.append(toAppend).append("&");

        tokenName.ifPresent(n -> queryParams.append("name=").append(n).append("&"));
        tokenAddress.ifPresent(a -> queryParams.append("address=").append(a).append("&"));
        pageSize.ifPresent(p -> queryParams.append("pageSize=").append(p).append("&"));

        // Remove the last '&' if present
        if (queryParams.length() > 1) {
            queryParams.setLength(queryParams.length() - 1); // Remove the last '&'
        }

        // Complete URL
        String url = baseUrl + queryParams.toString();

        BoundRequestBuilder req = buildRequest("GET", baseUrl, null, Optional.empty());
        String resp = getResponse(req);
        JSONObject jsonObj = new JSONObject(resp);
        return jsonObj.getJSONObject("data").getJSONArray("tokenBalances");
    }

    /* Helper methods */

    // Converts a Date into an ISO 8601 timestamp
    public String convertDateToISO(Date date) {
        // Convert Date to Instant
        Instant instant = date.toInstant();

        // Format as ISO 8601 string
        return DateTimeFormatter.ISO_INSTANT.format(instant);
    }

    public BoundRequestBuilder buildRequest(String method, String url, Map<String, Object> bodyParams, Optional<UUID> idempotencyKey) {
        try {
            // initializing the request object and adding Content-Type and API key headers for authorization
            BoundRequestBuilder req = client.prepare(method, url)
            .setHeader("Content-Type", "application/json");
            if (method.equals("GET")) { return req; }

            req.setHeader("Authorization", String.format("Bearer %s", CIRCLE_API_KEY));

            String body = "{";
            // POST requests must include an idempotency key to prevent duplicate requests
            if (method.equals("POST")) {
                if (!idempotencyKey.isPresent()) {
                    throw new Exception("Idempotency key not found. POST requests must include a UUID v4 idempotency key.");
                }
                body = body.concat(String.format("\"idempotencyKey\":\"%s\",\"entitySecretCipherText\":\"%s\",", idempotencyKey.get().toString(), ""));
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
                        body = body.concat(String.format("\"%s\":\"%s\",", key, listAsString));
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
            System.out.println(body);
            return req;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getResponse(BoundRequestBuilder req) {
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

    // public UUID generateUUID() {
    //     return UUID.randomUUID();
    // }

    // public parseResponse(String jsonString) {
    //     final JSONObject resp = new JSONObject(jsonString);
    //     resp.get

    // }

}
