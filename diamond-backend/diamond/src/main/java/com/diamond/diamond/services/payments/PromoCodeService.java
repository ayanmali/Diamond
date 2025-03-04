package com.diamond.diamond.services.payments;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.diamond.diamond.dtos.payments.PromoCodeDto;
import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.repositories.payments.PromoCodeRepository;

@Service
public class PromoCodeService {
    private final PromoCodeRepository promoCodeRepository;

    public PromoCodeService(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public static PromoCodeDto convertPromoCodeToDto(PromoCode promoCode) {
        PromoCodeDto promoCodeDto = new PromoCodeDto();
        promoCodeDto.setCode(promoCode.getCode());
        promoCodeDto.setDiscount(promoCode.getDiscount());
        promoCodeDto.setExpiration(promoCode.getExpiration());
        promoCodeDto.setId(promoCode.getId());
        promoCodeDto.setPaymentId(promoCode.getPayment().getId());
        promoCodeDto.setAccountId(promoCode.getAccount().getId());
        return promoCodeDto;
    }

    public PromoCodeDto savePromoCode(PromoCode promoCode) {
        return convertPromoCodeToDto(promoCodeRepository.save(promoCode));
    }

    public PromoCodeDto findPromoCodeDtoById(Long id) {
        return convertPromoCodeToDto(promoCodeRepository.findById(id).orElseThrow());
    }

    public PromoCode findPromoCodeById(Long id) {
        return promoCodeRepository.findById(id).orElseThrow();
    }

    public List<PromoCodeDto> findPromoCodesAppliedByPaymentTxn(PaymentTxn paymentTxn) {
        return promoCodeRepository.findByPaymentTxns(Set.of(paymentTxn)).stream() // Convert the List<PromoCode> to a Stream<PromoCode>
        .map(PromoCodeService::convertPromoCodeToDto) // Map each AccountWallet to PromoCodeDto
        .collect(Collectors.toList()); // Collect the results into a List<PromoCodeDto>
    }

    public List<PromoCodeDto> findPromoCodesAppliedByPaymentTxns(Set<PaymentTxn> paymentTxns) {
        return promoCodeRepository.findByPaymentTxns(paymentTxns).stream() // Convert the List<PromoCode> to a Stream<PromoCode>
        .map(PromoCodeService::convertPromoCodeToDto) // Map each AccountWallet to PromoCodeDto
        .collect(Collectors.toList()); // Collect the results into a List<PromoCodeDto>
    }

    // find all Payments that include a given PromoCode
    // public List<Payment> findPayments(Long id) {

    // }

    // // find all transactions where a given PromoCode was applied
    // public List<PaymentTxn> findPaymentTxns(Long id) {

    // }

    public PromoCodeDto updateDiscount(Long id, Double discount) {
        PromoCode promoCode = promoCodeRepository.findById(id).orElseThrow();
        promoCode.setDiscount(discount);
        return convertPromoCodeToDto(promoCodeRepository.save(promoCode));
    }

    public PromoCodeDto updateCode(Long id, String code) {
        PromoCode promoCode = promoCodeRepository.findById(id).orElseThrow();
        promoCode.setCode(code);
        return convertPromoCodeToDto(promoCodeRepository.save(promoCode));
    }

    public PromoCodeDto updateExpiration(Long id, Date expiration) {
        PromoCode promoCode = promoCodeRepository.findById(id).orElseThrow();
        promoCode.setExpiration(expiration);
        return convertPromoCodeToDto(promoCodeRepository.save(promoCode));
    }

    public void deletePromoCodeById(Long id) {
        promoCodeRepository.deleteById(id);
    }

    public void deletePromoCode(PromoCode promoCode) {
        promoCodeRepository.delete(promoCode);
    }

}
