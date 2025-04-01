package com.diamond.diamond.entities.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "accounts")
// Implementing the UserDetails interface to use user data for authentication
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    // The name of the user
    @Column(nullable=false)
    @Size(min=1, max=50)
    private String name;

    // The name of the organization/business the user is apart of
    @Column(nullable = false, name="business_name")
    @Size(min=1, max=50)
    private String businessName;

    @Column(nullable=false, name="pin")
    @Size(min=4, max=4)
    private String encryptedPin;

    // The wallets belonging to this user
    @OneToMany(mappedBy="account", cascade=CascadeType.ALL)
    private List<AccountWallet> wallets;

    // The email address belonging to this user
    @Column(unique = true, length = 100, nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy="account", cascade=CascadeType.ALL)
    private List<Customer> customers;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Metadata for Circle programmable wallets
    // @Column(name="wallet_set_id", unique=true, nullable=false)
    // private UUID walletSetId;

    public Account() {this.wallets = new ArrayList<>();}

    public Account(String businessName, String email) throws Exception {
        this.businessName = businessName;
        this.email = email;
        this.wallets = new ArrayList<>();
        this.createdAt = new Date();
    }

    /* Implementing the interface */
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     return List.of();
    // }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setBusinessName(String newBusinessName) {
        this.businessName = newBusinessName;
    }

    public List<AccountWallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<AccountWallet> wallets) {
        this.wallets = wallets;
    }

    public void addWallet(AccountWallet wallet) {
        wallets.add(wallet);
    }

    public void removeWallet(AccountWallet wallet) throws Exception {
        if (wallets.contains(wallet)) {
            wallets.remove(wallet);
        } else {
            throw new Exception(String.format("Wallet with address %s not found.", wallet.getAddress()));
        }
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    // public UUID getWalletSetId() {
    //     return walletSetId;
    // }

    // public void setWalletSetId(UUID walletSetId) {
    //     this.walletSetId = walletSetId;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}