package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * account status - active, inactive
 */
public enum PayeeType {
  INTERNAL_DOMESTIC("INTERNAL_DOMESTIC"),

  EXTERNAL_DOMESTIC("EXTERNAL_DOMESTIC"),

  WALLET("WALLET");

  private String value;

  PayeeType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayeeType fromValue(String text) {
    for (PayeeType b : PayeeType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
