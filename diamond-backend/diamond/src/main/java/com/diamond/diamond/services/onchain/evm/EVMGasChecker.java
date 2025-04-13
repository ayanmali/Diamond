// package com.diamond.diamond.services.onchain.evm;

// import java.math.BigInteger;

// import org.springframework.stereotype.Service;
// import org.web3j.protocol.core.methods.response.EthGasPrice;

// @Service
// public class EVMGasChecker {
//     public static void main(String[] args) {
//         EthGasPrice gasPriceResponse = web3j.ethGasPrice().send();
//         BigInteger currentGasPrice = gasPriceResponse.getGasPrice();
//         System.out.println("Current Gas Price: " + currentGasPrice);

//         // Suppose tokenContractAddress is your deployed token address.
//         String tokenContractAddress = "0xYourTokenContractAddress";

//         // Define details for the transfer
//         String fromAddress = "";
//         String toAddress = "0xRecipientWalletAddress";
//         BigInteger tokenAmount = new BigInteger("1000000000000000000"); // Adjust for decimals

//         // Create Function object for ERC-20 transfer
//         Function function = new Function(
//             "transfer",
//             Arrays.asList(new Address(toAddress), new Uint256(tokenAmount)),
//             Collections.emptyList()
//         );

//         // Encode the function call data
//         String encodedFunction = FunctionEncoder.encode(function);

//         // Create a transaction object for estimation; note that setting nonce or value to null is fine for estimation.
//         Transaction ethCallTransaction = Transaction.createFunctionCallTransaction(
//             fromAddress,
//             null,           // nonce can be left null for estimation
//             currentGasPrice,
//             null,           // gas limit is not needed here because it will be estimated
//             tokenContractAddress,
//             encodedFunction
//         );

//         // Estimate gas usage
//         EthEstimateGas estimateGasResponse = web3j.ethEstimateGas(ethCallTransaction).send();
//         BigInteger gasLimitEstimated = estimateGasResponse.getAmountUsed();

//         System.out.println("Estimated Gas Limit: " + gasLimitEstimated);
//         BigInteger gasFeeWei = currentGasPrice.multiply(gasLimitEstimated);
//         System.out.println("Estimated Gas Fee (in wei): " + gasFeeWei);
//     }
// }
