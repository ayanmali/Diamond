package com.diamond.diamond.controllers.payments.payments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payments.new_payments.NewCheckoutPaymentDto;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.entities.payments.checkouts.CheckoutPayment;
import com.diamond.diamond.services.VendorService;
import com.diamond.diamond.services.VendorWalletService;
import com.diamond.diamond.services.payments.PromoCodeService;
import com.diamond.diamond.services.payments.checkouts.CheckoutService;

@RestController
@RequestMapping("/checkout-payments")
public class CheckoutPaymentController extends PaymentController<CheckoutPayment, NewCheckoutPaymentDto> {
    private final PromoCodeService promoCodeService;

    public CheckoutPaymentController(CheckoutService checkoutPaymentService, VendorService vendorService, VendorWalletService vendorWalletService, PromoCodeService promoCodeService) {
        this.paymentService = checkoutPaymentService;
        this.vendorService = vendorService;
        this.vendorWalletService = vendorWalletService;
        this.promoCodeService = promoCodeService;
    }

    @Override
    CheckoutPayment convertNewDtoToPayment(NewCheckoutPaymentDto paymentDto) {
        // TODO Auto-generated method stub
        List<VendorWallet> vendorWallets = this.getVendorWalletsFromPaymentDto(paymentDto);

        List<PromoCode> validPromoCodes = new ArrayList<>();
        for (Long promoCodeId : paymentDto.getValidPromoCodeIds()) {
            validPromoCodes.add(promoCodeService.findPromoCodeById(promoCodeId));
        }
    
        return new CheckoutPayment(paymentDto.getAmount(), 
                                   vendorService.findVendorById(paymentDto.getVendorId()), 
                                   paymentDto.getCurrency(), 
                                   paymentDto.getChain(), 
                                   vendorWallets, 
                                   paymentDto.getHasMaxNumberOfPayments(), 
                                   paymentDto.getMaxNumberOfPayments(), 
                                   paymentDto.getEnablePromoCodes(), 
                                   validPromoCodes);
    }
}
