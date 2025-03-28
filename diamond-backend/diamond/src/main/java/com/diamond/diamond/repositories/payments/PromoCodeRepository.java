package com.diamond.diamond.repositories.payments;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.catalogue.coupons.PromoCode;
import com.diamond.diamond.entities.payments.PaymentTxn;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
    List<PromoCode> findByPaymentTxns(Set<PaymentTxn> paymentTxns);
}
