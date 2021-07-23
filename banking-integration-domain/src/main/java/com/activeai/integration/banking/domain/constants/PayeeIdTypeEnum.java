package com.activeai.integration.banking.domain.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The Enum PayeeIdTypeEnum.
 */
public enum PayeeIdTypeEnum {

  /**
   * The account number.
   */
  ACCOUNT_NUMBER("ACCOUNT_NUMBER"),
  /**
   * The email id.
   */
  EMAIL_ID("EMAIL_ID"),
  /**
   * The mmid.
   */
  MMID("MMID"),
  /**
   * The mobile number.
   */
  MOBILE_NUMBER("MOBILE_NUMBER"),
  /**
   * The card number.
   */
  CARD_NUMBER("CARD_NUMBER"),

  UNKNOWN("");


  private String value;

  PayeeIdTypeEnum(String value) {
    this.value = value;
  }

  @JsonCreator public static PayeeIdTypeEnum fromValue(String text) {
    for (PayeeIdTypeEnum b : PayeeIdTypeEnum.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return UNKNOWN;
  }
}
