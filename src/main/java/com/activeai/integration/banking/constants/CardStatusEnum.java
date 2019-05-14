package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Account status dormant / active / closed / blocker
 */
public enum CardStatusEnum {
  /** Issued. */
  ISSUED("ISSUED"),
  /** Not Issued. */
  NOT_ISSUED("NOT_ISSUED"),
  /** The active. */
  ACTIVE("ACTIVE"),
  /** The Blocked. */
  BLOCKED_TEMPORARY("BLOCKED_TEMPORARY"),
  /** The Hot. */
  BLOCKED_PERMANENT("BLOCKED_PERMANENT"),
  /** The Expired. */
  EXPIRED("EXPIRED"),
  /** The Cancelled. */
  CANCELLED("CANCELLED"),
  /** Closed */
  CLOSED("CLOSED"),
  /** Unkown. */
  UNKNOWN("UNKNOWN");

  private String value;

  CardStatusEnum(String value) {
    this.value = value;
  }

  CardStatusEnum() {

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
