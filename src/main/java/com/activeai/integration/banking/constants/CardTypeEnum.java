package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of card
 */
public enum CardTypeEnum {
  CREDIT("CREDIT"),

  DEBIT("DEBIT"),

  FOREX("FOREX");

  private String value;

  CardTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CardTypeEnum fromValue(String text) {
    for (CardTypeEnum b : CardTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
