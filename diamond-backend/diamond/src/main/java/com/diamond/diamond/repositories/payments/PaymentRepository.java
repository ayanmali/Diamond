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

import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.types.Blockchain;

@Repository
public interface PaymentRepository<T extends Payment> extends JpaRepository<T, UUID> {
    // TODO: filter for wallets
    @Query("SELECT p FROM #{#entityName} p WHERE " +
           "(:id IS NULL OR p.id = :id) AND " +
           "(:accountId IS NULL OR p.accountId = :accountId) AND " +
           "(:chain IS NULL OR p.chain = :chain) AND " +
           "(:amountGreaterThan IS NULL OR p.amount >= :amountGreaterThan) AND " +
           "(:amountLessThan IS NULL OR p.amount <= :amountLessThan) AND " +
           "(:chain IS NULL OR p.chain = :chain) AND " +
           "(:createdBefore IS NULL OR p.createdAt <= :createdBefore) AND " +
           "(:createdAfter IS NULL OR p.createdAt >= :createdAfter)")
    Page<T> findPaymentsWithFilters(
        @Param("id") UUID id,
        @Param("accountId") UUID accountId,
        @Param("chain") Blockchain chain,
        @Param("amountGreaterThan") BigDecimal amountGreaterThan,
        @Param("amountLessThan") BigDecimal amountLessThan,
        @Param("createdBefore") Date createdBefore,
        @Param("createdAfter") Date createdAfter,
        Pageable pageable
    );
}
