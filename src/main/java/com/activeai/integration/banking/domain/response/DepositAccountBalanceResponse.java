package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.DepositAccount;
import com.activeai.integration.banking.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class DepositAccountBalanceResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("accountSelected")
  private DepositAccount accountSelected = null;

  public DepositAccountBalanceResponse result(Result result) {
    this.result = result;
    return this;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public DepositAccount getAccountSelected() {
    return accountSelected;
  }

  public void setAccountSelected(DepositAccount accountSelected) {
    this.accountSelected = accountSelected;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("DepositAccountBalanceResponse{");
    sb.append("result=").append(result);
    sb.append(", accountSelected=").append(accountSelected);
    sb.append('}');
    return sb.toString();
  }
}
