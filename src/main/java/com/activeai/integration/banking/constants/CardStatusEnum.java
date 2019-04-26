package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Account status dormant / active / closed / blocker
 */
public enum CardStatusEnum {
  ACTIVE("ACTIVE"),

  INACTIVE("INACTIVE"),

  CLOSED("CLOSED"),

  BLOCKED("BLOCKED");

  private String value;

  CardStatusEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CardStatusEnum fromValue(String text) {
    for (CardStatusEnum b : CardStatusEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
