package com.diamond.diamond;

public enum Blockchain {
    ETH, SOL, BASE, TRON;

    @Override 
    public String toString() {
        return name();
    }
}