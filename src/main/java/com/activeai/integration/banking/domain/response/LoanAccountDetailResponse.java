package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.LoanAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

@Validated
public class LoanAccountDetailResponse extends Response {

  @JsonProperty("accountDetails")
  private LoanAccount accountDetails = null;

  public LoanAccount getAccountDetails() {
    return accountDetails;
  }

  public void setAccountDetails(LoanAccount accountDetails) {
    this.accountDetails = accountDetails;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("accountDetails", accountDetails).toString();
  }
}
