// package com.diamond.diamond.microservices;

// import org.springframework.stereotype.Service;

// import com.diamond.diamond.transactions.Blockchain;
// import com.diamond.diamond.transactions.StablecoinCurrency;
// import com.diamond.diamond.transactions.VendorWallet;

// import proto.Service.SwapStablesToSpotRequest;
// import proto.Service.SwapStablesToSpotResponse;
// import proto.SwapsGrpc;

// @Service
// public class StablecoinSwapsClientService {

//     private final SwapsGrpc.SwapsBlockingStub swapsStub;

//     public StablecoinSwapsClientService(SwapsGrpc.SwapsBlockingStub swapsStub) {
//         this.swapsStub = swapsStub;
//     }

//     public void swapStablesToSpot(VendorWallet vendorWallet, StablecoinCurrency initialStablecoinCurrency, double amount, Blockchain targetToken) {
//         // Defining the VendorWallet object to be sent over gRPC to the microservice
//         proto.Service.VendorWallet vendorWalletGRPC = proto.Service.VendorWallet.newBuilder()
//                 .setVendorID(vendorWallet.getVendorId())
//                 .setWalletAddress(vendorWallet.getAddress())
//                 .setChain(vendorWallet.getChain().toString())
//                 .build();

//         // Defining the parameters to be sent in the gRPC request
//         SwapStablesToSpotRequest request = SwapStablesToSpotRequest.newBuilder()
//                 .setVendorWallet(vendorWalletGRPC)
//                 .setInitialStablecoinCurrency(initialStablecoinCurrency.toString())
//                 .setAmount(amount)
//                 .setTargetToken(targetToken.toString())
//                 .build();

//         // Make the gRPC call
//         SwapStablesToSpotResponse response = swapsStub.swapStablesToSpot(request);
//         // Process the response
//     }

// }
