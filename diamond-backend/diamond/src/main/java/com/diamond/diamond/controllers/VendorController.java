package com.diamond.diamond.controllers;

import java.util.List;
import java.util.Map;
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
import com.diamond.diamond.services.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/signup")
    FetchVendorDto signup(@RequestBody RegisterUserDto registerUserDto) {
        return vendorService.signUp(registerUserDto);
    }
    
    @GetMapping("/id/{id}")
    FetchVendorDto getVendorById(@PathVariable(value = "id") String id) {
        //return "wfqwgqg";
        return vendorService.findVendorDtoById(id);
        //return String.format("Testing get endpoint for id %s", id);
    }

    @GetMapping("/email/{email}")
    FetchVendorDto getVendorByEmail(@PathVariable(value="email") String email) {
        return vendorService.findVendorDtoByEmail(email);
    }

    @GetMapping("wallets/{id}")
    List<FetchVendorWalletDto> getWallets(@PathVariable(value="id") String id) {
        return vendorService.findVendorWallets(UUID.fromString(id));
    }
    
    @PostMapping("update-email/{id}")
    FetchVendorDto updateEmail(@RequestBody String email, @PathVariable(value="id") String id) {
        //TODO: process POST request
        
        return vendorService.updateVendorEmail(UUID.fromString(id), email);
    }
    
    @PostMapping("/update-name/{id}")
    FetchVendorDto updateBusinessName(@RequestBody String name, @PathVariable(value="id") String id) {
        //TODO: process POST request
        return vendorService.updateVendorName(UUID.fromString(id), name);
    }

    @PostMapping("/delete/{id}")
    Map<String, String> deleteVendor(@PathVariable(value="id") String id) {
        //TODO: process POST request
        
        vendorService.deleteVendorById(id);
        return Map.of("id", id);
        
    }
    
    // @GetMapping("/email")
    // public String getMethodName(@RequestParam String param) {
    //     return new String();
    // }
    
    
    // public ResponseEntity<Vendor> getVendorById() {

    // }
}
