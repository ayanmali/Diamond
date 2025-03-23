package com.diamond.diamond.dtos.wallets;

import java.util.UUID;

import com.diamond.diamond.types.Blockchain;

import jakarta.validation.constraints.Pattern;

public class NewCustomerWalletDto {
    @Pattern(regexp="^(0x[a-fA-F0-9]{40}|[1-9A-HJ-NP-Za-km-z]{32,44})$")
    private String address;
    private Blockchain chain;
    
    private UUID customerId;
    
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
