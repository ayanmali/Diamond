package com.diamond.diamond.repositories.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diamond.diamond.entities.user.TokenTransfer;

@Repository
public interface TokenTransferRepository extends JpaRepository<TokenTransfer, UUID>{
    
}
