package com.diamond.diamond.repositories.payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.payments.Payout;
import com.diamond.diamond.types.PayoutStatus;

@Repository
public interface PayoutRepository extends JpaRepository<Payout, UUID> {
    @Query("SELECT p FROM Payout p WHERE " +
           "(:id IS NULL OR p.id = :id) AND " +
           "(:accountId IS NULL OR p.account.id = :accountId) AND " +
           "(:walletId IS NULL OR p.offrampWallet.id = :walletId) AND " +
           "(:amountLessThan IS NULL OR p.amount <= :amountLessThan) AND " +
           "(:amountGreaterThan IS NULL OR p.amount >= :amountGreaterThan) AND " +
           "(:status IS NULL OR p.status = :status) AND " +
           "(:createdBefore IS NULL OR p.createdAt <= :createdBefore) AND " +
           "(:createdAfter IS NULL OR p.createdAt >= :createdAfter) AND " +
           "(:paidBefore IS NULL OR p.payoutDate <= :paidBefore) AND " +
           "(:paidAfter IS NULL OR p.payoutDate >= :paidAfter)")
    Page<Payout> findPayoutsWithFilters(
        @Param("id") UUID id,
        @Param("accountId") UUID accountId,
        @Param("walletId") UUID walletId,
        @Param("amountLessThan") BigDecimal amountLessThan,
        @Param("amountGreaterThan") BigDecimal amountGreaterThan,
        @Param("status") PayoutStatus status,
        @Param("createdBefore") Date createdBefore,
        @Param("createdAfter") Date createdAfter,
        @Param("paidBefore") Date paidBefore,
        @Param("paidAfter") Date paidAfter,
        Pageable pageable
    );
}
