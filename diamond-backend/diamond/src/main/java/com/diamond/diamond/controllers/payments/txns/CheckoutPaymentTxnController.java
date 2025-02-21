package com.diamond.diamond.controllers.payments.txns;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.dtos.payments.txns.NewPaymentTxnDto;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.entities.payments.checkouts.CheckoutPayment;
import com.diamond.diamond.entities.payments.checkouts.CheckoutPaymentTxn;
import com.diamond.diamond.services.CustomerService;
import com.diamond.diamond.services.payments.PaymentService;
import com.diamond.diamond.services.payments.PaymentTxnService;
import com.diamond.diamond.services.payments.PromoCodeService;

@RestController
@RequestMapping("/txns/checkout-payments")
public class CheckoutPaymentTxnController extends PaymentTxnController<CheckoutPaymentTxn, CheckoutPayment> {
    public CheckoutPaymentTxnController(PaymentTxnService<CheckoutPaymentTxn> txnService, PaymentService<CheckoutPayment> paymentService, PromoCodeService promoCodeService, CustomerService customerService) {
        this.txnService = txnService;
        this.paymentService = paymentService;
        this.promoCodeService = promoCodeService;
        this.customerService = customerService;
    }

    @Override
    CheckoutPaymentTxn convertNewDtoToTxn(NewPaymentTxnDto txnDto) {
        // TODO Auto-generated method stub
        Set<PromoCode> codesApplied = new HashSet<>();
        for (Long promoCodeId : txnDto.getCodesAppliedIds()) {
            codesApplied.add(promoCodeService.findPromoCodeById(promoCodeId));
        }

        return new CheckoutPaymentTxn(paymentService.findPaymentById(txnDto.getPaymentId()),
                                                customerService.findCustomerById(txnDto.getCustomerId()),
                                                txnDto.getRevenue(), 
                                                codesApplied);
        
    }

}
