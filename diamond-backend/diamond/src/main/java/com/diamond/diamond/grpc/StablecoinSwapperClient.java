package com.diamond.diamond.grpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class StablecoinSwapperClient {

    @Bean
    public ManagedChannel grpcChannel(String host, int port) {
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext() // to be removed in prod and use TLS instead
                .build();
    }


}
