package com.diamond.diamond.dtos.payments.fetch_payments;

import com.diamond.diamond.entities.payments.FlexiblePayment;
import com.diamond.diamond.entities.payments.Payment;

/*
 * Response for retrieving a flexible payment.
 * Amount is 
 */
public class FetchFlexiblePaymentDto extends FetchSimplePaymentDto {
    public FetchFlexiblePaymentDto(Payment payment, Boolean hasMaxNumberOfPayments, Integer maxNumberOfPayments) {
        super(payment, hasMaxNumberOfPayments, maxNumberOfPayments, false);
    }

    public FetchFlexiblePaymentDto(FlexiblePayment flexiblePayment) {
        super(flexiblePayment);
    }
}
