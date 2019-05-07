package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of EMAIL ID i.e. PRIMARY/SECONDARY
 */
public enum EmailTypeEnum {

  PRIMARY("PRIMARY"),

  SECONDARY("SECONDARY");

  private String value;

  EmailTypeEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static EmailTypeEnum fromValue(String text) {
    for (EmailTypeEnum b : EmailTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

}
