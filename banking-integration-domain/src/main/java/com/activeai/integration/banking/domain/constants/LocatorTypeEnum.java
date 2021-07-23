package com.activeai.integration.banking.domain.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LocatorTypeEnum {
  /* ATM */
  ATM("atm"),

  /* Brach */
  BRANCH("branch"),

  UNKNOWN("");

  private String value;

  LocatorTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static LocatorTypeEnum fromValue(String text) {
    for (LocatorTypeEnum b : LocatorTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return UNKNOWN;
  }
}

