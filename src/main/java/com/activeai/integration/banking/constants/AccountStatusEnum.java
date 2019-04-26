package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Account status dormant / active / closed
 */

public enum AccountStatusEnum {
  ACTIVE("ACTIVE"),

  INACTIVE("INACTIVE"),

  CLOSED("CLOSED");

  private String value;

  AccountStatusEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AccountStatusEnum fromValue(String text) {
    for (AccountStatusEnum b : AccountStatusEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
