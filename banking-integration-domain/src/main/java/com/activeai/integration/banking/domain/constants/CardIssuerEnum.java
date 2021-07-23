package com.activeai.integration.banking.domain.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Card issues like visa, master card, amex, Diners
 */
public enum CardIssuerEnum {
  VISA("VISA"),

  MASTER("MASTER"),

  AMEX("AMEX"),

  DINERS("DINERS"),

  UNKNOWN("UNKNOWN");

  private String value;

  CardIssuerEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CardIssuerEnum fromValue(String text) {
    for (CardIssuerEnum b : CardIssuerEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return UNKNOWN;
  }
}
