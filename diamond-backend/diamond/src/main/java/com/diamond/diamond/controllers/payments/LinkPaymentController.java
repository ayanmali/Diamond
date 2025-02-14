package com.diamond.diamond.controllers.payments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payments.LinkPaymentRequestDto;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.link_payments.LinkPayment;
import com.diamond.diamond.services.CustomerService;
import com.diamond.diamond.services.VendorService;
import com.diamond.diamond.services.VendorWalletService;
import com.diamond.diamond.services.payments.linkpayments.LinkPaymentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/links")
public class LinkPaymentController {
    private final LinkPaymentService linkPaymentService;
    private final VendorService vendorService;
    private final CustomerService customerService;
    private final VendorWalletService vendorWalletService;

    public LinkPaymentController(LinkPaymentService linkPaymentService, VendorService vendorService, CustomerService customerService, VendorWalletService vendorWalletService) {
        this.linkPaymentService = linkPaymentService;
        this.vendorService = vendorService;
        this.customerService = customerService;
        this.vendorWalletService = vendorWalletService;
    }

    @PostMapping("/new")
    public LinkPayment createPayment(@RequestBody LinkPaymentRequestDto paymentReq) {
        //TODO: process POST request
        // Initializing superclass values
        List<VendorWallet> vendorWallets = new ArrayList<>();
        for (Long walletId : paymentReq.getVendorWalletIds()) {
            vendorWallets.add(vendorWalletService.findWalletById(walletId).orElseThrow());
        }

        LinkPayment payment = new LinkPayment(
            paymentReq.getAmount(),
            vendorService.findVendorById(paymentReq.getVendorId()),
            customerService.findCustomerById(paymentReq.getCustomerId()),
            paymentReq.getCurrency(),
            paymentReq.getChain(),
            vendorWallets
        );

        return payment;
    }

    @GetMapping("/id/{}")
    public String getPaymentById(@RequestParam String param) {
        return new String();
    }

    
    
    
}
