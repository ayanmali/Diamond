// package com.diamond.diamond.microservices;

// import org.springframework.stereotype.Service;

// import com.diamond.diamond.transactions.Blockchain;
// import com.diamond.diamond.transactions.StablecoinCurrency;
// import com.diamond.diamond.transactions.AccountWallet;

// import proto.Service.SwapStablesToSpotRequest;
// import proto.Service.SwapStablesToSpotResponse;
// import proto.SwapsGrpc;

// @Service
// public class StablecoinSwapsClientService {

//     private final SwapsGrpc.SwapsBlockingStub swapsStub;

//     public StablecoinSwapsClientService(SwapsGrpc.SwapsBlockingStub swapsStub) {
//         this.swapsStub = swapsStub;
//     }

//     public void swapStablesToSpot(AccountWallet AccountWallet, StablecoinCurrency initialStablecoinCurrency, double amount, Blockchain targetToken) {
//         // Defining the AccountWallet object to be sent over gRPC to the microservice
//         proto.Service.AccountWallet AccountWalletGRPC = proto.Service.AccountWallet.newBuilder()
//                 .setAccountID(AccountWallet.getAccountId())
//                 .setWalletAddress(AccountWallet.getAddress())
//                 .setChain(AccountWallet.getChain().toString())
//                 .build();

//         // Defining the parameters to be sent in the gRPC request
//         SwapStablesToSpotRequest request = SwapStablesToSpotRequest.newBuilder()
//                 .setAccountWallet(AccountWalletGRPC)
//                 .setInitialStablecoinCurrency(initialStablecoinCurrency.toString())
//                 .setAmount(amount)
//                 .setTargetToken(targetToken.toString())
//                 .build();

//         // Make the gRPC call
//         SwapStablesToSpotResponse response = swapsStub.swapStablesToSpot(request);
//         // Process the response
//     }

// }
