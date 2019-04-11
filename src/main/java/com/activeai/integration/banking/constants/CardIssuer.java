package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Card issues like visa, master card, amex, Diners
 */
public enum CardIssuer {
  VISA("Visa"),

  MASTER_CARD("Master Card"),

  AMEX("AMEX"),

  DINERS("Diners");

  private String value;

  CardIssuer(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CardIssuer fromValue(String text) {
    for (CardIssuer b : CardIssuer.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
