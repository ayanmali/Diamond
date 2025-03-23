package com.diamond.diamond.repositories.payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.PaymentTxn;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.PaymentStatus;
import com.diamond.diamond.types.StablecoinCurrency;

@Repository
public interface PaymentTxnRepository<T extends PaymentTxn> extends JpaRepository<T, UUID> {
    Optional<T> findByTxHash(String txHash);
    @Query("SELECT t FROM #{#entityName} t WHERE " +
           "(:id IS NULL OR t.id = :id) AND " +
           "(:paymentId IS NULL OR t.payment.id = :paymentId) AND " +
           "(:accountId IS NULL OR t.payment.account.id = :accountId) AND " +
           "(:customerId IS NULL OR t.customer.id = :customerId) AND " +
           "(:currency IS NULL OR t.payment.currency = :currency) AND " +
           "(:chain IS NULL OR t.payment.chain = :chain) AND " +
           "(:revenueGreaterThan IS NULL OR t.revenue >= :revenueGreaterThan) AND " +
           "(:revenueLessThan IS NULL OR t.revenue <= :revenueLessThan) AND " +
           "(:status IS NULL OR t.status = :status) AND " +
           "(:paidBefore IS NULL OR t.timePaid <= :paidBefore) AND " +
           "(:paidAfter IS NULL OR t.timePaid >= :paidAfter)")
    Page<T> findTxnsWithFilters(
        @Param("id") UUID id,
        @Param("paymentId") UUID paymentId,
        @Param("accountId") UUID accountId,
        @Param("customerId") UUID customerId,
        @Param("currency") StablecoinCurrency currency,
        @Param("chain") Blockchain chain,
        @Param("revenueGreaterThan") BigDecimal revenueGreaterThan,
        @Param("revenueLessThan") BigDecimal revenueLessThan,
        @Param("status") PaymentStatus status,
        @Param("paidBefore") Date paidBefore,
        @Param("paidAfter") Date paidAfter,
        Pageable pageable
    );
}
