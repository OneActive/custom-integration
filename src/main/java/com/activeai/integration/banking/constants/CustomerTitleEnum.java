package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerTitleEnum {
  MR_("Mr."),

  MRS_("Mrs."),

  MS_("Ms."),

  MISS_("Miss."),

  DR_("Dr.");

  private String value;

  CustomerTitleEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CustomerTitleEnum fromValue(String text) {
    for (CustomerTitleEnum b : CustomerTitleEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
