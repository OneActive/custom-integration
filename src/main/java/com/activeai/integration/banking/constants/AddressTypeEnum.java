package com.activeai.integration.banking.constants;

import com.activeai.integration.banking.model.Address;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Address type like primary, office, other etc.
 */
public enum AddressTypeEnum {
  PRIMARY("PRIMARY"),

  OFFICE("OFFICE");

  private String value;

  AddressTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AddressTypeEnum fromValue(String text) {
    for (AddressTypeEnum b : AddressTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
