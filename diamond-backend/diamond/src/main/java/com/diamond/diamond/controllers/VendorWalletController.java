package com.diamond.diamond.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.entities.VendorWallet;
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

    @GetMapping("/id/{id}")
    public String getWalletByWalletId(@PathVariable(value = "id") Long id) {
        return new String();
    }

    @GetMapping("/address/{address}")
    public String getWalletByAddress(@PathVariable(value = "address") String address) {
        return new String();
    }
    
    @PostMapping("/update-name")
    public VendorWallet updateWalletName(@PathVariable(value = "id") Long id, @RequestParam String newName) {
        return vendorWalletService.updateWalletName(id, newName);
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
