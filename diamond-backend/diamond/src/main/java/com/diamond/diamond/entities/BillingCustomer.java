//package com.diamond.diamond.entities;
// package com.diamond.diamond.payments;

// import com.diamond.diamond.transactions.CustomerWallet;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Table;

// /*
//  * Customer class used for invoices and subscription payments (ex. B2B)
//  */
// @Table(name="billing_customers")
// @Entity
// public class BillingCustomer extends Customer {

//     private String firstName;
//     private String lastName;
//     private String businessName;

//     public BillingCustomer(String email, CustomerWallet wallet) {
//         super(email, wallet);
//     }

//     // public void addCustomerWallet(CustomerWallet wallet) {
//     //     // Setting the priority for the new wallet
//     //     wallet.setWalletPriority(wallets.size());
//     //     // Adding the wallet to this customer's wallets list
//     //     wallets.add(wallet);
//     //     // Sorting the Customer's wallets list by priority
//     //     wallets.sort(Comparator.comparing(CustomerWallet::getWalletPriority));
//     // }

//     public String getFirstName() {
//         return firstName;
//     }

//     public void setFirstName(String firstName) {
//         this.firstName = firstName;
//     }

//     public String getLastName() {
//         return lastName;
//     }

//     public void setLastName(String lastName) {
//         this.lastName = lastName;
//     }

//     public String getBusinessName() {
//         return businessName;
//     }

//     public void setBusinessName(String businessName) {
//         this.businessName = businessName;
//     }

// }
