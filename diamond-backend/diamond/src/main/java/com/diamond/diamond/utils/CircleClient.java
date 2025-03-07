package com.diamond.diamond.utils;

import java.util.Map;
import java.util.UUID;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;

import io.github.cdimascio.dotenv.Dotenv;

public class CircleClient {
    private final Dotenv dotenv;
    private final AsyncHttpClient client;

    public CircleClient() {
        // loads environment variables defined in .env file
        dotenv = Dotenv.configure().load();
        client = new DefaultAsyncHttpClient();
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    private BoundRequestBuilder buildRequest(String method, String url, Map<String, String> bodyParams) {
        try {
            // initializing the request object and adding Content-Type and API key headers for authorization
            BoundRequestBuilder req = client.prepare(method, url)
            .setHeader("Content-Type", "application/json")
            .setHeader("Authorization", String.format("Bearer %s", dotenv.get("CIRCLE_API_KEY")));

            String body = "{";
            // POST requests must include an idempotency key to prevent duplicate requests
            if (method.equals("POST")) {
                body = body.concat(String.format("{\"idempotencyKey\":\"%s\",\"entitySecretCipherText\":\"%s\"}", generateUUID(), dotenv.get("CIRCLE_ENTITY_SECRET_CIPHERTEXT")));
                //req.setBody(String.format("{\"idempotencyKey\":\"%s\",\"entitySecretCipherText\":\"%s\"}", generateUUID(), dotenv.get("CIRCLE_ENTITY_SECRET_CIPHERTEXT")));
            }

            for (String key : bodyParams.keySet()) {
                body = body.concat(String.format("\"%s\":\"%s\"", key, bodyParams.get(key)));
            }
            body = body.concat("}");
            req.setBody(body);
            return req;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
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

    private String createWalletSet(String walletName) {
        try {
            BoundRequestBuilder req = buildRequest("POST",
                                                   "https://api.circle.com/v1/w3s/developer/walletSets",
                                                   Map.of("name", walletName));
            String response = req.execute()
            .toCompletableFuture()
            .thenApply(resp -> {return resp.getResponseBody();})
            .join();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
