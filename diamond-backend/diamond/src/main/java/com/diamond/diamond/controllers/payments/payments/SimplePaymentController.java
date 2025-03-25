package com.diamond.diamond.controllers.payments.payments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payments.new_payments.NewSimplePaymentDto;
import com.diamond.diamond.entities.AccountWallet;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.entities.payments.SimplePayment;
import com.diamond.diamond.services.AccountService;
import com.diamond.diamond.services.AccountWalletService;
import com.diamond.diamond.services.payments.PromoCodeService;
import com.diamond.diamond.services.payments.SimplePaymentService;

@RestController
@RequestMapping("/simple-payments")
public class SimplePaymentController extends PaymentController<SimplePayment, NewSimplePaymentDto> {
    private final PromoCodeService promoCodeService;

    public SimplePaymentController(SimplePaymentService simplePaymentService, AccountService accountService, AccountWalletService accountWalletService, PromoCodeService promoCodeService) {
        this.paymentService = simplePaymentService;
        this.accountService = accountService;
        this.accountWalletService = accountWalletService;
        this.promoCodeService = promoCodeService;
    }

    @Override
    SimplePayment convertNewDtoToPayment(NewSimplePaymentDto paymentDto) {
        // TODO Auto-generated method stub
        List<AccountWallet> accountWallets = this.getAccountWalletsFromPaymentDto(paymentDto);

        List<PromoCode> validPromoCodes = new ArrayList<>();
        for (Long promoCodeId : paymentDto.getValidPromoCodeIds()) {
            validPromoCodes.add(promoCodeService.findPromoCodeById(promoCodeId));
        }
    
        return new SimplePayment(paymentDto.getAmount(), 
                                   accountService.findAccountById(paymentDto.getAccountId()),  
                                   paymentDto.getChain(), 
                                   accountWallets, 
                                   paymentDto.getHasMaxNumberOfPayments(), 
                                   paymentDto.getMaxNumberOfPayments(), 
                                   paymentDto.getEnablePromoCodes(), 
                                   validPromoCodes,
                                   paymentDto.getCategory(), 
                                   paymentDto.getCurrencies());
    }
}

