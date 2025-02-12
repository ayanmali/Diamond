package com.diamond.diamond.dtos.wallets;

import java.util.UUID;

import com.diamond.diamond.types.Blockchain;

public class FetchCustomerWalletDto {
    private Long id;
    private String address;
    private Blockchain chain;
    private UUID customerId;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Blockchain getChain() {
        return chain;
    }
    public void setChain(Blockchain chain) {
        this.chain = chain;
    }
    public UUID getCustomerId() {
        return customerId;
    }
    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
