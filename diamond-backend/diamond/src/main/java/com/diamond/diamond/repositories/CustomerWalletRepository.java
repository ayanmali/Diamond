package com.diamond.diamond.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.CustomerWallet;

@Repository
public interface CustomerWalletRepository extends JpaRepository<CustomerWallet, UUID> {
    
}
