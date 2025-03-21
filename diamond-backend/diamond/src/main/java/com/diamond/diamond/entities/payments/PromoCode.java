/*
 * Promo codes can be applied to checkout payments and payment links
 */
package com.diamond.diamond.entities.payments;

import java.util.Date;
import java.util.Set;

import com.diamond.diamond.entities.Account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="promo_codes")
public class PromoCode {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable=false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="account_id", referencedColumnName="id", nullable=false)
    private Account account;

    @ManyToOne
    @JoinColumn(name="payment_id", referencedColumnName="id", nullable=false)
    private Payment payment;

    @Column(nullable=false, unique=true)
    private String code;

    private Date expiration;

    @Column(nullable=false)
    private Double discount;

    @ManyToMany(mappedBy = "codesApplied")
    private Set<PaymentTxn> paymentTxns;

    public PromoCode() {}

    public PromoCode(Account account, Payment payment, String code, Double discount) {
        this.account = account;
        this.payment = payment;
        this.code = code;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccountId(Account account) {
        this.account = account;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Set<PaymentTxn> getPaymentTxns() {
        return paymentTxns;
    }

    public void setPaymentTxns(Set<PaymentTxn> paymentTxns) {
        this.paymentTxns = paymentTxns;
    }
}
