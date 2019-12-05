package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PayeeExistsTypeEnum {

  /**
   * The existing payee.
   */

  EXISTING_PAYEE("EXISTING_PAYEE"),
  /**
   * The adhoc payee.
   */
  ADHOC_PAYEE("ADHOC_PAYEE");
  private String value;

  PayeeExistsTypeEnum(String value) {
    this.value = value;
  }

  @Override @JsonValue public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator public static PayeeExistsTypeEnum fromValue(String text) {
    for (PayeeExistsTypeEnum b : PayeeExistsTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
