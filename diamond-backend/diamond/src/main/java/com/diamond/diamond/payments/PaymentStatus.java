package com.diamond.diamond.payments;

public enum PaymentStatus {
    SUCCEEDED, FAILED, PENDING, CANCELLED, REFUNDED;

    @Override
    public String toString() {
        return name();
    }
}
