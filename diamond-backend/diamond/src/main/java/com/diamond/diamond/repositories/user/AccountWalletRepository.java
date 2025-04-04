package com.diamond.diamond.repositories.user;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.user.AccountWallet;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.WalletStatus;

@Repository
public interface AccountWalletRepository extends JpaRepository<AccountWallet, UUID> {

    Optional<AccountWallet> findByAddress(String address);
    List<AccountWallet> findByAccountId(UUID accountId);
    // Geting the AccountWallets associated with any number of Payments
    // For a single Payment, this query returns the wallet distribution for that Payment
    //List<AccountWallet> findByPaymentIds(List<UUID> paymentIds);
    //List<AccountWallet> findByPaymentId();

    @Query("SELECT w from AccountWallet w WHERE " +
    "(:walletId IS NULL OR w.id = :walletId) AND " +
    "(:accountId IS NULL OR w.accountId = :accountId) AND " + 
    "(:chain IS NULL OR w.chain = :chain) AND " + 
    "(:status IS NULL OR w.status = :status) AND " + 
    "(:createdBefore IS NULL OR w.createdAt <= :createdBefore) AND " + 
    "(:createdAfter IS NULL OR w.createdAt >= :createdAfter)")
    Page<AccountWallet> findAccountsWithFilters(
        @Param("walletId") UUID walletId,
        @Param("accountId") UUID accountId,
        @Param("chain") Blockchain chain,
        @Param("status") WalletStatus status,
        @Param("createdBefore") Date createdBefore,
        @Param("createdAfter") Date createdAfter,
        Pageable pageable
    );
    
}