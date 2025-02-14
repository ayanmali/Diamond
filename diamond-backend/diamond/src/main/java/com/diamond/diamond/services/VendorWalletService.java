package com.diamond.diamond.services;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.wallets.FetchVendorWalletDto;
import com.diamond.diamond.dtos.wallets.NewVendorWalletDto;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.repositories.VendorWalletRepository;

@Service
public class VendorWalletService {
    private final VendorWalletRepository vendorWalletRepository;

    public VendorWalletService(VendorWalletRepository vendorWalletRepository) {
        this.vendorWalletRepository = vendorWalletRepository;
    }

    public static FetchVendorWalletDto convertVendorWalletToFetchDto(VendorWallet vendorWallet) {
        FetchVendorWalletDto walletDto = new FetchVendorWalletDto();
        walletDto.setAddress(vendorWallet.getAddress());
        walletDto.setChain(vendorWallet.getChain());
        walletDto.setCreatedAt(vendorWallet.getCreatedAt());
        walletDto.setId(vendorWallet.getId());
        walletDto.setStatus(vendorWallet.getStatus());
        walletDto.setUpdatedAt(vendorWallet.getUpdatedAt());
        walletDto.setVendorId(vendorWallet.getVendor().getId());
        walletDto.setWalletName(vendorWallet.getWalletName());
        return walletDto;
    }

    public FetchVendorWalletDto saveWallet(NewVendorWalletDto wallet, Vendor vendor) {
        VendorWallet vendorWallet = new VendorWallet(
                                        wallet.getAddress(),
                                        wallet.getWalletName(),
                                        vendor,
                                        wallet.getChain());
        return convertVendorWalletToFetchDto(vendorWalletRepository.save(vendorWallet));
    }

    public FetchVendorWalletDto findWalletDtoById(Long id) {
        return convertVendorWalletToFetchDto(vendorWalletRepository.findById(id).orElseThrow());
    }

    public VendorWallet findWalletById(Long id) {
        return vendorWalletRepository.findById(id).orElseThrow();
    }

    public FetchVendorWalletDto findWalletDtoByAddress(String address) {
        return convertVendorWalletToFetchDto(vendorWalletRepository.findByAddress(address).orElseThrow());
    }

    public VendorWallet findWalletByAddress(String address) {
        return vendorWalletRepository.findByAddress(address).orElseThrow();
    }

    public FetchVendorWalletDto updateWalletName(Long id, String name) {
        VendorWallet vendorWallet = vendorWalletRepository.findById(id).orElseThrow();
        vendorWallet.setName(name);
        return convertVendorWalletToFetchDto(vendorWalletRepository.save(vendorWallet));
    }

    public void deleteWalletById(Long id) {
        vendorWalletRepository.deleteById(id);
    }

    public void deleteWallet(VendorWallet wallet) {
        vendorWalletRepository.delete(wallet);
    }

}