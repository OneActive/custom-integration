package com.activeai.integration.banking.domain.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerTitleEnum {
  MR("Mr."),

  MRS("Mrs."),

  MS("Ms."),

  MISS("Miss."),

  DR("Dr."),

  UNKNOWN("");

  private String value;

  CustomerTitleEnum(String value) {
    this.value = value;
  }

  @Override @JsonValue public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator public static CustomerTitleEnum fromValue(String text) {
    for (CustomerTitleEnum b : CustomerTitleEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return UNKNOWN;
  }
}
