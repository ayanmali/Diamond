package com.diamond.diamond.entities.payments;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.PaymentStatus;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

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
    private Double amount;
    //final VendorWallet businessWallet;

    @ManyToOne
    @JoinColumn(name="vendor_id", referencedColumnName="id", nullable=false)
    private Vendor vendor;

    // remove
    // @ManyToOne
    // @JoinColumn(name="customer_id", nullable=false)
    // private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private StablecoinCurrency currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Blockchain chain;

    // remove
    // @Enumerated(EnumType.STRING)
    // @Column(nullable=false)
    // private PaymentStatus status;

    // used to define how payments are allocated between the vendor's wallets, if desired
    //private PaymentDistributor distributor;
    @ManyToMany
    @JoinTable(
        name = "payment_wallet_distribution", // Optional: Custom name for the join table
        joinColumns = @JoinColumn(name = "payment_id"),
        inverseJoinColumns = @JoinColumn(name = "wallet_id")
    )
    private List<VendorWallet> walletDistribution;

    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

    // /*
    //  * Constructor method that uses a single wallet for routing payments by default
    //  */
    // public Payment(double amount, Vendor vendor, Customer customer, StablecoinCurrency currency, Blockchain chain) throws Exception {
    //     this.amount = amount;
    //     this.vendor = vendor;
    //     this.customer = customer;
    //     this.currency = currency;
    //     this.chain = chain;
    //     this.paymentStatus = PaymentStatus.PENDING;

    //     // retrieves the vendor's first wallet on the speciied chain
    //     VendorWallet wallet = this.vendor.getWallets(chain).get(0);
    //     // Allocates all incoming payments to the vendor's primary wallet by default
    //     this.distributor = new PaymentDistributor(vendor,
    //             Map.of(wallet, 1.0),
    //             "");
    // }

    public Payment() {}
    /*
     * Constructor method that uses a provided Map to route payments to multiple wallets
     */
    public Payment(Double amount, Vendor vendor, StablecoinCurrency currency, Blockchain chain, List<VendorWallet> vendorWallets/*, PaymentDistributor distributor*/) /*throws Exception*/ {
        this.amount = amount;
        this.vendor = vendor;
        this.currency = currency;
        this.chain = chain;
        this.walletDistribution = vendorWallets;
        // this.distributor = distributor;
        // this.distributor = new PaymentDistributor(vendor, mappings, "");
    }

    public Payment(Double amount, Vendor vendor, StablecoinCurrency currency, Blockchain chain, VendorWallet vendorWallet/*, PaymentDistributor distributor*/) /*throws Exception*/ {
        this.amount = amount;
        this.vendor = vendor;
        this.currency = currency;
        this.chain = chain;
        this.walletDistribution = new ArrayList<>(Arrays.asList(vendorWallet));
        // this.distributor = distributor;
        // this.distributor = new PaymentDistributor(vendor, mappings, "");
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
    //     Map<VendorWallet, Double> mappings = this.distributor.getDistribution().getMappings();
    //     List<VendorWallet> keyList = new ArrayList<>(mappings.keySet());

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

    // public abstract void pay();

    // todo
    public PaymentStatus validatePayment() {return null;}

    public Double getAmount() {
        return amount;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public StablecoinCurrency getStablecoinCurrency() {
        return currency;
    }

    public void setCurrency(StablecoinCurrency currency) {
        this.currency = currency;
    }

    // public PaymentDistributor getDistributor() {
    //     return distributor;
    // }

    // public void setDistributor(PaymentDistributor distributor) {
    //     this.distributor = distributor;
    // }

    // public void setDistributor(Map<VendorWallet, Double> mappings) throws Exception {
    //     this.distributor = new PaymentDistributor(this.vendor, mappings, "");
    // }

    public Blockchain getChain() {
        return chain;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public List<VendorWallet> getWalletDistribution() {
        return walletDistribution;
    }

    public void setWalletDistribution(List<VendorWallet> walletDistribution) {
        this.walletDistribution = walletDistribution;
    }

    public void addWallet(VendorWallet wallet) {
        walletDistribution.add(wallet);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
