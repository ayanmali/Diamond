package com.diamond.diamond.controllers.payments.payments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payments.new_payments.NewLinkPaymentDto;
import com.diamond.diamond.entities.AccountWallet;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.entities.payments.link_payments.LinkPayment;
import com.diamond.diamond.services.AccountService;
import com.diamond.diamond.services.AccountWalletService;
import com.diamond.diamond.services.payments.PromoCodeService;
import com.diamond.diamond.services.payments.linkpayments.LinkPaymentService;

@RestController
@RequestMapping("/link-payments")
public class LinkPaymentController extends PaymentController<LinkPayment, NewLinkPaymentDto> {
    private final PromoCodeService promoCodeService;

    public LinkPaymentController(LinkPaymentService linkPaymentService, AccountService accountService, AccountWalletService accountWalletService, PromoCodeService promoCodeService) {
        this.paymentService = linkPaymentService;
        this.accountService = accountService;
        this.accountWalletService = accountWalletService;
        this.promoCodeService = promoCodeService;
    }

    @Override
    LinkPayment convertNewDtoToPayment(NewLinkPaymentDto paymentDto) {
        List<AccountWallet> accountWallets = this.getAccountWalletsFromPaymentDto(paymentDto);

        List<PromoCode> validPromoCodes = new ArrayList<>();
        for (Long promoCodeId : paymentDto.getValidPromoCodeIds()) {
            validPromoCodes.add(promoCodeService.findPromoCodeById(promoCodeId));
        }
    
        return new LinkPayment(paymentDto.getAmount(), 
                                   accountService.findAccountById(paymentDto.getAccountId()), 
                                   paymentDto.getCurrency(), 
                                   paymentDto.getChain(), 
                                   accountWallets, 
                                   paymentDto.getHasMaxNumberOfPayments(), 
                                   paymentDto.getMaxNumberOfPayments(), 
                                   paymentDto.getEnablePromoCodes(), 
                                   validPromoCodes);
    }

}
