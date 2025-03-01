package com.diamond.diamond.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.wallets.FetchVendorWalletDto;
import com.diamond.diamond.dtos.wallets.NewVendorWalletDto;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.repositories.VendorWalletRepository;
import com.diamond.diamond.types.WalletStatus;

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

    public FetchVendorWalletDto saveWallet(NewVendorWalletDto walletDto, Vendor vendor) {
        VendorWallet vendorWallet = new VendorWallet(
                                        walletDto.getAddress(),
                                        walletDto.getWalletName(),
                                        vendor,
                                        walletDto.getChain());
        vendorWallet.setStatus(WalletStatus.ACTIVE);

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

    public List<FetchVendorWalletDto> findWalletDtosByVendor(Vendor vendor) {
        // List<VendorWallet> vendorWallets = vendorWalletRepository.findByVendor(vendor);
        //List<VendorWallet> vendorWallets = vendorWalletRepository.findAll();
        return vendorWalletRepository.findByVendor(vendor).stream() // Convert the List<VendorWallet> to a Stream<VendorWallet>
            .map(VendorWalletService::convertVendorWalletToFetchDto) // Map each VendorWallet to FetchVendorWalletDto
            .collect(Collectors.toList()); // Collect the results into a List<FetchVendorWalletDto>
    }

    public List<VendorWallet> findWalletsByVendor(Vendor vendor) {
        return vendorWalletRepository.findByVendor(vendor);
    }

    public List<VendorWallet> findWalletsByPayment(Payment payment) {
        return vendorWalletRepository.findByPayments(List.of(payment));
    }

    public List<FetchVendorWalletDto> findWalletDtosByPayment(Payment payment) {
        return vendorWalletRepository.findByPayments(List.of(payment)).stream() // Convert the List<VendorWallet> to a Stream<VendorWallet>
        .map(VendorWalletService::convertVendorWalletToFetchDto) // Map each VendorWallet to FetchVendorWalletDto
        .collect(Collectors.toList()); // Collect the results into a List<FetchVendorWalletDto>
    }

    public List<VendorWallet> findWalletsByPayments(List<Payment> payments) {
        return vendorWalletRepository.findByPayments(payments);
    }

    public List<FetchVendorWalletDto> findWalletDtosByPayments(List<Payment> payments) {
        return vendorWalletRepository.findByPayments(payments).stream() // Convert the List<VendorWallet> to a Stream<VendorWallet>
        .map(VendorWalletService::convertVendorWalletToFetchDto) // Map each VendorWallet to FetchVendorWalletDto
        .collect(Collectors.toList()); // Collect the results into a List<FetchVendorWalletDto>
    }

    public FetchVendorWalletDto updateWalletName(Long id, String name) {
        VendorWallet vendorWallet = vendorWalletRepository.findById(id).orElseThrow();
        vendorWallet.setName(name);
        return convertVendorWalletToFetchDto(vendorWalletRepository.save(vendorWallet));
    }

    public FetchVendorWalletDto archiveWallet(Long id) {
        VendorWallet vendorWallet = vendorWalletRepository.findById(id).orElseThrow();
        vendorWallet.setStatus(WalletStatus.ARCHIVED);
        return convertVendorWalletToFetchDto(vendorWalletRepository.save(vendorWallet));
    }

    public FetchVendorWalletDto reactivateWallet(Long id) {
        VendorWallet vendorWallet = vendorWalletRepository.findById(id).orElseThrow();
        vendorWallet.setStatus(WalletStatus.ACTIVE);
        return convertVendorWalletToFetchDto(vendorWalletRepository.save(vendorWallet));
    }

    public void deleteWalletById(Long id) {
        vendorWalletRepository.deleteById(id);
    }

    public void deleteWallet(VendorWallet wallet) {
        vendorWalletRepository.delete(wallet);
    }

}