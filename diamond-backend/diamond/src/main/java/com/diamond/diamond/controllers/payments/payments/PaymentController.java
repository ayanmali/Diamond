package com.diamond.diamond.controllers.payments.payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.diamond.diamond.dtos.payments.fetch_payments.FetchPaymentDto;
import com.diamond.diamond.dtos.payments.new_payments.NewPaymentDto;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.services.payments.PaymentService;
import com.diamond.diamond.services.user.AccountService;
import com.diamond.diamond.services.user.AccountWalletService;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.validation.Valid;

//@RequestMapping("/payments")
public abstract class PaymentController<P extends Payment, N extends NewPaymentDto> {
    protected PaymentService<P> paymentService; // the Service class for the given Payment type
    protected AccountService accountService;
    protected AccountWalletService accountWalletService;
    abstract P convertNewDtoToPayment(N paymentDto); // helper method for converting to and from the Payment type and the NewPaymentDTO
    
    public PaymentController(PaymentService<P> paymentService, AccountService accountService, AccountWalletService accountWalletService) {
        this.paymentService = paymentService;
        this.accountService = accountService;
        this.accountWalletService = accountWalletService;
    }
    // Used in child classes
    // protected List<AccountWallet> getAccountWalletsFromPaymentDto(N paymentDto) {
    //     List<AccountWallet> accountWallets = new ArrayList<>();
    //     for (UUID walletId : paymentDto.getAccountWalletIds()) {
    //         accountWallets.add(accountWalletService.findWalletById(walletId));
    //     }
    //     return accountWallets;
    // }

    // private FetchPaymentDto loadAccountWallets(FetchPaymentDto paymentDto) {
    //     // Retrieving the wallet distribution for this Payment
    //     List<FetchAccountWalletDto> walletDistribution = accountWalletService.findWalletsByPayment(paymentService.findPaymentById(paymentDto.getId())).stream() // Convert the List<Customer> to a Stream<Customer>
    //     .map(AccountWalletService::convertAccountWalletToFetchDto) // Map each Customer to FetchCustomerDto
    //     .collect(Collectors.toList());
        
    //     paymentDto.setAccountWalletDtos(walletDistribution);
    //     return paymentDto;
    // }
    
    @PostMapping("/new")
    public FetchPaymentDto createPayment(@Valid @RequestBody N paymentDto) {
        return paymentService.convertPaymentToFetchDto(
            paymentService.savePayment(convertNewDtoToPayment(paymentDto))
        );
    }

    // TODO: filter for payments by wallets
    @GetMapping("/{id}/payments")
    public List<FetchPaymentDto> getPayments(@RequestBody(required=false) UUID id,
                                             @PathVariable(value="id")    UUID accountId,
                                             @RequestBody(required=false) Blockchain chain,
                                             @RequestBody(required=false) BigDecimal amountGreaterThan,
                                             @RequestBody(required=false) BigDecimal amountLessThan,
                                             @RequestBody(required=false) Date createdBefore,
                                             @RequestBody(required=false) Date createdAfter,
                                             @RequestBody(required=false) Integer pageSize) {
        return paymentService.findPaymentDtosWithFilters(id, accountId, chain, amountGreaterThan, amountLessThan, createdBefore, createdAfter, pageSize);
    }

    @GetMapping("/paymentid/{id}")
    public FetchPaymentDto getPaymentById(@PathVariable(value="id") UUID id) {
        //P payment = paymentService.findPaymentById(id);
        return paymentService.findPaymentDtoById(id);
        // paymentDto = loadAccountWallets(paymentDto);
        // return paymentDto;
    }

    @PatchMapping("/paymentid/{id}/update-amount")
    public FetchPaymentDto updateAmount(@PathVariable(value="id") UUID id, @RequestParam BigDecimal amount) {
        return paymentService.convertPaymentToFetchDto(
            paymentService.updateAmount(id, amount)
        );
        // paymentDto = loadAccountWallets(paymentDto);
        // return paymentDto;
    }

    @PatchMapping("/paymentid/{id}/update-currency")
    public FetchPaymentDto updateCurrency(@PathVariable(value="id") UUID id, @RequestBody List<StablecoinCurrency> currencies) {
        return paymentService.convertPaymentToFetchDto(
            paymentService.updateCurrency(id, currencies)
        );
        // paymentDto = loadAccountWallets(paymentDto);
        // return paymentDto;
    }

    // TODO: Test this endpoint
    @PatchMapping("/paymentid/{id}/update-wallet-distribution")
    public FetchPaymentDto updateWallets(@PathVariable(value="id") UUID id, @RequestBody List<UUID> accountWalletIds) {
        // List<AccountWallet> accountWallets = new ArrayList<>();
        // for (UUID walletId : accountWalletIds) {
        //     accountWallets.add(accountWalletService.findWalletById(walletId));
        // }
        // FetchPaymentDto paymentDto = paymentService.convertPaymentToFetchDto(paymentService.updateWalletDistribution(id, accountWallets));
        //paymentDto = loadAccountWallets(paymentDto);
        // return paymentDto;
        return paymentService.convertPaymentToFetchDto(
            paymentService.updateWalletDistribution(id, accountWalletIds)
        );
    }

    @DeleteMapping("/paymentid/{id}/delete")
    public FetchPaymentDto deletePayment(@PathVariable(value="id") UUID id) {
        //P payment = paymentService.findPaymentById(id);
        FetchPaymentDto paymentDto = paymentService.findPaymentDtoById(id);
        // paymentDto = loadAccountWallets(paymentDto);
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