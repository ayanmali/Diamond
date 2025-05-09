// package com.diamond.diamond.entities.payments.walletdistribution;
// package com.diamond.diamond.payments.walletdistribution;

// import java.util.Map;

// import com.diamond.diamond.transactions.AccountWallet;

// public class PaymentDistribution {

//     private static final double MARGIN_OF_ERROR = 0.001;

//     private Map<AccountWallet, BigDecimal> mappings;
//     private String name;
//     private String description;

//     public PaymentDistribution(Map<AccountWallet, BigDecimal> mappings, String name) throws Exception {
//         if (isValidDistribution(mappings)) {
//             this.mappings = mappings;
//             this.name = name;
//         }
//     }

//     /*
//      * Helper method to determine if the given mappings of payments for each wallet is valid (i.e. each wallet's percentages add up to 1)
//      */
//     private static boolean isValidDistribution(Map<AccountWallet, BigDecimal> mappings) throws Exception {
//         if (mappings.isEmpty()) {
//             return true;
//         }

//         // TODO: Add regex validation to ensure all wallets have valid addresses and are of the same chain
//         // Calculating the sum of percentages across all wallets in the mappings
//         double total = 0.0;
//         for (BigDecimal val : mappings.values()) {
//             total += val;
//             // if the total ever becomes greater than 1, then this mappings is not valid
//             if (total > 1) {
//                 // throwing an error if it is invalid
//                 throw new Exception("Wallet mappings must sum to 100%.");
//             }
//         }
//         // Using a very small constant to determine if the total is close enough to 1
//         if (Math.abs(total - 1) > MARGIN_OF_ERROR) {
//             // throwing an error if it is invalid
//             throw new Exception("Wallet mappingss must sum to 100%.");
//         }
//         return true;
//     }

//     public Map<AccountWallet, BigDecimal> getMappings() {
//         return mappings;
//     }

//     public void setDistribution(Map<AccountWallet, BigDecimal> mappings) throws Exception {
//         // Changes the object mappings if the provided mappings is valid
//         if (isValidDistribution(mappings)) {
//             this.mappings = mappings;
//         }

//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

// }
