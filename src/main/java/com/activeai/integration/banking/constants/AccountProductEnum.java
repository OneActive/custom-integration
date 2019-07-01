package com.activeai.integration.banking.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.ArrayUtils;

public enum AccountProductEnum {

  FIXED_DEPOSIT("Fixed Deposit","Certificate of deposit"),

  HOME_LOAN("Home Loan"),

  CAR_LOAN("Car Loan"),

  PERSONAL_LOAN("Personal Loan"),

  UNKNOWN("");

  /**
   * The Product Type
   */
  private String productType;

  /**
   * synonyms for product type we are supporting
   */
  private String[] synonyms;

  AccountProductEnum(String productType,String ...synonyms) {
    this.productType = productType;
    this.synonyms = synonyms;
  }

  /**
   * get productType
   * @return
   */
  public String getProductType() {
    return productType;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(productType);
  }

  /**
   * From string.
   * @param productType
   * @return
   */
  @JsonCreator
  public static AccountProductEnum fromValue(String productType ) {
    if (productType != null) {
      for (AccountProductEnum productTypeEnum : AccountProductEnum.values()) {
        if (productType.equalsIgnoreCase(productTypeEnum.productType)) {
          return productTypeEnum;
        }

        if (productTypeEnum.synonyms != null && productTypeEnum.synonyms.length > 0) {
          if (ArrayUtils.contains(productTypeEnum.synonyms, productType)) {
            return productTypeEnum;
          }
        }
      }
    }
    return UNKNOWN;
  }

}
