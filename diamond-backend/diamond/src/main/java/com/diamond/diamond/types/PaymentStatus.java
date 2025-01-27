package com.diamond.diamond.types;

public enum PaymentStatus {
    SUCCEEDED, FAILED, PENDING, CANCELLED, REFUNDED;

    @Override
    public String toString() {
        return name();
    }
}
