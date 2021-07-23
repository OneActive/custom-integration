package com.activeai.integration.banking.domain.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.ArrayUtils;

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
  BLOCKED_TEMPORARY("BLOCKED_TEMPORARY","TEMPORARY"),
  /** The Hot. */
  BLOCKED_PERMANENT("BLOCKED_PERMANENT","PERMANENT"),
  /** The Expired. */
  EXPIRED("EXPIRED"),
  /** The Cancelled. */
  CANCELLED("CANCELLED"),
  /** Closed */
  CLOSED("CLOSED"),
  /** Unkown. */
  UNKNOWN("");

  private String value;

  /**
   * synonyms for product type we are supporting
   */
  private String[] synonyms;

  CardStatusEnum(String cardStatus,String ...synonyms) {
    this.value = cardStatus;
    this.synonyms = synonyms;
  }

  CardStatusEnum(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CardStatusEnum fromValue(String cardStatus) {
    if (cardStatus != null) {
      for (CardStatusEnum cardStatusEnum : CardStatusEnum.values()) {
        if (cardStatus.equalsIgnoreCase(cardStatusEnum.value)) {
          return cardStatusEnum;
        }

        if (cardStatusEnum.synonyms != null && cardStatusEnum.synonyms.length > 0 && ArrayUtils
            .contains(cardStatusEnum.synonyms, cardStatus)) {
          return cardStatusEnum;

        }
      }
    }
    return UNKNOWN;
  }
}
