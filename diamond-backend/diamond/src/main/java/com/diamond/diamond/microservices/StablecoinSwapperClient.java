// package com.diamond.diamond.microservices;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import io.grpc.ManagedChannel;
// import io.grpc.ManagedChannelBuilder;

// @Configuration
// public class StablecoinSwapperClient {

//     @Bean
//     public ManagedChannel grpcChannel(
//             @Value("${grpc.server.host}") String host,
//             @Value("${grpc.server.port}") int port) {
//         return ManagedChannelBuilder
//                 .forAddress(host, port)
//                 .usePlaintext() // to be removed in prod and use TLS instead
//                 .build();
//     }

// }
