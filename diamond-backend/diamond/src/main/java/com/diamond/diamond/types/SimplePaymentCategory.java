package com.diamond.diamond.types;

public enum SimplePaymentCategory {
    CHECKOUT, LINK, FLEXIBLE;

    @Override
    public String toString() {
        return name();
    }
}
