package com.activeai.integration.banking.domain.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionTypeEnum {
  DEBIT("DEBIT"),

  CREDIT("CREDIT"),

  UNKNOWN("");

  private String value;

  TransactionTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TransactionTypeEnum fromValue(String text) {
    for (TransactionTypeEnum b : TransactionTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return UNKNOWN;
  }

}
