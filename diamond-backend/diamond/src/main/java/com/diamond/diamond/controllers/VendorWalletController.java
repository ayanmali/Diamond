package com.diamond.diamond.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.services.VendorWalletService;

@RestController
@RequestMapping("/vwallets")
public class VendorWalletController {
    private final VendorWalletService vendorWalletService;

    public VendorWalletController(VendorWalletService vendorWalletService) {
        this.vendorWalletService = vendorWalletService;
    }

    @PostMapping("/new")
    public String createNewWallet(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @GetMapping("/id")
    public String getWalletByWalletId(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/address")
    public String getWalletByAddress(@RequestParam String address) {
        return new String();
    }
    
    @PostMapping("/update-name")
    public String updateWalletName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PostMapping("/archive")
    public String archiveWallet(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PostMapping("/activate")
    public String reactivateWallet(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
}
