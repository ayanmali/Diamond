package com.diamond.diamond.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.Vendor;
import com.diamond.diamond.entities.VendorWallet;
import com.diamond.diamond.entities.payments.Payment;



@Repository
public interface VendorWalletRepository extends JpaRepository<VendorWallet, Long> {

    Optional<VendorWallet> findByAddress(String address);
    List<VendorWallet> findByVendor(Vendor vendor);
    // Geting the VendorWallets associated with any number of Payments
    // For a single Payment, this query returns the wallet distribution for that Payment
    List<VendorWallet> findByPayments(List<Payment> payments);
    //List<VendorWallet> findByPaymentId();
    
}