package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.constants.StatusEnum;
import com.activeai.integration.banking.model.Payee;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
    return new ToStringBuilder(this).append("validPayee", validPayee).append("transactionStatus", transactionStatus).toString();
  }
}
