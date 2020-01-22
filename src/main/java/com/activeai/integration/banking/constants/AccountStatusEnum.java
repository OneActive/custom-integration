package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Account status dormant / active / closed
 */

public enum AccountStatusEnum {
  /** The new account. */
  NEW_ACCOUNT("NEW_ACCOUNT"),
  /** The active. */
  ACTIVE("ACTIVE"),
  /** The closed. */
  CLOSED("CLOSED"),
  /** The dormant. */
  DORMANT("CLOSED"),
  /** The no debit. */
  NO_DEBIT("NO_DEBIT"),
  /** The no credit. */
  NO_CREDIT("NO_CREDIT"),
  /** The unknown. */
  UNKNOWN(""),
  /** The open. */
  OPEN("OPEN");

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
    return UNKNOWN;
  }
}
