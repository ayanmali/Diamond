package com.diamond.diamond.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.wallets.FetchVendorWalletDto;
import com.diamond.diamond.dtos.wallets.NewVendorWalletDto;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.services.VendorService;
import com.diamond.diamond.services.VendorWalletService;

@RestController
@RequestMapping("/vwallets")
public class VendorWalletController {
    private final VendorWalletService vendorWalletService;
    private final VendorService vendorService;

    public VendorWalletController(VendorWalletService vendorWalletService, VendorService vendorService) {
        this.vendorWalletService = vendorWalletService;
        this.vendorService = vendorService;
    }

    @PostMapping("/new")
    public FetchVendorWalletDto createWallet(@RequestBody NewVendorWalletDto vendorWalletDto) {
        //TODO: process POST request
        Vendor vendor = vendorService.findVendorById(vendorWalletDto.getVendorId());
        return vendorWalletService.saveWallet(vendorWalletDto, vendor);
    }

    @GetMapping("/id/{id}")
    public FetchVendorWalletDto getWalletByWalletId(@PathVariable(value = "id") Long id) {
        return vendorWalletService.findWalletDtoById(id);
    }

    @GetMapping("/address/{address}")
    public FetchVendorWalletDto getWalletByAddress(@PathVariable(value = "address") String address) {
        return vendorWalletService.findWalletDtoByAddress(address);
    }
    
    @PostMapping("/update-name/{id}")
    public FetchVendorWalletDto updateWalletName(@RequestBody String name, @PathVariable(value = "id") Long id) {
        return vendorWalletService.updateWalletName(id, name);
    }

    @PostMapping("/archive/{id}")
    public FetchVendorWalletDto archiveWallet(@PathVariable(value="id") Long id) {
        //TODO: process POST request
        return vendorWalletService.archiveWallet(id);
    }

    @PostMapping("/activate")
    public FetchVendorWalletDto reactivateWallet(@PathVariable(value="id") Long id) {
        //TODO: process POST request
        return vendorWalletService.reactivateWallet(id);
    }
    
}
