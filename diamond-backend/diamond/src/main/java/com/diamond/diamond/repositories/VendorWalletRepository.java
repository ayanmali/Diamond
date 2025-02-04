package com.diamond.diamond.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.VendorWallet;

@Repository
public interface VendorWalletRepository extends JpaRepository<VendorWallet, Long> {

    Optional<VendorWallet> findByAddress(String address);
}