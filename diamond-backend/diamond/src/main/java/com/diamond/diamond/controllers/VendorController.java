package com.diamond.diamond.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.vendor.FetchVendorDto;
import com.diamond.diamond.dtos.vendor.RegisterUserDto;
import com.diamond.diamond.dtos.wallets.FetchVendorWalletDto;
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.services.VendorService;
import com.diamond.diamond.services.VendorWalletService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    private final VendorService vendorService;
    private final VendorWalletService vendorWalletService;

    public VendorController(VendorService vendorService, VendorWalletService vendorWalletService) {
        this.vendorService = vendorService;
        this.vendorWalletService = vendorWalletService;
    }

    private FetchVendorDto loadVendorWallets(FetchVendorDto vendorDto) {
        Vendor vendor = vendorService.findVendorById(vendorDto.getId());
        List<FetchVendorWalletDto> wallets = vendorWalletService.findWalletDtosByVendor(
                                                                vendor);
        vendorDto.setWallets(wallets);
        return vendorDto;
        
    }

    @PostMapping("/signup")
    FetchVendorDto signup(@RequestBody RegisterUserDto registerUserDto) {
        return vendorService.signUp(registerUserDto);
    }
    
    @GetMapping("/id/{id}")
    FetchVendorDto getVendorById(@PathVariable(value = "id") String id) {
        FetchVendorDto vendorDto = vendorService.findVendorDtoById(id);

        vendorDto = loadVendorWallets(vendorDto);

        return vendorDto;
    }

    @GetMapping("/email/{email}")
    FetchVendorDto getVendorByEmail(@PathVariable(value="email") String email) {
        // getting the vendor dto
        FetchVendorDto vendorDto = vendorService.findVendorDtoByEmail(email);
        
        vendorDto = loadVendorWallets(vendorDto);

        return vendorDto;
    }

    // @GetMapping("wallets/{id}")
    // List<FetchVendorWalletDto> getWallets(@PathVariable(value="id") String id) {
    //     return vendorService.findVendorWallets(UUID.fromString(id));
    // }
    
    @PostMapping("update-email/{id}")
    FetchVendorDto updateEmail(@RequestBody String email, @PathVariable(value="id") String id) {
        //TODO: process POST request
        FetchVendorDto vendorDto = vendorService.updateVendorEmail(UUID.fromString(id), email);
        vendorDto = loadVendorWallets(vendorDto);
        return vendorDto;
    }
    
    @PostMapping("/update-name/{id}")
    FetchVendorDto updateBusinessName(@RequestBody String name, @PathVariable(value="id") String id) {
        //TODO: process POST request
        FetchVendorDto vendorDto = vendorService.updateVendorName(UUID.fromString(id), name);
        vendorDto = loadVendorWallets(vendorDto);
        return vendorDto;
    }

    @PostMapping("/delete/{id}")
    FetchVendorDto deleteVendor(@PathVariable(value="id") String id) {
        //TODO: process POST request
        FetchVendorDto vendorDto = vendorService.findVendorDtoById(id);
        vendorDto = loadVendorWallets(vendorDto);
        vendorService.deleteVendorById(id);
        return vendorDto;
        
    }
    
    // @GetMapping("/email")
    // public String getMethodName(@RequestParam String param) {
    //     return new String();
    // }
    
    
    // public ResponseEntity<Vendor> getVendorById() {

    // }
}
