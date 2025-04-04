package com.diamond.diamond.entities.payments;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.diamond.diamond.types.Blockchain;
import com.diamond.diamond.types.PaymentStatus;
import com.diamond.diamond.types.StablecoinCurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/*
 * Used for SimplePayments (checkout and link payment) Transactions
 */
@Entity
@Table(name="txns")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PaymentTxn {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable=false)
    private UUID id;

    // @ManyToOne
    // @JoinColumn(name="payment_id", referencedColumnName="id", nullable=false)
    // private SimplePayment payment; // the payment configuration created by the Account that is associated with this transaction
    @Column(name="payment_id", nullable=false) // the payment configuration (SimplePayment) created by the Account that is associated with this transaction
    private UUID paymentId;

    @Column(name="account_id", nullable=false)
    private UUID accountId;

    @Enumerated(EnumType.STRING)
    private Blockchain chain;
    
    // @ManyToOne
    // @JoinColumn(name="customer_id", referencedColumnName="id")
    // private Customer customer;
    @Column(name="customer_id", nullable=false)
    private UUID customerId;

    @Column(name="revenue")
    @Positive
    private BigDecimal revenue;

    @Column(name="currency_used")
    @Enumerated(EnumType.STRING)
    private StablecoinCurrency currencyUsed;

    // TODO: allow customers to pay with a chain of their choice and store it in this field
    // @Column(name="chain")
    // @Enumerated(EnumType.STRING)
    // private Blockchain chain;

    /*
     * Hash for signing/approving the transaction in the user's wallet
     */
    @Column(name="sign_hash", unique=true)
    @Pattern(regexp="^(0x[A-Fa-f0-9]{64}|[1-9A-HJ-NP-Za-km-z]{88})$")
    private String signHash;

    /*
     * Hash for the token transfer
     */
    @Column(name="tx_hash", unique=true)
    @Pattern(regexp="^(0x[A-Fa-f0-9]{64}|[1-9A-HJ-NP-Za-km-z]{88})$")
    private String txHash;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    private PaymentStatus status;

    @Column(name="time_paid")
    @PastOrPresent
    private Date timePaid;

    // @ManyToMany
    // @JoinTable(
    //     name = "payment_txn_promo_codes", // Optional: Custom name for the join table
    //     joinColumns = @JoinColumn(name = "payment_txn_id"),
    //     inverseJoinColumns = @JoinColumn(name = "promo_code_id")
    // )
    // private List<PromoCode> codesApplied;

    public PaymentTxn() {}

    public PaymentTxn(UUID paymentId, UUID customerId, BigDecimal revenue/*, List<PromoCode> codesApplied*/) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.revenue = revenue;
        // this.codesApplied = codesApplied;
        this.status = PaymentStatus.PROCESSING;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PaymentTxn)) {
            return false;
        }
        PaymentTxn txnObject = (PaymentTxn) o;
        return this.id == txnObject.getId();

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }
    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.revenue = totalRevenue;
    }
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
    // public List<PromoCode> getCodesApplied() {
    //     return codesApplied;
    // }
    // public void setCodesApplied(List<PromoCode> codesApplied) {
    //     this.codesApplied = codesApplied;
    // }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSignHash() {
        return signHash;
    }

    public void setSignHash(String signHash) {
        this.signHash = signHash;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public Date getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(Date timePaid) {
        this.timePaid = timePaid;
    }

    public StablecoinCurrency getCurrencyUsed() {
        return currencyUsed;
    }

    public void setCurrencyUsed(StablecoinCurrency currencyUsed) {
        this.currencyUsed = currencyUsed;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Blockchain getChain() {
        return chain;
    }

    public void setChain(Blockchain chain) {
        this.chain = chain;
    }

}
