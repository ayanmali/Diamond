package com.diamond.diamond.repositories.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.user.Customer;
import com.diamond.diamond.entities.user.CustomerWallet;


@Repository
public interface CustomerWalletRepository extends JpaRepository<CustomerWallet, UUID> {
    Optional<CustomerWallet> findByAddress(String address);

    List<CustomerWallet> findByCustomer(Customer customer);
}
