package com.diamond.diamond.controllers.payments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payments.fetch_payments.FetchPaymentDto;
import com.diamond.diamond.dtos.payments.new_payments.NewPaymentDto;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.entities.payments.link_payments.LinkPayment;
import com.diamond.diamond.services.VendorService;
import com.diamond.diamond.services.VendorWalletService;
import com.diamond.diamond.services.payments.linkpayments.LinkPaymentService;

@RestController
@RequestMapping("/links")
public class LinkPaymentController extends PaymentController<LinkPayment> {
    //private final LinkPaymentService linkPaymentService;
    private final VendorService vendorService;
    private final VendorWalletService vendorWalletService;

    public LinkPaymentController(LinkPaymentService linkPaymentService, VendorService vendorService, VendorWalletService vendorWalletService) {
        this.paymentService = linkPaymentService;
        this.vendorService = vendorService;
        this.vendorWalletService = vendorWalletService;
    }

    // @PostMapping("/new")
    // public LinkPayment createPayment(@RequestBody NewLinkPaymentDto paymentDto) {
    //     //TODO: process POST request
    //     LinkPayment payment = convertNew
    //     return linkPaymentService.savePayment(payment);
    // }

    @Override
    FetchPaymentDto convertPaymentToFetchDto(Payment payment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertPaymentToFetchDto'");
    }

    @Override
    LinkPayment convertNewDtoToPayment(NewPaymentDto paymentDto) {
        // TODO Auto-generated method stub
        List<VendorWallet> vendorWallets = new ArrayList<>();
        for (Long walletId : paymentDto.getVendorWalletIds()) {
            vendorWallets.add(vendorWalletService.findWalletById(walletId));
        }
        return new LinkPayment(paymentDto.getAmount(), 
                               vendorService.findVendorById(paymentDto.getVendorId()), 
                               paymentDto.getCurrency(), 
                               paymentDto.getChain(), 
                               vendorWallets);
    }

}
