package com.diamond.diamond.controllers.payments.txns;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diamond.diamond.dtos.payments.txns.FetchPaymentTxnDto;
import com.diamond.diamond.dtos.payments.txns.NewPaymentTxnDto;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.services.CustomerService;
import com.diamond.diamond.services.payments.PaymentService;
import com.diamond.diamond.services.payments.PaymentTxnService;
import com.diamond.diamond.services.payments.PromoCodeService;

@RequestMapping("/txns")
public abstract class PaymentTxnController<T extends PaymentTxn, P extends Payment> {
    protected PaymentTxnService<T> txnService;
    protected PaymentService<P> paymentService;
    protected PromoCodeService promoCodeService;
    protected CustomerService customerService;
    abstract T convertNewDtoToTxn(NewPaymentTxnDto txnDto); // helper method for converting to and from the PaymentTxn type and the NewPaymentTxn DTO
    
    @PostMapping("/new")
    public FetchPaymentTxnDto newTxn(@RequestBody NewPaymentTxnDto txnDto) {
        return txnService.savePaymentTxn(convertNewDtoToTxn(txnDto));
    }
    
}
