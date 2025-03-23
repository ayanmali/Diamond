package com.diamond.diamond.repositories;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByEmail(String email);
    
    @Query("SELECT a FROM Account a WHERE " +
           "(:id IS NULL OR a.id = :id) AND " +
           "(:email IS NULL OR LOWER(a.email) = LOWER(:email)) AND " +
           "(:createdBefore IS NULL OR a.createdAt <= :createdBefore) AND " +
           "(:createdAfter IS NULL OR a.createdAt >= :createdAfter)")
    Page<Account> findAccountsWithFilters(
        @Param("id") UUID id,
        @Param("email") String email,
        @Param("createdBefore") Date createdBefore,
        @Param("createdAfter") Date createdAfter,
        Pageable pageable
    );
    //List<AccountWallet> findWallets(UUID id);
}