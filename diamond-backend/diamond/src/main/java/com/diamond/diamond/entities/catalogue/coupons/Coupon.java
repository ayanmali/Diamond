// package com.diamond.diamond.entities.catalogue.coupons;

// import java.math.BigDecimal;
// import java.util.Date;
// import java.util.List;
// import java.util.Set;
// import java.util.UUID;

// import org.springframework.data.annotation.Id;

// import com.diamond.diamond.entities.payments.Invoice;
// import com.diamond.diamond.types.CouponType;
// import com.diamond.diamond.types.FiatCurrency;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.OneToMany;
// import jakarta.validation.constraints.Future;
// import jakarta.validation.constraints.Positive;
// import jakarta.validation.constraints.PositiveOrZero;
// import jakarta.validation.constraints.Size;

// @Entity
// public class Coupon {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private UUID id;

//     // The name appears on receipts and invoices.
//     @Column(nullable = false)
//     @Size(min=1, max=50)
//     private String name;

//     // Indicates whether discount is percentage or a fixed amount.
//     @Enumerated(EnumType.STRING)
//     @Column(nullable = false)
//     private CouponType type;

//     // Discount amount (percentage value or fixed monetary amount).
//     @Column(nullable = false)
//     @Positive
//     private BigDecimal discountAmount;

//     // only applicable when the discount is a fixed amount; this field specifies the currency of the amount to discount
//     @Column(name="fiat_currency")
//     @Enumerated
//     private FiatCurrency discountCurrency;

//     // The Invoice(s) that this coupon applies to
//     @ManyToMany
//     @JoinTable(
//         name = "invoice_coupons", // Optional: Custom name for the join table
//         joinColumns = @JoinColumn(name = "coupon_id", referencedColumnName="id"),
//         inverseJoinColumns = @JoinColumn(name = "invoice_id", referencedColumnName="id")
//     )
//     //@JoinColumn(name="payment_id", referencedColumnName="id")
//     private Set<Invoice> invoicesAppliedTo;

//     // For coupons applicable to a specific customer, this defines the duration (in months) for which it is valid.
//     // null indicates infinite duration
//     @Column(name="duration_in_months")
//     @Positive
//     private Integer durationInMonths;

//     // The maximum number of times this coupon can be redeemed.
//     @Column(name="max_redemptions")
//     @Positive
//     private Long maxRedemptions; // Maximum number of times the coupon can be redeemed

//     // The current number of times this coupon has been redeemed,
//     // This coupon is no longer valid once this value is equal to the max number of redemptions.
//     @Column(name="curr_redemptions")
//     @PositiveOrZero
//     private Long currRedemptions;

//     // Becomes inactive once the current number of redemptions = the max number of redemptions
//     // also becomes inactive if the user explicitly specifies it as inactive
//     @Column(name="is_active", nullable=false)
//     private Boolean isActive;

//     @Column(name="expiration_date")
//     @Future
//     private Date expirationDate; // Overall coupon expiration date

//     // A coupon may have one or more promo codes associated with it that a Customer can enter at the point of sale
//     @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     @Column(name="promo_codes")
//     private List<PromoCode> promoCodes;

//     public Coupon() {}

//     public UUID getId() {
//         return id;
//     }

//     public void setId(UUID id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public CouponType getType() {
//         return type;
//     }

//     public void setType(CouponType type) {
//         this.type = type;
//     }

//     public BigDecimal getDiscountAmount() {
//         return discountAmount;
//     }

//     public void setDiscountAmount(BigDecimal discountAmount) {
//         this.discountAmount = discountAmount;
//     }

//     public Integer getDurationInMonths() {
//         return durationInMonths;
//     }

//     public void setDurationInMonths(Integer durationInMonths) {
//         this.durationInMonths = durationInMonths;
//     }

//     public Long getMaxRedemptions() {
//         return maxRedemptions;
//     }

//     public void setMaxRedemptions(Long maxRedemptions) {
//         this.maxRedemptions = maxRedemptions;
//     }

//     public Date getExpirationDate() {
//         return expirationDate;
//     }

//     public void setExpirationDate(Date expirationDate) {
//         this.expirationDate = expirationDate;
//     }

//     public List<PromoCode> getPromoCodes() {
//         return promoCodes;
//     }

//     public void setPromoCodes(List<PromoCode> promoCodes) {
//         this.promoCodes = promoCodes;
//     }

//     public Boolean getIsActive() {
//         return isActive;
//     }

//     public void setIsActive(Boolean isActive) {
//         this.isActive = isActive;
//     }

//     public Long getCurrRedemptions() {
//         return currRedemptions;
//     }

//     public void setCurrRedemptions(Long currRedemptions) {
//         this.currRedemptions = currRedemptions;
//     }

//     public Set<Invoice> getInvoicesAppliedTo() {
//         return invoicesAppliedTo;
//     }

//     public void setInvoicesAppliedTo(Set<Invoice> invoicesAppliedTo) {
//         this.invoicesAppliedTo = invoicesAppliedTo;
//     }

//     public FiatCurrency getDiscountCurrency() {
//         return discountCurrency;
//     }

//     public void setDiscountCurrency(FiatCurrency fiatCurrency) {
//         this.discountCurrency = fiatCurrency;
//     }
    
// }
