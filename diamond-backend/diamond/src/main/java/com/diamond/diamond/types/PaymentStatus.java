package com.diamond.diamond.types;

public enum PaymentStatus {
    SUCCEEDED, FAILED, PROCESSING, CANCELLED, REQUIRES_ACTION, REQUIRES_CONFIRMATION, REQUIRES_PAYMENT_METHOD;
    // requires_capture
    @Override
    public String toString() {
        return name();
    }
}
