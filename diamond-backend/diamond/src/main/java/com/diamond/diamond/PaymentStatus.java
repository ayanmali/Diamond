package com.diamond.diamond;

public enum PaymentStatus {
    SUCCEEDED, FAILED, PENDING, CANCELLED;

    @Override
    public String toString() {
        return name();
    }
}
