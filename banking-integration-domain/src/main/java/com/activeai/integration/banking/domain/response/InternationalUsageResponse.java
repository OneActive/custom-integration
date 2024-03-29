package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.domain.constants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class InternationalUsageResponse extends Response {

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

  public StatusEnum getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(StatusEnum transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("transactionStatus", transactionStatus).append("isInternationalEnabled", isInternationalEnabled)
        .toString();
  }
}

