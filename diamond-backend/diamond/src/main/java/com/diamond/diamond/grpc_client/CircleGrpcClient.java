/*
 * Used for making POST requests to Circle API (e.g. creating wallets and wallet sets)
 */

package com.diamond.diamond.grpc_client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.grpc_client.BlockchainProto;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

@Service
public class CircleGrpcClient {
    private final ManagedChannel channel;
    private final CircleGrpc.CircleStub asyncStub;

    // private final CircleGrpc.CircleBlockingStub blockingStub;

    public CircleGrpcClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()  // TODO: Use TLS in production!
                .build();
        asyncStub = CircleGrpc.newStub(channel);
        //blockingStub = CircleGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    // public Optional<UUID> createWalletSet(String walletName) {
    //     // Form the RPC request
    //     CreateWalletSetRequest request = CreateWalletSetRequest.newBuilder()
    //             .setWalletName(walletName)
    //             .build();

    //     // to store the result
    //     final Optional<UUID>[] result = new Optional[1];
        
    //     // Creating an async stub to avoid blocking when making a call
    //     asyncStub.createWalletSet(request, new StreamObserver<CreateWalletSetResponse>() {
    //         @Override
    //         public void onNext(CreateWalletSetResponse response) {
    //             result[0] = Optional.ofNullable(UUID.fromString(response.getId()));
    //         }

    //         @Override
    //         public void onError(Throwable t) {
    //             System.err.println("RPC failed: " + t.getMessage());
    //             result[0] = Optional.empty();
    //         }

    //         @Override
    //         public void onCompleted() {
    //             // Handle completion if needed
    //         }
    //     });
    //     return result[0];
    // }

    public Optional<UUID> createWalletSet(String walletName) {
        // Form the RPC request
        CreateWalletSetRequest request = CreateWalletSetRequest.newBuilder()
                .setWalletName(walletName)
                .build();
        
        final CountDownLatch finishLatch = new CountDownLatch(1);

        @SuppressWarnings("unchecked")
        final Optional<UUID>[] result = new Optional[1];
        //result[0] = result[0].map(UUID.class::cast);
        result[0] = Optional.empty();

        asyncStub.createWalletSet(request, new StreamObserver<CreateWalletSetResponse>() {
            @Override
            public void onNext(CreateWalletSetResponse response) {
                result[0] = Optional.ofNullable(UUID.fromString(response.getId()));
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                System.out.printf("CreateWalletSet failed: %s\n", status);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
            }
        });

        try {
            finishLatch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return result[0];
    }

    /*
     * Creates a single wallet for a given user.
     */
    public Optional<CreateWalletResponse> createWallet(Blockchain chain, UUID walletSetId, UUID idempotencyKey) {
        // Creating the RPC request
        CreateWalletRequest request = CreateWalletRequest.newBuilder()
        .setBlockchain(BlockchainProto.valueOf(chain.toString()))
        .setWalletSetId(walletSetId.toString())
        .setIdempotencyKey(idempotencyKey.toString())
        .build();

        final CountDownLatch finishLatch = new CountDownLatch(1);

        @SuppressWarnings("unchecked")
        final Optional<CreateWalletResponse>[] result = new Optional[1];
        result[0] = Optional.empty();

        asyncStub.createWallet(request, new StreamObserver<CreateWalletResponse>() {
            @Override
            public void onNext(CreateWalletResponse response) {
                result[0] = Optional.ofNullable(response);
            }

            @Override
            public void onError(Throwable t) {
                Status status = Status.fromThrowable(t);
                System.out.printf("CreateWallet failed: %s%n", status);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
            }
        });

        try {
            finishLatch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return result[0];
    }

    /*
     * Creates multiple wallets for a given user.
     * TODO: implement this method
     */
    // public Optional<List<CreateWalletResponse>> createWallets(Integer count, List<Blockchain> chains, UUID walletSetId, UUID idempotencyKey) {
    //     // Form the RPC request
    //     CreateWalletsRequest request = CreateWalletsRequest.newBuilder()
    //     .setBlockchains(count, BlockchainProto.SOL)
    //     .build();
    // }


    // public static void main(String[] args) throws InterruptedException {
    //     CircleClient client = new CircleClient("localhost", 50051);
    //     try {
    //         client.createWalletSet("test_wallet");
    //     } finally {
    //         client.shutdown();
    //     }
    // }
}