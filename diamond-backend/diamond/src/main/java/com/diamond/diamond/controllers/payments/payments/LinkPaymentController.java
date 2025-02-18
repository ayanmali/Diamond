package com.diamond.diamond.controllers.payments.payments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payments.new_payments.NewLinkPaymentDto;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.entities.payments.link_payments.LinkPayment;
import com.diamond.diamond.services.VendorService;
import com.diamond.diamond.services.VendorWalletService;
import com.diamond.diamond.services.payments.PromoCodeService;
import com.diamond.diamond.services.payments.linkpayments.LinkPaymentService;

@RestController
@RequestMapping("/payments/link-payments")
public class LinkPaymentController extends PaymentController<LinkPayment, NewLinkPaymentDto> {
    private final PromoCodeService promoCodeService;

    public LinkPaymentController(LinkPaymentService linkPaymentService, VendorService vendorService, VendorWalletService vendorWalletService, PromoCodeService promoCodeService) {
        this.paymentService = linkPaymentService;
        this.vendorService = vendorService;
        this.vendorWalletService = vendorWalletService;
        this.promoCodeService = promoCodeService;
    }

    @Override
    LinkPayment convertNewDtoToPayment(NewLinkPaymentDto paymentDto) {
        List<VendorWallet> vendorWallets = this.getVendorWalletsFromPaymentDto(paymentDto);

        List<PromoCode> validPromoCodes = new ArrayList<>();
        for (Long promoCodeId : paymentDto.getValidPromoCodeIds()) {
            validPromoCodes.add(promoCodeService.findPromoCodeById(promoCodeId));
        }
    
        return new LinkPayment(paymentDto.getAmount(), 
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
