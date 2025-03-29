// package com.diamond.diamond.entities.catalogue.coupons;

// import java.math.BigDecimal;
// import java.util.Date;
// import java.util.UUID;

// import org.springframework.data.annotation.Id;

// import com.diamond.diamond.entities.user.Customer;

// import jakarta.persistence.Column;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.validation.constraints.Future;
// import jakarta.validation.constraints.Positive;
// import jakarta.validation.constraints.PositiveOrZero;
// import jakarta.validation.constraints.Size;

// public class PromoCode {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private UUID id;

//     // The promo code string that the customer fills in at point of sale
//     @Column(nullable = false, unique = true)
//     @Size(min=1, max=50)
//     private String code;

//     // Specifies if the promo code is only eligible for first-time customers.
//     @Column(name="first_time_only", nullable=false)
//     private Boolean firstTimeOnly;

//     // If this promo code is limited to a particular customer, store that Customer here
//     @Column(name="customer_id", table="customers")
//     private Customer specificCustomer;

//     // Maximum number of times this promo code can be redeemed.
//     @Column(name="max_redemptions")
//     @Positive
//     private Long maxRedemptions;

//     // Number of times this promo code has been redeemed.
//     @Column(name="curr_redemptions")
//     @PositiveOrZero
//     private Long currRedemptions;

//     // Expiration date when the promo code will no longer be valid.
//     @Column(name="expiration_date")
//     @Future
//     private Date expirationDate;

//     // Maximum order value for which the promo code is applicable.
//     @Column(name="max_order_value")
//     @Positive
//     private BigDecimal maximumOrderValue;

//     // Association back to the parent Coupon.
//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "coupon_id")
//     private Coupon coupon;

//     public PromoCode() {}

//     public UUID getId() {
//         return id;
//     }

//     public void setId(UUID id) {
//         this.id = id;
//     }

//     public String getCode() {
//         return code;
//     }

//     public void setCode(String code) {
//         this.code = code;
//     }

//     public Boolean getFirstTimeOnly() {
//         return firstTimeOnly;
//     }

//     public void setFirstTimeOnly(Boolean firstTimeOnly) {
//         this.firstTimeOnly = firstTimeOnly;
//     }

//     public Date getExpirationDate() {
//         return expirationDate;
//     }

//     public void setExpirationDate(Date expirationDate) {
//         this.expirationDate = expirationDate;
//     }

//     public BigDecimal getMaximumOrderValue() {
//         return maximumOrderValue;
//     }

//     public void setMaximumOrderValue(BigDecimal maximumOrderValue) {
//         this.maximumOrderValue = maximumOrderValue;
//     }

//     public Coupon getCoupon() {
//         return coupon;
//     }

//     public void setCoupon(Coupon coupon) {
//         this.coupon = coupon;
//     }

//     public Customer getSpecificCustomer() {
//         return specificCustomer;
//     }

//     public void setSpecificCustomer(Customer specificCustomer) {
//         this.specificCustomer = specificCustomer;
//     }

//     public Long getCurrRedemptions() {
//         return currRedemptions;
//     }

//     public void setCurrRedemptions(Long currRedemptions) {
//         this.currRedemptions = currRedemptions;
//     }

//     public Long getMaxRedemptions() {
//         return maxRedemptions;
//     }

//     public void setMaxRedemptions(Long maxRedemptions) {
//         this.maxRedemptions = maxRedemptions;
//     }

    
// }
