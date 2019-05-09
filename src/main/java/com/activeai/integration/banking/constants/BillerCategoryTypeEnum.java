package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BillerCategoryTypeEnum {

    REGISTERED("REGISTERED"),

    ADHOC("ADHOC");

    private String value;

    BillerCategoryTypeEnum(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static BillerCategoryTypeEnum fromValue(String text) {
        for (BillerCategoryTypeEnum b : BillerCategoryTypeEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
