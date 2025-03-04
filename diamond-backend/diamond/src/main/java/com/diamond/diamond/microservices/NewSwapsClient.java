// // TODO: fix the grpc client

// package com.diamond.diamond.microservices;

// //import java.util.Random;
// //import java.util.logging.Logger;

// import com.diamond.diamond.transactions.Blockchain;
// import com.diamond.diamond.transactions.StablecoinCurrency;
// import com.diamond.diamond.transactions.AccountWallet;

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
//   public void swapStablesToSpot(AccountWallet AccountWallet, StablecoinCurrency initialStablecoinCurrency, double amount, Blockchain targetToken) {
//     //info("*** GetFeature: lat={0} lon={1}", lat, lon);
//     // Defining the AccountWallet object to be sent over gRPC to the microservice
//     proto.Service.AccountWallet AccountWalletGRPC = proto.Service.AccountWallet.newBuilder()
//     .setAccountID(AccountWallet.getAccountId())
//     .setWalletAddress(AccountWallet.getAddress())
//     .setChain(AccountWallet.getChain().toString())
//     .build();

//     SwapStablesToSpotRequest request = SwapStablesToSpotRequest.newBuilder()
//     .setAccountWallet(AccountWalletGRPC)
//     .setInitialStablecoinCurrency(initialStablecoinCurrency.toString())
//     .setAmount(amount)
//     .setTargetToken(targetToken.toString())
//     .build();
                                            
//     // Make the gRPC call
//     SwapStablesToSpotResponse response = blockingStub.swapStablesToSpot(request);
//     // Process the response
//   }

// }
