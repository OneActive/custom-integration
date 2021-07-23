package com.activeai.integration.banking.domain.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * account status - active, inactive
 */

public enum PayeeStatusEnum {
  ACTIVE("ACTIVE"),

  INACTIVE("INACTIVE"),

  UNKNOWN("");

  private String value;

  PayeeStatusEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayeeStatusEnum fromValue(String text) {
    for (PayeeStatusEnum b : PayeeStatusEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
