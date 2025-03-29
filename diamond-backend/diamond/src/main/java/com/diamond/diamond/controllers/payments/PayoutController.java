package com.diamond.diamond.controllers.payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payouts.FetchPayoutDto;
import com.diamond.diamond.dtos.payouts.NewPayoutDto;
import com.diamond.diamond.services.payments.PayoutService;
import com.diamond.diamond.services.user.AccountService;
import com.diamond.diamond.services.user.AccountWalletService;
import com.diamond.diamond.types.PayoutStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payouts")
public class PayoutController {
    private final PayoutService payoutService;
    private final AccountService accountService;
    private final AccountWalletService accountWalletService;

    public PayoutController(PayoutService payoutService, AccountService accountService, AccountWalletService accountWalletService) {
        this.payoutService = payoutService;
        this.accountService = accountService;
        this.accountWalletService = accountWalletService;
    }
    
    @PostMapping("/new")
    public FetchPayoutDto createPayout(@Valid @RequestBody NewPayoutDto payoutDto) {
        return payoutService.createPayout(
            payoutDto,
            accountService.findAccountById(payoutDto.getAccountId()),
            accountWalletService.findWalletById(payoutDto.getWalletId()));
    }

    @GetMapping("/payouts")
    public List<FetchPayoutDto> getPayoutDtos(
        @RequestBody(required=false) UUID id,
        @RequestBody(required=false) UUID accountId,
        @RequestBody(required=false) UUID walletId,
        @RequestBody(required=false) BigDecimal amountLessThan,
        @RequestBody(required=false) BigDecimal amountGreaterThan,
        @RequestBody(required=false) PayoutStatus status,
        @RequestBody(required=false) Date createdBefore,
        @RequestBody(required=false) Date createdAfter,
        @RequestBody(required=false) Date paidBefore,
        @RequestBody(required=false) Date paidAfter,
        @RequestBody(required=false) Integer pageSize
    ) {
        return payoutService.findPayoutDtosWithFilters(id, accountId, walletId, amountLessThan, amountGreaterThan, status, createdBefore, createdAfter, paidBefore, paidAfter, pageSize);
    }

    // TODO: add SSEs
    
}
