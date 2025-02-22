package com.diamond.diamond.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.Customer;
import com.diamond.diamond.entities.CustomerWallet;


@Repository
public interface CustomerWalletRepository extends JpaRepository<CustomerWallet, Long> {
    Optional<CustomerWallet> findByAddress(String address);

    List<CustomerWallet> findByCustomer(Customer customer);
}
