package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.annotations.Beta;


/**
 * account status - active, inactive
 */
public enum PayeeTypeEnum {
  INTERNAL_DOMESTIC("INTERNAL_DOMESTIC"),

  EXTERNAL_DOMESTIC("EXTERNAL_DOMESTIC"),

  SELF("SELF"),

  CREDIT_CARD("CREDIT_CARD"),

  UNKNOWN("");

  private String value;

  PayeeTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayeeTypeEnum fromValue(String text) {
    for (PayeeTypeEnum b : PayeeTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return UNKNOWN;
  }
}
