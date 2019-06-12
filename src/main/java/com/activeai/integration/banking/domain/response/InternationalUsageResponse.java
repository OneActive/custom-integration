package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.constants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InternationalUsageResponse extends Response{

  @JsonProperty("referenceId")
  private String referenceId;

  @JsonProperty("transactionStatus")
  private StatusEnum transactionStatus;

  @JsonProperty("isInternationalEnabled")
  private boolean isInternationalEnabled;

  public boolean getInternationalEnabled() {
    return isInternationalEnabled;
  }

  public void setInternationalEnabled(boolean internationalEnabled) {
    isInternationalEnabled = internationalEnabled;
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public StatusEnum getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(StatusEnum transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("InternationalUsageResponse{");
    sb.append("referenceId='").append(referenceId).append('\'');
    sb.append(", status=").append(transactionStatus);
    sb.append('}');
    return sb.toString();
  }
}

