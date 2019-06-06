package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.DepositAccount;
import com.activeai.integration.banking.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class DepositAccountDetailResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("accountDetails")
  private DepositAccount accountDetails = null;

  public DepositAccountDetailResponse result(Result result) {
    this.result = result;
    return this;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public DepositAccount getAccountDetails() {
    return accountDetails;
  }

  public void setAccountDetails(DepositAccount accountDetails) {
    this.accountDetails = accountDetails;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("DepositAccountDetailResponse{");
    sb.append("result=").append(result);
    sb.append(", accountDetails=").append(accountDetails);
    sb.append('}');
    return sb.toString();
  }
}
