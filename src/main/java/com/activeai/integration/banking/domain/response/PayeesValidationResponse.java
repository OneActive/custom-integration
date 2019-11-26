package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.constants.StatusEnum;
import com.activeai.integration.banking.model.Payee;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayeesValidationResponse extends Response {

  /** This is valid payee. */
  @JsonProperty("validPayee")
  private Boolean validPayee =false;

  /* Success, Failure, Pending */
  @JsonProperty("transactionStatus")
  private StatusEnum transactionStatus=null;

  /**
   *
   * @return validPayee
   */
  public Boolean getValidPayee() {
    return validPayee;
  }
  /**
   *
   * @param validPayee
   */

  public void setValidPayee(Boolean validPayee) {
    this.validPayee = validPayee;
  }
  /**
   *
   * @return transactionStatus
   */
  public StatusEnum getTransactionStatus() {
    return transactionStatus;
  }

  /**
   *
   * @param transactionStatus
   */
  public void setTransactionStatus(StatusEnum transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("PayeesValidationResponse{");
    sb.append("validPayee=").append(validPayee);
    sb.append(", transactionStatus=").append(transactionStatus);
    sb.append('}');
    return sb.toString();
  }
}
