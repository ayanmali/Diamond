// // TODO: fix the grpc client

// package com.diamond.diamond.microservices;

// //import java.util.Random;
// //import java.util.logging.Logger;

// import com.diamond.diamond.transactions.Blockchain;
// import com.diamond.diamond.transactions.StablecoinCurrency;
// import com.diamond.diamond.transactions.VendorWallet;

// import io.grpc.Channel;
// import proto.Service.SwapStablesToSpotRequest;
// import proto.Service.SwapStablesToSpotResponse;
// import proto.SwapsGrpc;
// import proto.SwapsGrpc.SwapsBlockingStub;
// //import proto.SwapsGrpc.SwapsStub;

// public class NewSwapsClient {
//     // private static final Logger logger = Logger.getLogger(NewSwapsClient.class.getName());

//   private final SwapsBlockingStub blockingStub;
// //   private final SwapsStub asyncStub;

// //   private Random random = new Random();
//   //private TestHelper testHelper;

//   /** Construct client for accessing RouteGuide server using the existing channel. */
//   public NewSwapsClient(Channel channel) {
//     blockingStub = SwapsGrpc.newBlockingStub(channel);
//     //asyncStub = SwapsGrpc.newStub(channel);
//   }

//   /**
//    * Blocking unary call example.  Calls getFeature and prints the response.
//    */
//   public void swapStablesToSpot(VendorWallet vendorWallet, StablecoinCurrency initialStablecoinCurrency, double amount, Blockchain targetToken) {
//     //info("*** GetFeature: lat={0} lon={1}", lat, lon);
//     // Defining the VendorWallet object to be sent over gRPC to the microservice
//     proto.Service.VendorWallet vendorWalletGRPC = proto.Service.VendorWallet.newBuilder()
//     .setVendorID(vendorWallet.getVendorId())
//     .setWalletAddress(vendorWallet.getAddress())
//     .setChain(vendorWallet.getChain().toString())
//     .build();

//     SwapStablesToSpotRequest request = SwapStablesToSpotRequest.newBuilder()
//     .setVendorWallet(vendorWalletGRPC)
//     .setInitialStablecoinCurrency(initialStablecoinCurrency.toString())
//     .setAmount(amount)
//     .setTargetToken(targetToken.toString())
//     .build();
                                            
//     // Make the gRPC call
//     SwapStablesToSpotResponse response = blockingStub.swapStablesToSpot(request);
//     // Process the response
//   }

// }
