package com.diamond.diamond.repositories.payments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.PromoCode;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
    
}
