package com.diamond.diamond.services.payments;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.diamond.diamond.entities.payments.PromoCode;
import com.diamond.diamond.repositories.payments.PromoCodeRepository;

@Service
public class PromoCodeService {
    private final PromoCodeRepository promoCodeRepository;

    public PromoCodeService(PromoCodeRepository promoCodeRepository) {
        this.promoCodeRepository = promoCodeRepository;
    }

    public PromoCode savePromoCode(PromoCode promoCode) {
        return promoCodeRepository.save(promoCode);
    }

    public Optional<PromoCode> findPromoCodeById(Long id) {
        return promoCodeRepository.findById(id);
    }

    public void deletePromoCodeById(Long id) {
        promoCodeRepository.deleteById(id);
    }

    public void deletePromoCode(PromoCode promoCode) {
        promoCodeRepository.delete(promoCode);
    }

}
