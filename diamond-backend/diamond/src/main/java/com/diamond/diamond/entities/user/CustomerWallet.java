package com.diamond.diamond.entities.user;

import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.Wallet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="customer_wallets")
public class CustomerWallet implements Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @Column(unique=true, nullable=false, updatable=false)
    @Pattern(regexp="^(0x[a-fA-F0-9]{40}|[1-9A-HJ-NP-Za-km-z]{32,44})$")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, updatable=false)
    private Blockchain chain;
    
    //@ManyToOne
    //@JoinColumn(name="customer_id", referencedColumnName="id")
    //private Customer customer;
    @Column(name="customer_id", nullable=false)
    private UUID customerId;

    // @Column
    // private final String email;
    //private int priority;
    // private double usdcBalance;
    // private double eurcBalance;
    // private double solBalance;
    // private double baseEthBalance;

    /* Constructor method */
    public CustomerWallet() {}

    public CustomerWallet(String address, Blockchain chain, UUID customerId /*,String email*/) {
        this.address = address;
        this.chain = chain;
        this.customerId = customerId;
        //this.email = email;

        // this.usdcBalance = 0;
        // this.eurcBalance = 0;
        // this.solBalance = 0;
        // this.baseEthBalance = 0;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public Blockchain getChain() {
        return chain;
    }

    // public String getEmail() {
    //     return email;
    // }

    // public int getWalletPriority() {
    //     return priority;
    // }

    // public void setWalletPriority(int priority) {
    //     this.priority = priority;
    // }

    // public double getUsdcBalance() {
    //     return usdcBalance;
    // }
    // public double getEurcBalance() {
    //     return eurcBalance;
    // }
    // public double getSolBalance() {
    //     return solBalance;
    // }
    // public double getBaseEthBalance() {
    //     return baseEthBalance;
    // }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
