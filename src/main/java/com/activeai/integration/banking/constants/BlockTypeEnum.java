package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BlockTypeEnum {

    PERMANENT("PERMANENT"),

    TEMPORARY("TEMPORARY"),

    UNKNOWN("UNKNOWN");

    private String value;

    BlockTypeEnum(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static BlockTypeEnum fromValue(String text) {
        for (BlockTypeEnum b : BlockTypeEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}

