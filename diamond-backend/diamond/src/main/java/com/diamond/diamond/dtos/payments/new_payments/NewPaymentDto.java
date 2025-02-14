/*
 * To be used when requesting to create a new Payment
 */
package com.diamond.diamond.dtos.payments.new_payments;

import java.util.List;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

public abstract class NewPaymentDto {

    private UUID id;
    private Double amount;
    private String vendorId;
    private StablecoinCurrency currency;
    private Blockchain chain;
    private List<Long> vendorWalletIds;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getVendorId() {
        return vendorId;
    }
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
    public StablecoinCurrency getCurrency() {
        return currency;
    }
    public void setCurrency(StablecoinCurrency currency) {
        this.currency = currency;
    }
    public Blockchain getChain() {
        return chain;
    }
    public void setChain(Blockchain chain) {
        this.chain = chain;
    }
    public List<Long> getVendorWalletIds() {
        return vendorWalletIds;
    }
    public void setVendorWalletIds(List<Long> vendorWalletIds) {
        this.vendorWalletIds = vendorWalletIds;
    }

}
