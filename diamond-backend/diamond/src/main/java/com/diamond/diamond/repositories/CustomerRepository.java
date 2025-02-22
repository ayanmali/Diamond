package com.diamond.diamond.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.Vendor;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);

    List<Customer> findByVendor(Vendor vendor);

    // @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.wallets WHERE c.id = :id")
    // Optional<Customer> findByIdWithWallets(@Param("id") UUID id);
}
