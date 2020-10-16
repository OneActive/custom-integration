package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.DepositAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

@Validated
public class DepositAccountDetailResponse extends Response {

  @JsonProperty("accountDetails")
  private DepositAccount accountDetails = null;

  public DepositAccount getAccountDetails() {
    return accountDetails;
  }

  public void setAccountDetails(DepositAccount accountDetails) {
    this.accountDetails = accountDetails;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("accountDetails", accountDetails).toString();
  }
}
