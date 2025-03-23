package com.diamond.diamond.controllers.payments.txns;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diamond.diamond.dtos.payments.PromoCodeDto;
import com.diamond.diamond.dtos.payments.txns.FetchPaymentTxnDto;
import com.diamond.diamond.dtos.payments.txns.NewPaymentTxnDto;
import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.services.CustomerService;
import com.diamond.diamond.services.payments.PaymentService;
import com.diamond.diamond.services.payments.PaymentTxnService;
import com.diamond.diamond.services.payments.PromoCodeService;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.PaymentStatus;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.validation.Valid;

@RequestMapping("/txns")
public abstract class PaymentTxnController<T extends PaymentTxn, P extends Payment> {
    protected PaymentTxnService<T> txnService;
    protected PaymentService<P> paymentService;
    protected PromoCodeService promoCodeService;
    protected CustomerService customerService;
    abstract T convertNewDtoToTxn(NewPaymentTxnDto txnDto); // helper method for converting to and from the PaymentTxn type and the NewPaymentTxn DTO
    
    private FetchPaymentTxnDto loadPromoCodesApplied(FetchPaymentTxnDto txnDto) {
        List<PromoCodeDto> codesApplied = promoCodeService.findPromoCodesAppliedByPaymentTxn(txnService.findTxnById(txnDto.getId()));
        List<Long> promoCodeIds = new ArrayList<>();
        for (PromoCodeDto code : codesApplied) {
            promoCodeIds.add(code.getId());
        }
        txnDto.setPromoCodesAppliedIds(promoCodeIds);
        return txnDto;
    }

    @PostMapping("/new")
    public FetchPaymentTxnDto newTxn(@Valid @RequestBody NewPaymentTxnDto txnDto) {
        return txnService.savePaymentTxn(convertNewDtoToTxn(txnDto));
    }

    // TODO: add filter for wallets
    @GetMapping("/txns")
    public List<FetchPaymentTxnDto> findTxns(
        @RequestBody(required=false) UUID id,
        @RequestBody(required=false) UUID paymentId,
        @RequestBody(required=false) UUID accountId,
        @RequestBody(required=false) UUID customerId,
        @RequestBody(required=false) StablecoinCurrency currency,
        @RequestBody(required=false) Blockchain chain,
        @RequestBody(required=false) BigDecimal revenueGreaterThan,
        @RequestBody(required=false) BigDecimal revenueLessThan,
        @RequestBody(required=false) PaymentStatus status,
        @RequestBody(required=false) Date paidBefore,
        @RequestBody(required=false) Date paidAfter,
        @RequestBody(required=false) Integer pageSize
    ) {
        return txnService.findTxnDtosWithFilters(id, paymentId, accountId, customerId, currency, chain, revenueGreaterThan, revenueLessThan, status, paidBefore, paidAfter, pageSize);
    }

    @GetMapping("/id/{id}")
    public FetchPaymentTxnDto findById(@PathVariable(value="id") UUID id) {
        FetchPaymentTxnDto txnDto = txnService.findTxnDtoById(id);
        loadPromoCodesApplied(txnDto);
        return txnDto;
    }

    // @GetMapping("/paymentid/{id}")
    // public List<FetchPaymentTxnDto> findAllByPaymentId(@PathVariable(value="id") String paymentId) {
    //     return txnService.findAllByPaymentId(paymentId);
    // }

    @GetMapping("/txhash/{txhash}")
    public FetchPaymentTxnDto findByTxhash(@PathVariable(value="txhash") String txHash) {
        FetchPaymentTxnDto txnDto = txnService.findTxnDtoByTxHash(txHash);
        loadPromoCodesApplied(txnDto);
        return txnDto;
    }

    // @GetMapping("/customerid/{id}")
    // public List<FetchPaymentTxnDto> findAllByCustomerId(@PathVariable(value="id") String customerId) {
    //     return txnService.findAllByCustomerId(customerId);
    // }

    //@PostMapping("/update")

    @DeleteMapping("/id/{id}/delete")
    public FetchPaymentTxnDto delete(@PathVariable(value="id") UUID id) {
        FetchPaymentTxnDto txnDto = txnService.findTxnDtoById(id);
        loadPromoCodesApplied(txnDto);
        txnService.deleteTxnById(id);
        return txnDto;
    }
    
}
