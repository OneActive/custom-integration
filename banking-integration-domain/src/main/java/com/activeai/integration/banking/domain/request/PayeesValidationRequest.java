package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.domain.constants.PayeeIdTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayeesValidationRequest extends FundTransferRequest {

  /** The payee id type enum. */
  @JsonProperty("payeeIdType")
  private PayeeIdTypeEnum payeeIdType;

  public PayeeIdTypeEnum getPayeeIdType() {
    return payeeIdType;
  }

  public void setPayeeIdType(PayeeIdTypeEnum payeeIdType) {
    this.payeeIdType = payeeIdType;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("PayeesValidationRequest{");
    sb.append("payeeIdType=").append(payeeIdType);
    sb.append('}');
    return sb.toString();
  }
}
