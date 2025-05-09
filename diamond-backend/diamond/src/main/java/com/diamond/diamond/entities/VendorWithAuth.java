// package com.diamond.diamond.entities;

// // import java.time.Instant;
// // import java.util.ArrayList;
// // import java.util.List;
// // import java.util.Map;

// // import com.diamond.diamond.payments.walletdistribution.PaymentDistributor;

// // public class Account {

// //     // private final long id;
// //     // private final List<AccountWallet> wallets;
// //     private final List<AccountWallet> solWallets;
// //     private final List<AccountWallet> baseWallets;
// //     private final List<AccountWallet> bscWallets;
// //     private String businessName;
// //     private double totalUSDCBalance;
// //     private double totalEURCBalance;
// //     private double totalUSDTBalance;
// //     private double totalSOLBalance;
// //     private double totalBaseETHBalance;
// //     private double totalBNBBalance;
// //     private final String primaryEmail;
// //     private PaymentDistributor defaultDistributor;
// //     private final long dateCreated;

// //     /* Constructor method */
// //     public Account(String businessName, String email) throws Exception {
// //         this.businessName = businessName;
// //         this.primaryEmail = email;

// //         // this.id = 0;
// //         // this.wallets = new ArrayList<>();
// //         this.solWallets = new ArrayList<>();
// //         this.baseWallets = new ArrayList<>();
// //         this.bscWallets = new ArrayList<>();
// //         this.totalUSDCBalance = 0;
// //         this.totalEURCBalance = 0;
// //         this.totalSOLBalance = 0;
// //         this.totalBaseETHBalance = 0;
// //         this.defaultDistributor = new PaymentDistributor(this, "Default");
// //         this.dateCreated = Instant.now().toEpochMilli();
// //     }

// //     // public void addWallet(AccountWallet wallet) {
// //     //     this.wallets.add(wallet);
// //     // }
// //     // public long getId() {
// //     //     return id;
// //     // }
// //     // public List<AccountWallet> getWallets() {
// //     //     return wallets;
// //     // }
// //     public List<AccountWallet> getSolWallets() {
// //         return solWallets;
// //     }

// //     public List<AccountWallet> getBaseWallets() {
// //         return baseWallets;
// //     }

// //     public List<AccountWallet> getBscWallets() {
// //         return bscWallets;
// //     }

// //     public List<AccountWallet> getWallets(Blockchain chain) {
// //         switch (chain) {
// //             case SOL -> {
// //                 return this.getSolWallets();
// //             }
// //             case BASE -> {
// //                 return this.getBaseWallets();
// //             }
// //             case BSC -> {
// //                 return this.getBscWallets();
// //             }
// //             default -> throw new AssertionError();
// //         }
// //     }

// //     public String getBusinessName() {
// //         return businessName;
// //     }

// //     public double getTotalUSDCBalance() {
// //         return totalUSDCBalance;
// //     }

// //     public double getTotalEURCBalance() {
// //         return totalEURCBalance;
// //     }

// //     public double getTotalUSDTBalance() {
// //         return totalUSDTBalance;
// //     }

// //     public double getTotalSOLBalance() {
// //         return totalSOLBalance;
// //     }

// //     public double getTotalBaseETHBalance() {
// //         return totalBaseETHBalance;
// //     }

// //     public double getTotalBNBBalance() {
// //         return totalBNBBalance;
// //     }

// //     public String getPrimaryEmail() {
// //         return primaryEmail;
// //     }

// //     public long getDateCreated() {
// //         return dateCreated;
// //     }

// //     public void setBusinessName(String businessName) {
// //         this.businessName = businessName;
// //     }

// //     public void setTotalUSDCBalance(double totalUSDCBalance) {
// //         this.totalUSDCBalance = totalUSDCBalance;
// //     }

// //     public void setTotalEURCBalance(double totalEURCBalance) {
// //         this.totalEURCBalance = totalEURCBalance;
// //     }

// //     public void setTotalUSDTBalance(double totalUSDTBalance) {
// //         this.totalUSDTBalance = totalUSDTBalance;
// //     }

// //     public void setTotalSOLBalance(double totalSOLBalance) {
// //         this.totalSOLBalance = totalSOLBalance;
// //     }

// //     public void setTotalBaseEthBalance(double totalBaseETHBalance) {
// //         this.totalBaseETHBalance = totalBaseETHBalance;
// //     }

// //     public void setTotalBNBBalance(double totalBNBBalance) {
// //         this.totalBNBBalance = totalBNBBalance;
// //     }

// //     public void addSolWallet(AccountWallet wallet) {
// //         // add regex validation for the wallet's address to ensure it is on the right chain
// //         solWallets.add(wallet);
// //     }

// //     public void addBaseWallet(AccountWallet wallet) {
// //         // add regex validation for the wallet's address to ensure it is on the right chain
// //         baseWallets.add(wallet);
// //     }

// //     public void addBscWallet(AccountWallet wallet) {
// //         // add regex validation for the wallet's address to ensure it is on the right chain
// //         bscWallets.add(wallet);
// //     }

// //     /*
// //      * Creates a new wallet for the Account on the specified chain
// //      */
// //     public AccountWallet createWallet(Blockchain chain) {
// //        AccountWallet wallet = new AccountWallet("", "My Wallet", 0, chain);
// //        addWallet(wallet);
// //        return wallet;
// //     }

// //     public void addWallet(AccountWallet wallet) {
// //         switch (wallet.getChain()) {
// //             case SOL -> addSolWallet(wallet);
// //             case BASE -> addBaseWallet(wallet);
// //             case BSC -> addBscWallet(wallet);
// //             default -> throw new AssertionError();
// //         }
// //     }

// //     public PaymentDistributor getDefaultDistributor() {
// //         return defaultDistributor;
// //     }

// //     public void setDefaultDistributor(PaymentDistributor defaultDistributor) {
// //         this.defaultDistributor = defaultDistributor;
// //     }

// //     public void setDefaultDistributor(Map<AccountWallet, BigDecimal> mappings) throws Exception {
// //         this.defaultDistributor = new PaymentDistributor(this, mappings, this.getDefaultDistributor().getDistribution().getName());
// //     }

// //     public boolean hasDefaultDistributor() {
// //         return defaultDistributor.getDistribution().getMappings().size() > 1;
// //     }

// // }

// // package com.diamond.diamond.entities;

// import java.util.Collection;
// import java.util.Date;
// import java.util.List;
// import java.util.UUID;

// import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.UpdateTimestamp;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "Accounts")
// // Implementing the UserDetails interface to use user data for authentication
// public class Account implements UserDetails {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     @Column(nullable = false)
//     private UUID id;

//     @Column(nullable = false, name="business_name")
//     private String businessName;

//     @OneToMany(mappedBy="address", cascade=CascadeType.ALL)
//     private List<AccountWallet> wallets;

//     @Column(unique = true, length = 100, nullable = false)
//     private String email;

//     @Column(nullable = false)
//     private String password;

//     @CreationTimestamp
//     @Column(updatable = false, name = "created_at")
//     private Date createdAt;

//     @UpdateTimestamp
//     @Column(name = "updated_at")
//     private Date updatedAt;

//     // private final List<AccountWallet> solWallets;
//     // private final List<AccountWallet> baseWallets;
//     // private final List<AccountWallet> bscWallets;

//     // private double totalUSDCBalance;
//     // private double totalEURCBalance;
//     // private double totalUSDTBalance;
//     // private double totalSOLBalance;
//     // private double totalBaseETHBalance;
//     // private double totalBNBBalance;

//     // private PaymentDistributor defaultDistributor;

//     /* Implementing the interface */
//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return List.of();
//     }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     @Override
//     public String getUsername() {
//         return email;
//     }

//     public UUID getId() {
//         return id;
//     }

//     public String getBusinessName() {
//         return businessName;
//     }

//     public Date getCreatedAt() {
//         return createdAt;
//     }

//     public Date getUpdatedAt() {
//         return updatedAt;
//     }

//     // public PaymentDistributor getDefaultDistributor() {
//     //     return defaultDistributor;
//     // }

//     public void setEmail(String newEmail) {
//         this.email = newEmail;
//     }

//     public void setPassword(String newPassword) {
//         this.password = newPassword;
//     }

//     public void setBusinessName(String newBusinessName) {
//         this.businessName = newBusinessName;
//     }

//     // public void setDefaultDistributor(PaymentDistributor defaultDistributor) {
//     //     this.defaultDistributor = defaultDistributor;
//     // }

//     public List<AccountWallet> getWallets() {
//         return wallets;
//     }

//     public void setWallets(List<AccountWallet> wallets) {
//         this.wallets = wallets;
//     }

//     public void addWallet(AccountWallet wallet) {
//         wallets.add(wallet);
//     }

//     public void removeWallet(AccountWallet wallet) throws Exception {
//         if (wallets.contains(wallet)) {
//             wallets.remove(wallet);
//         } else {
//             throw new Exception(String.format("Wallet with address %s not found.", wallet.getAddress()));
//         }
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

// }