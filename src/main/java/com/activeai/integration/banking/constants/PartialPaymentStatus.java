package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PartialPaymentStatus {
    ALLOWED("ALLOWED"),

    NOT_ALLOWED("NOT_ALLOWED"),

    UNKNOWN("");

    private String value;

    PartialPaymentStatus(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static PartialPaymentStatus fromValue(String text) {
        for (PartialPaymentStatus b : PartialPaymentStatus.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return UNKNOWN;
    }
}
