package com.activeai.integration.banking.domain.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReplaceTypeEnum {

  REPLACE("replace"),
  BLOCKANDREPLACE("blockAndReplace"),
  UNKNOWN("unknown");

  private String value;

  ReplaceTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ReplaceTypeEnum fromValue(String text) {
    for (ReplaceTypeEnum b : ReplaceTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return UNKNOWN;
  }

}
