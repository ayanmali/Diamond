package com.diamond.diamond.dtos.wallets;

import com.diamond.diamond.types.Blockchain;

public class NewVendorWalletDto {
    private String address;
    private Blockchain chain;
    private String walletName;

    // public UUID getVendorId() {
    //     return vendorId;
    // }
    // public void setVendorId(UUID vendorId) {
    //     this.vendorId = vendorId;
    // }
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
    public String getWalletName() {
        return walletName;
    }
    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

}
