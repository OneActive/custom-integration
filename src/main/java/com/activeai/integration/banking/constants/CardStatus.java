package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Account status dormant / active / closed / blocker
 */
public enum CardStatus {
  ACTIVE("ACTIVE"),

  INACTIVE("INACTIVE"),

  CLOSED("CLOSED"),

  BLOCKED("BLOCKED");

  private String value;

  CardStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CardStatus fromValue(String text) {
    for (CardStatus b : CardStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
