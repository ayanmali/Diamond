package com.diamond.diamond.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import com.diamond.diamond.grpc_client.CircleGrpcClient;

@Configuration
public class CircleGrpcConfig {
    @Value("${grpc.server.host}")
    private String grpcHost;

    @Value("${grpc.server.port}")
    private int grpcPort;

    @Bean
    public CircleGrpcClient circleGrpcClient() {
        return new CircleGrpcClient(grpcHost, grpcPort);
    }
}


