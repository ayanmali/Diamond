package com.diamond.diamond.controllers.payments;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diamond.diamond.dtos.payments.fetch_payments.FetchPaymentDto;
import com.diamond.diamond.dtos.payments.new_payments.NewPaymentDto;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.services.VendorWalletService;
import com.diamond.diamond.services.payments.PaymentService;
import com.diamond.diamond.types.StablecoinCurrency;

@RequestMapping("/payments")
public abstract class PaymentController<P extends Payment> {
    protected PaymentService<P> paymentService; // the Service class for the given Payment type
    protected VendorWalletService vendorWalletService;
    // helper methods for converting to and from the Payment type and the appropriate DTOs
    //abstract FetchPaymentDto convertPaymentToFetchDto(Payment payment);
    abstract P convertNewDtoToPayment(NewPaymentDto paymentDto);

    @PostMapping("/new")
    public FetchPaymentDto createPayment(@RequestBody NewPaymentDto paymentDto) {
        return convertPaymentToFetchDto(paymentService.savePayment(convertNewDtoToPayment(paymentDto)));
    }

    @GetMapping("/id/{id}")
    public FetchPaymentDto getPaymentById(@PathVariable(value="id") String id) {
        return convertPaymentToFetchDto(paymentService.findPaymentById(id));
    }

    @PostMapping("/update-amount/{id}")
    public FetchPaymentDto updateAmount(@PathVariable(value="id") String id, @RequestBody Double amount) {
        return convertPaymentToFetchDto(paymentService.updateAmount(UUID.fromString(id), amount));
    }

    @PostMapping("/update-currency/{id}")
    public FetchPaymentDto updateCurrency(@PathVariable(value="id") String id, @RequestBody StablecoinCurrency currency) {
        return convertPaymentToFetchDto(paymentService.updateCurrency(UUID.fromString(id), currency));
    }

    @PostMapping("/update-wallet-distribution/{id}")
    public FetchPaymentDto updateWallets(@PathVariable(value="id") String id, @RequestBody List<Long> vendorWalletIds) {
        List<VendorWallet> vendorWallets = new ArrayList<>();
        for (Long walletId : vendorWalletIds) {
            vendorWallets.add(vendorWalletService.findWalletById(walletId));
        }
        return convertPaymentToFetchDto(paymentService.updateWalletDistribution(UUID.fromString(id), vendorWallets));
    }

    @PostMapping("/delete/{id}")
    public FetchPaymentDto deletePayment(@PathVariable(value="id") String id) {
        P payment = paymentService.findPaymentById(id);
        paymentService.deletePaymentById(id);
        return convertPaymentToFetchDto(payment);
    }


}

// @RestController
// @RequestMapping("/payments")
// public class PaymentController {
//     private final PaymentService<Payment> paymentService;
    
//     public PaymentController(PaymentService<Payment> paymentService) {
//         this.paymentService = paymentService;
//     }

//     @PostMapping("/new")
//     public String postMethodName(@RequestBody Map<String, String> payload) {
//         //TODO: process POST request
//         // Getting the type of payment to instantiate
//         String type = payload.get("type");

//         Payment payment;
//         // Determining the type of payment to instantiate
//         switch (type) {
//             case "invoice":
//                 break;
//             case "checkout":
//                 break;
//             case "link":
//                 break;

//             default:
//                 throw new AssertionError();
//         }

//         paymentService.newPayment(
//             new Payment()
//         );
        
//         return entity;
//     }

//     @GetMapping("/id")
//     public String getMethodName(@RequestParam String id) {
//         return new String();
//     }

// }