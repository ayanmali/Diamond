package com.diamond.diamond.controllers.payments.payments;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.diamond.diamond.dtos.payments.fetch_payments.FetchPaymentDto;
import com.diamond.diamond.dtos.payments.new_payments.NewPaymentDto;
import com.diamond.diamond.dtos.wallets.FetchAccountWalletDto;
import com.diamond.diamond.entities.AccountWallet;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.services.AccountService;
import com.diamond.diamond.services.AccountWalletService;
import com.diamond.diamond.services.payments.PaymentService;
import com.diamond.diamond.types.StablecoinCurrency;

//@RequestMapping("/payments")
public abstract class PaymentController<P extends Payment, N extends NewPaymentDto> {
    protected PaymentService<P> paymentService; // the Service class for the given Payment type
    protected AccountService accountService;
    protected AccountWalletService accountWalletService;
    abstract P convertNewDtoToPayment(N paymentDto); // helper method for converting to and from the Payment type and the NewPaymentDTO

    // Used in child classes
    protected List<AccountWallet> getAccountWalletsFromPaymentDto(N paymentDto) {
        List<AccountWallet> accountWallets = new ArrayList<>();
        for (Long walletId : paymentDto.getAccountWalletIds()) {
            accountWallets.add(accountWalletService.findWalletById(walletId));
        }
        return accountWallets;
    }

    private FetchPaymentDto loadAccountWallets(FetchPaymentDto paymentDto) {
        // Retrieving the wallet distribution for this Payment
        List<FetchAccountWalletDto> walletDistribution = accountWalletService.findWalletsByPayment(paymentService.findPaymentById(paymentDto.getId())).stream() // Convert the List<Customer> to a Stream<Customer>
        .map(AccountWalletService::convertAccountWalletToFetchDto) // Map each Customer to FetchCustomerDto
        .collect(Collectors.toList());
        
        paymentDto.setAccountWalletDtos(walletDistribution);
        return paymentDto;
    }
    
    @PostMapping("/new")
    public FetchPaymentDto createPayment(@RequestBody N paymentDto) {
        return paymentService.convertPaymentToFetchDto(paymentService.savePayment(convertNewDtoToPayment(paymentDto)));
    }

    @GetMapping("/id/{id}")
    public FetchPaymentDto getPaymentById(@PathVariable(value="id") String id) {
        //P payment = paymentService.findPaymentById(id);
        FetchPaymentDto paymentDto = paymentService.findPaymentDtoById(id);
        paymentDto = loadAccountWallets(paymentDto);
        return paymentDto;
    }

    @PostMapping("/update-amount/{id}")
    public FetchPaymentDto updateAmount(@PathVariable(value="id") String id, @RequestParam Double amount) {
        FetchPaymentDto paymentDto = paymentService.convertPaymentToFetchDto(paymentService.updateAmount(UUID.fromString(id), amount));
        paymentDto = loadAccountWallets(paymentDto);
        return paymentDto;
    }

    @PostMapping("/update-currency/{id}")
    public FetchPaymentDto updateCurrency(@PathVariable(value="id") String id, @RequestBody StablecoinCurrency currency) {
        FetchPaymentDto paymentDto = paymentService.convertPaymentToFetchDto(paymentService.updateCurrency(UUID.fromString(id), currency));
        paymentDto = loadAccountWallets(paymentDto);
        return paymentDto;
    }

    // TODO: Test this endpoint
    @PostMapping("/update-wallet-distribution/{id}")
    public FetchPaymentDto updateWallets(@PathVariable(value="id") String id, @RequestBody List<Long> accountWalletIds) {
        List<AccountWallet> accountWallets = new ArrayList<>();
        for (Long walletId : accountWalletIds) {
            accountWallets.add(accountWalletService.findWalletById(walletId));
        }
        FetchPaymentDto paymentDto = paymentService.convertPaymentToFetchDto(paymentService.updateWalletDistribution(UUID.fromString(id), accountWallets));
        //paymentDto = loadAccountWallets(paymentDto);
        return paymentDto;
    }

    @PostMapping("/delete/{id}")
    public FetchPaymentDto deletePayment(@PathVariable(value="id") String id) {
        //P payment = paymentService.findPaymentById(id);
        FetchPaymentDto paymentDto = paymentService.findPaymentDtoById(id);
        paymentDto = loadAccountWallets(paymentDto);
        paymentService.deletePaymentById(id);
        return paymentDto;
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