package com.diamond.diamond.types;

public enum SimplePaymentCategory {
    CHECKOUT, LINK;

    @Override
    public String toString() {
        return name();
    }
}
