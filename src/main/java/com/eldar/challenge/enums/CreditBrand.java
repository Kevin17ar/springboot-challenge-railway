package com.eldar.challenge.enums;

public enum CreditBrand {
    VISA("VISA"),
    NARA("NARA"),
    AMEX("AMEX");

    public final String label;

    private CreditBrand(String label) {
        this.label = label;
    }
}
