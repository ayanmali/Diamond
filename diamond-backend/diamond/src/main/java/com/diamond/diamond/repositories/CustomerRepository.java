package com.diamond.diamond.repositories;

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

import com.diamond.diamond.entities.user.Account;
import com.diamond.diamond.entities.user.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);

    List<Customer> findByAccount(Account account);

    @Query("SELECT c FROM Customer c WHERE " + 
    "(:customerId IS NULL OR c.id = :customerId) AND " +
    "(:email IS NULL OR c.email = :email) AND " +
    "(:accountId IS NULL OR c.account.id = :accountId) AND " +
    "(:createdBefore IS NULL OR c.createdAt <= :createdBefore) AND " +
    "(:createdAfter IS NULL OR c.createdAt >= :createdAfter)")
    Page<Customer> findCustomersWithFilters(
        @Param("id") UUID customerId,
        @Param("email") String email,
        @Param("accountId") UUID accountId,
        @Param("createdBefore") Date createdBefore,
        @Param("createdAfter") Date createdAfter,
        Pageable pageable
    );

    // @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.wallets WHERE c.id = :id")
    // Optional<Customer> findByIdWithWallets(@Param("id") UUID id);
}
