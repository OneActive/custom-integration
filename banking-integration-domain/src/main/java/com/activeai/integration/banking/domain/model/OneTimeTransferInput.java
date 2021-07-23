package com.activeai.integration.banking.domain.model;

import com.activeai.integration.banking.domain.constants.PayeeTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OneTimeTransferInput {

  /* Internal payee, external payee, adhoc payee */
  @JsonProperty("payeeType")
  private PayeeTypeEnum payeeType;

  /* Same Bank Payee, Other Bank Payee */
  @JsonProperty("displayPayeeType")
  private String displayPayeeType;

  @JsonProperty("userIdentityInputs")
  private List<UserIdentityInput> userIdentityInputs;

  public PayeeTypeEnum getPayeeType() {
    return payeeType;
  }

  public void setPayeeType(PayeeTypeEnum payeeType) {
    this.payeeType = payeeType;
  }

  /**
   *
   * @return displayPayeeType
   */
  public String getDisplayPayeeType() {
    return displayPayeeType;
  }

  /**
   *
   * @param displayPayeeType
   */
  public void setDisplayPayeeType(String displayPayeeType) {
    this.displayPayeeType = displayPayeeType;
  }

  /**
   *
   * @return  userIdentityInputs
   */
  public List<UserIdentityInput> getUserIdentityInputs() {
    return userIdentityInputs;
  }

  /**
   *
   * @param userIdentityInputs
   */
  public void setUserIdentityInputs(List<UserIdentityInput> userIdentityInputs) {
    this.userIdentityInputs = userIdentityInputs;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("OneTimeTransferInput{");
    sb.append("payeeType").append(payeeType);
    sb.append(", displayPayeeType='").append(displayPayeeType).append('\'');
    sb.append(", userIdentityInputs=").append(userIdentityInputs);
    sb.append('}');
    return sb.toString();
  }
}
