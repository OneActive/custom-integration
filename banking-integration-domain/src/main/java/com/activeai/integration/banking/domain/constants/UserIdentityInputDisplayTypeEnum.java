package com.activeai.integration.banking.domain.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserIdentityInputDisplayTypeEnum {
    TEXT("TEXT"),

    LIST("LIST"),

    UNKNOWN("");

    private String value;

    UserIdentityInputDisplayTypeEnum(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static UserIdentityInputDisplayTypeEnum fromValue(String text) {
        for (UserIdentityInputDisplayTypeEnum b : UserIdentityInputDisplayTypeEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return UNKNOWN;
    }
}
