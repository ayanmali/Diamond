// package com.diamond.diamond.configs;

// import java.net.http.HttpClient;
// import java.time.Duration;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.diamond.diamond.services.solana.SolanaRPCClient;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @Configuration
// public class SolanaRpcConfig {
//     @Value("${solana.rpc.url}")
//     private String rpcEndpoint;

//     private final HttpClient httpClient = HttpClient.newBuilder()
//     .connectTimeout(Duration.ofSeconds(10))
//     .build();

//     private final ObjectMapper objectMapper = new ObjectMapper();

//     @Bean
//     public SolanaRPCClient solanaRpcClient() {
//         return new SolanaRPCClient(httpClient, objectMapper);
//     }
// }
