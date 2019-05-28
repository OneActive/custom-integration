package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum  StatusEnum {

  SUCCESS("SUCCESS"),
  FAILURE("FAILURE"),
  PENDING("PENDING");

  private String value;

  StatusEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static StatusEnum fromValue(String text) {
    for (StatusEnum b : StatusEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
