package com.activeai.integration.banking.domain.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of Phone number i.e. work/home/office
 */
public enum PhoneTypeEnum {
  PRIMARY("PRIMARY"),

  OFFICE("OFFICE"),

  HOME("HOME"),

  UNKNOWN("");

  private String value;

  PhoneTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PhoneTypeEnum fromValue(String text) {
    for (PhoneTypeEnum b : PhoneTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return UNKNOWN;
  }

}
