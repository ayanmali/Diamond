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
import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.services.VendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/signup")
    Vendor signup(@RequestBody RegisterUserDto registerUserDto) {
        return vendorService.signUp(registerUserDto);
    }
    
    @GetMapping("/id/{id}")
    FetchVendorDto getVendorById(@PathVariable(value = "id") String id) {
        //return "wfqwgqg";
        return vendorService.findVendorDtoById(id);
        //return String.format("Testing get endpoint for id %s", id);
    }

    @GetMapping("/email")
    FetchVendorDto getVendorByEmail(@RequestBody Map<String, String> payload) {
        return vendorService.findVendorDtoByEmail(payload.get("email"));
    }

    @GetMapping("/wallets")
    List<VendorWallet> getWallets(@RequestBody Map<String, String> payload) {
        return vendorService.findVendorWallets(UUID.fromString(payload.get("id")));
    }
    
    @PostMapping("/update-email")
    Vendor updateEmail(@RequestBody Map<String, String> payload) {
        //TODO: process POST request
        
        return vendorService.updateVendorEmail(UUID.fromString(payload.get("id")), payload.get("email"));
    }

    @PostMapping("/update-name")
    Vendor updateBusinessName(@RequestBody Map<String, String> payload) {
        //TODO: process POST request
        
        return vendorService.updateVendorName(UUID.fromString(payload.get("id")), payload.get("name"));
    }

    @PostMapping("/delete")
    Map<String, String> deleteVendor(@RequestBody Map<String, String> payload) {
        //TODO: process POST request
        vendorService.deleteVendorById(UUID.fromString(payload.get("id")));
        return Map.of("id", payload.get("id"));
        
    }
    
    
    // @GetMapping("/email")
    // public String getMethodName(@RequestParam String param) {
    //     return new String();
    // }
    
    
    // public ResponseEntity<Vendor> getVendorById() {

    // }
}
