package com.diamond.diamond.controllers.payments.txns;

import org.springframework.web.bind.annotation.RequestMapping;

import com.diamond.diamond.dtos.payments.txns.NewPaymentTxnDto;
import com.diamond.diamond.services.payments.PaymentTxnService;

@RequestMapping("/txns")
public abstract class PaymentTxnController<T extends PaymentTxn> {
    protected PaymentTxnService<T> txnService;
    abstract P convertNewDtoToTxn(NewPaymentTxnDto txnDto); // helper method for converting to and from the PaymentTxn type and the NewPaymentTxn DTO
    


    
}
