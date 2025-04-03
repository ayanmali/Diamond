package com.diamond.diamond.entities.payments;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.validation.constraints.Positive;

/*
 * Used to define the generic attributes and methods across the structures (not records) of all types of payments.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 public abstract class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=false)
    private UUID id;
    
    @Column(nullable=false)
    @Positive
    private BigDecimal amount;
    //final AccountWallet businessWallet;

    // @ManyToOne
    // @JoinColumn(name="account_id", referencedColumnName="id", nullable=false)
    // private Account account;
    @Column(name="account_id", nullable=false)
    private UUID accountId;

    // remove
    // @ManyToOne
    // @JoinColumn(name="customer_id", nullable=false)
    // private Customer customer;

    // @Enumerated(EnumType.STRING)
    // @Column(nullable=false)
    // private StablecoinCurrency currency;

    @Column(name="accept_usdc", nullable=false)
    private Boolean acceptUsdc = false;

    @Column(name="accept_eurc", nullable=false)
    private Boolean acceptEurc = false;

    @Column(name="accept_usdt", nullable=false)
    private Boolean acceptUsdt = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Blockchain chain;

    // remove
    // @Enumerated(EnumType.STRING)
    // @Column(nullable=false)
    // private PaymentStatus status;

    // used to define how payments are allocated between the account's wallets, if desired
    //private PaymentDistributor distributor;
    // @ManyToMany
    // @JoinTable(
    //     name = "payment_wallet_distribution", // Optional: Custom name for the join table
    //     joinColumns = @JoinColumn(name = "payment_id"),
    //     inverseJoinColumns = @JoinColumn(name = "wallet_id")
    // )
    // private List<AccountWallet> walletDistribution;
    @ElementCollection
    @CollectionTable(name = "account_wallet_ids", joinColumns = @JoinColumn(name = "payment_id"))
    @Column(name = "wallet_id")
    private List<UUID> walletDistribution;


    @ElementCollection
    @CollectionTable(
        name = "payment_metadata",
        joinColumns = @JoinColumn(name = "payment_id")
    )
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> metadata;

    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

    // /*
    //  * Constructor method that uses a single wallet for routing payments by default
    //  */
    // public Payment(double amount, Account account, Customer customer, StablecoinCurrency currency, Blockchain chain) throws Exception {
    //     this.amount = amount;
    //     this.account = account;
    //     this.customer = customer;
    //     this.currency = currency;
    //     this.chain = chain;
    //     this.paymentStatus = PaymentStatus.PENDING;

    //     // retrieves the account's first wallet on the speciied chain
    //     AccountWallet wallet = this.account.getWallets(chain).get(0);
    //     // Allocates all incoming payments to the account's primary wallet by default
    //     this.distributor = new PaymentDistributor(account,
    //             Map.of(wallet, 1.0),
    //             "");
    // }

    public Payment() {}

    /*
     * Alternate constructor -- to be overridden by FlexiblePayment class (doesn't involve an amount value)
     */
    public Payment(UUID accountId, Blockchain chain, List<UUID> walletDistributionIds, List<StablecoinCurrency> acceptedCurrencies) {
        this.amount = null;
        this.accountId = accountId;

        // Setting each of the accepted currency flags
        for (StablecoinCurrency currency : acceptedCurrencies) {
            switch (currency) {
                case USDC:
                    this.acceptUsdc = true;
                    break;
                case EURC:
                    this.acceptEurc = true;
                    break;
                case USDT:
                    this.acceptUsdt = true;
                    break;
                case SOL:
                    break;
                default:
                    throw new AssertionError();
            }
        }

        this.chain = chain;
        this.walletDistribution = walletDistributionIds;
        this.metadata = new HashMap<>();
    }
    /*
     * Constructor method that uses a provided Map to route payments to multiple wallets
     */
    public Payment(BigDecimal amount, UUID accountId, Blockchain chain, List<UUID> walletDistributionIds, List<StablecoinCurrency> acceptedCurrencies/*, PaymentDistributor distributor*/) /*throws Exception*/ {
        this.amount = amount;
        this.accountId = accountId;
        //this.currency = currency;

        // Setting each of the accepted currency flags
        for (StablecoinCurrency currency : acceptedCurrencies) {
            switch (currency) {
                case USDC:
                    this.acceptUsdc = true;
                    break;
                case EURC:
                    this.acceptEurc = true;
                    break;
                case USDT:
                    this.acceptUsdt = true;
                    break;
                case SOL:
                    break;
                default:
                    throw new AssertionError();
            }
        }
        this.chain = chain;
        this.walletDistribution = walletDistributionIds;
        this.metadata = new HashMap<>();
        // this.distributor = distributor;
        // this.distributor = new PaymentDistributor(account, mappings, "");
    }

    public Payment(BigDecimal amount, UUID accountId, Blockchain chain, UUID accountWalletId, List<StablecoinCurrency> acceptedCurrencies/*, PaymentDistributor distributor*/) /*throws Exception*/ {
        this.amount = amount;
        this.accountId = accountId;
        // Setting each of the accepted currency flags
        for (StablecoinCurrency currency : acceptedCurrencies) {
            switch (currency) {
                case USDC:
                    this.acceptUsdc = true;
                    break;
                case EURC:
                    this.acceptEurc = true;
                    break;
                case USDT:
                    this.acceptUsdt = true;
                    break;
                case SOL:
                    break;
                default:
                    throw new AssertionError();
            }
        }
        //this.currency = currency;

        this.chain = chain;
        this.walletDistribution = new ArrayList<>(Arrays.asList(accountWalletId));
        this.metadata = new HashMap<>();
        // this.distributor = distributor;
        // this.distributor = new PaymentDistributor(account, mappings, "");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Payment)) {
            return false;
        }
        Payment paymentObj = (Payment) o;
        return this.id == paymentObj.getId();

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }   

    /*
     * Distributes payments to multiple wallets according to the 
     */
    // public void distributePayment() {
    //     Map<AccountWallet, BigDecimal> mappings = this.distributor.getDistribution().getMappings();
    //     List<AccountWallet> keyList = new ArrayList<>(mappings.keySet());

    //     double remainingAmount = this.amount;

    //     for (int i = 0; i < keyList.size(); i++) {
    //         double transferAmount;

    //         if (i == keyList.size() - 1) {
    //             transferAmount = remainingAmount;
    //         } else {
    //             double percentage = mappings.get(keyList.get(i));
    //             transferAmount = (this.amount * Math.floor(percentage * 10000)) / 10000;
    //             remainingAmount -= transferAmount;
    //         }
    //         // Execute the transfer
    //         try {
    //             String address = keyList.get(i).getAddress();
    //             // transfer a quantity equivalent to transferAmount in tokens to the address
    //             System.out.printf("Transferring: %.2f tokens to %s\n", transferAmount, address);
    //         } catch (Exception e) {
    //             System.out.printf("Transfer failed");
    //         }
    //     }
    // }

    public BigDecimal getAmount() {
        return amount;
    }

    // public StablecoinCurrency getStablecoinCurrency() {
    //     return currency;
    // }

    // public void setCurrency(StablecoinCurrency currency) {
    //     this.currency = currency;
    // }

    // public PaymentDistributor getDistributor() {
    //     return distributor;
    // }

    // public void setDistributor(PaymentDistributor distributor) {
    //     this.distributor = distributor;
    // }

    // public void setDistributor(Map<AccountWallet, BigDecimal> mappings) throws Exception {
    //     this.distributor = new PaymentDistributor(this.account, mappings, "");
    // }

    public Blockchain getChain() {
        return chain;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public List<UUID> getWalletDistribution() {
        return walletDistribution;
    }

    public void setWalletDistribution(List<UUID> walletDistribution) {
        this.walletDistribution = walletDistribution;
    }

    public void addWallet(UUID walletId) {
        walletDistribution.add(walletId);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getAcceptUsdc() {
        return acceptUsdc;
    }

    public void setAcceptUsdc(Boolean acceptUsdc) {
        this.acceptUsdc = acceptUsdc;
    }

    public Boolean getAcceptEurc() {
        return acceptEurc;
    }

    public void setAcceptEurc(Boolean acceptEurc) {
        this.acceptEurc = acceptEurc;
    }

    public Boolean getAcceptUsdt() {
        return acceptUsdt;
    }

    public void setAcceptUsdt(Boolean acceptUsdt) {
        this.acceptUsdt = acceptUsdt;
    }

    public List<StablecoinCurrency> getAcceptedCurrencies() {
        List<StablecoinCurrency> currencies = List.of();
        if (this.getAcceptUsdc()) {
            currencies.add(StablecoinCurrency.USDC);
        }
        if (this.getAcceptEurc()) {
            currencies.add(StablecoinCurrency.EURC);
        }
        if (this.getAcceptUsdt()) {
            currencies.add(StablecoinCurrency.USDT);
        }

        return currencies;
        
    }

    public void setAcceptedCurrencies(List<StablecoinCurrency> currencies) {
        this.setAcceptUsdc(false);
        this.setAcceptEurc(false);
        this.setAcceptUsdt(false);
        for (StablecoinCurrency currency : currencies) {
            switch (currency) {
                case USDC -> this.setAcceptUsdc(true);
                case EURC -> this.setAcceptEurc(true);
                case USDT -> this.setAcceptUsdt(true);
                default -> throw new AssertionError();
            }
        }
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

}
