/*
 * Defines a user-initiated transfer between two tokens (USDC, EURC, SOL), etc
 */
package com.diamond.diamond.entities.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.diamond.diamond.dtos.account.transfers.NewTokenTransferDto;
import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.StablecoinCurrency;
import com.diamond.diamond.types.TokenTransferStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name="token_transfers")
public class TokenTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    //@ManyToOne
    //@JoinColumn(name="account_id", nullable=false, referencedColumnName="id")
    @Column(name="account_id", nullable=false)
    private UUID accountId;

    @Enumerated(EnumType.STRING)
    @Column(name="token", nullable=false)
    private StablecoinCurrency token;

    @Column(name="receiver_address", nullable=false)
    @Pattern(regexp="^(0x[a-fA-F0-9]{40}|[1-9A-HJ-NP-Za-km-z]{32,44})$")
    private String receiverAddress;

    @Enumerated(EnumType.STRING)
    @Column(name="chain", nullable=false)
    private Blockchain chain;

    @Positive
    @Column(name="amount", nullable=false)
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name="date")
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private TokenTransferStatus status;

    //@ManyToOne
    //@Column(name="wallet", nullable=false)
    @Column(name="account_wallet_id", nullable=false)
    private UUID accountWalletId;

    @PositiveOrZero
    @Column(name="lamports_fee")
    private BigDecimal lamportsFee;

    @Column(name="sign_hash")
    @Pattern(regexp="^(0x[A-Fa-f0-9]{64}|[1-9A-HJ-NP-Za-km-z]{88})$")
    private String signHash;

    public TokenTransfer(UUID accountId, UUID accountWalletId, StablecoinCurrency token, String receiverAddress, Blockchain chain, BigDecimal amount, BigDecimal lamportsFee) {
        this.accountId = accountId;
        this.accountWalletId = accountWalletId;
        this.token = token;
        this.receiverAddress = receiverAddress;
        this.chain = chain;
        this.amount = amount;
        this.lamportsFee = lamportsFee;

        this.status = TokenTransferStatus.PENDING;
    }

    public TokenTransfer(NewTokenTransferDto transferDto, UUID accountId, BigDecimal lamportsFee) {
        this.accountId = accountId;
        this.accountWalletId = transferDto.getAccountWalletId();
        this.token = transferDto.getToken();
        this.receiverAddress = transferDto.getReceiverAddress();
        this.chain = transferDto.getChain();
        this.amount = transferDto.getAmount();
        this.lamportsFee = lamportsFee;

        this.status = TokenTransferStatus.PENDING;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public StablecoinCurrency getToken() {
        return token;
    }

    public Blockchain getChain() {
        return chain;
    }

    public void setChain(Blockchain chain) {
        this.chain = chain;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedat(Date createdAt) {
        this.createdAt = createdAt;
    }

    public TokenTransferStatus getStatus() {
        return status;
    }

    public void setStatus(TokenTransferStatus status) {
        this.status = status;
    }

    public BigDecimal getLamportsFee() {
        return lamportsFee;
    }

    public void setLamportsFee(BigDecimal lamportsFee) {
        this.lamportsFee = lamportsFee;
    }

    public UUID getAccountWalletId() {
        return accountWalletId;
    }

    public void setAccountWalletId(UUID accountWalletId) {
        this.accountWalletId = accountWalletId;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public void setToken(StablecoinCurrency token) {
        this.token = token;
    }

    public String getSignHash() {
        return signHash;
    }

    public void setSignHash(String signHash) {
        this.signHash = signHash;
    }

}
