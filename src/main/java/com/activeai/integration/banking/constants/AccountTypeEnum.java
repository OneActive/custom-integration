package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of account
 */
public enum AccountTypeEnum {
  SAVINGS("SAVINGS"),

  CHECKING("CHECKING"),

  CURRENT("CURRENT"),

  LOAN("LOAN"),

  DEPOSIT("DEPOSIT");

  private String value;

  AccountTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AccountTypeEnum fromValue(String text) {
    for (AccountTypeEnum b : AccountTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
