package com.diamond.diamond.grpc_client;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class CircleGrpcClient {
    private final ManagedChannel channel;
    private final CircleGrpc.CircleBlockingStub blockingStub;

    public CircleGrpcClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()  // For development only. Use TLS in production!
                .build();
        blockingStub = CircleGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public Optional<UUID> createWalletSet(String walletName, String idempotencyKey) {
        CreateWalletSetRequest request = CreateWalletSetRequest.newBuilder()
                .setWalletName(walletName)
                .setIdempotencyKey(idempotencyKey)
                .build();

        try {
            CreateWalletSetResponse response = blockingStub.createWalletSet(request);
            return Optional.ofNullable(UUID.fromString(response.getId()));
            //System.out.println("Response received: " + response.getId());
        } catch (Exception e) {
            System.err.println("RPC failed: " + e.getMessage());
            return Optional.empty();
        }
    }

    // public static void main(String[] args) throws InterruptedException {
    //     CircleClient client = new CircleClient("localhost", 50051);
    //     try {
    //         client.createWalletSet("test_wallet");
    //     } finally {
    //         client.shutdown();
    //     }
    // }
}