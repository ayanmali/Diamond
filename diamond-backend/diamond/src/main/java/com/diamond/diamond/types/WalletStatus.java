package com.diamond.diamond.types;

public enum WalletStatus {
    ACTIVE, ARCHIVED;

    @Override
    public String toString() {
        return name();
    }
    
}
