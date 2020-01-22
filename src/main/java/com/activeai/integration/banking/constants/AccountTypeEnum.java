package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of account
 */
public enum AccountTypeEnum {
  SAVINGS("SAVINGS"),

  CHECKING("CHECKING"),

  LOAN("LOAN"),

  DEPOSIT("DEPOSIT"),

  /** The investment. */
  INVESTMENT("INVESTMENT"),

  /** The bill. */
  BILL("BILL"),

  /** The tax. */
  TAX("TAX"),

  /** The insurance. */
  INSURANCE("INSURANCE"),

  /** The reward. */
  REWARD("REWARD"),

  /** The miles. */
  MILES("MILES"),

  /** The over draft. */
  OVER_DRAFT("OVER_DRAFT"),

  /** The unknown. */
  UNKNOWN("");

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
    return UNKNOWN;
  }
}
