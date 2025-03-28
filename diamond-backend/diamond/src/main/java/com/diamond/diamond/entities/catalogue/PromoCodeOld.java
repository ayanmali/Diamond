// /*
//  * Promo codes can be applied to checkout payments and payment links
//  */
// package com.diamond.diamond.entities.catalogue;

// import java.math.BigDecimal;
// import java.util.Date;
// import java.util.Set;

// import com.diamond.diamond.entities.payments.Payment;
// import com.diamond.diamond.entities.payments.PaymentTxn;
// import com.diamond.diamond.entities.user.Account;
// import com.diamond.diamond.types.PromoCodeDiscountType;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import jakarta.validation.constraints.Future;
// import jakarta.validation.constraints.Positive;
// import jakarta.validation.constraints.Size;

// @Entity
// @Table(name="promo_codes")
// public class PromoCode {
//     @Id
//     @GeneratedValue(strategy=GenerationType.AUTO)
//     @Column(nullable=false)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name="account_id", referencedColumnName="id", nullable=false)
//     private Account account;

//     @ManyToOne
//     @JoinColumn(name="payment_id", referencedColumnName="id", nullable=false)
//     private Set<Payment> payments;

//     // appears on customer's receipts and invoices -- e.g. "First Purchase Discount"
//     @Column(nullable=false)
//     private String name;


//     @Column(name="customer_code")
//     private String customerCode;

//     @Column(name="duration_in_months")
//     @Positive
//     // represents how long (in months) this coupon will be applied for (for subscriptions)
//     private Long durationInMonths;

//     @Future
//     private Date expiration;

//     @Enumerated
//     @Column(name="discount_type", nullable=false)
//     private PromoCodeDiscountType discountType;

//     // can represent either a percentage or a fixed amount, depending on the specified type
//     @Column(nullable=false)
//     private BigDecimal discount;

//     @ManyToMany(mappedBy = "codesApplied")
//     private Set<PaymentTxn> paymentTxns;

//     public PromoCode() {}

//     public PromoCode(Account account, Payment payment, String code, BigDecimal discount) {
//         this.account = account;
//         this.payment = payment;
//         this.code = code;
//         this.discount = discount;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Account getAccount() {
//         return account;
//     }

//     public void setAccountId(Account account) {
//         this.account = account;
//     }

//     public Payment getPayment() {
//         return payment;
//     }

//     public void setPayment(Payment payment) {
//         this.payment = payment;
//     }

//     public String getCode() {
//         return code;
//     }

//     public void setCode(String code) {
//         this.code = code;
//     }

//     public Date getExpiration() {
//         return expiration;
//     }

//     public void setExpiration(Date expiration) {
//         this.expiration = expiration;
//     }

//     public BigDecimal getDiscount() {
//         return discount;
//     }

//     public void setDiscount(BigDecimal discount) {
//         this.discount = discount;
//     }

//     public Set<PaymentTxn> getPaymentTxns() {
//         return paymentTxns;
//     }

//     public void setPaymentTxns(Set<PaymentTxn> paymentTxns) {
//         this.paymentTxns = paymentTxns;
//     }
// }
