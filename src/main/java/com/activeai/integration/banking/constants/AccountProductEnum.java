package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountProductEnum {

  FIXED_DEPOSIT("Fixed Deposit"),

  TERM_DEPOSIT("Term Deposit"),

  HOME_LOAN("Home Loan"),

  CAR_LOAN("Car Loan"),

  PERSONAL_LOAN("Personal Loan");

  private String value;

  AccountProductEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AccountProductEnum fromValue(String text) {
    for (AccountProductEnum b : AccountProductEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

}
