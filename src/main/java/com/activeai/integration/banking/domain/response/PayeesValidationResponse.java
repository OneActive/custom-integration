package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.constants.StatusEnum;
import com.activeai.integration.banking.model.Payee;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayeesValidationResponse extends Response {

  /** This is valid payee. */
  @JsonProperty("isValidPayee")
  private boolean isValidPayee;

  /* Success, Failure, Pending */
  @JsonProperty("transactionStatus")
  private StatusEnum transactionStatus=null;

  /** The payee details. */
  private Payee payeeDetails;
  /**
   *
   * @return isValidPayee
   */
  public boolean isValidPayee() {
    return isValidPayee;
  }

  /**
   *
   * @param validPayee
   */
  public void setValidPayee(boolean validPayee) {
    isValidPayee = validPayee;
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

  /**
   *
   * @return payeeDetails
   */
  public Payee getPayeeDetails() {
    return payeeDetails;
  }

  /**
   *
   * @param payeeDetails
   */
  public void setPayeeDetails(Payee payeeDetails) {
    this.payeeDetails = payeeDetails;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("PayeesValidationResponse{");
    sb.append("isValidPayee=").append(isValidPayee);
    sb.append(", transactionStatus=").append(transactionStatus);
    sb.append(", payeeDetails=").append(payeeDetails);
    sb.append('}');
    return sb.toString();
  }
}
