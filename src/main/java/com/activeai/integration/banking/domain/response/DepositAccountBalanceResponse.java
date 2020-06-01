package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.DepositAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

@Validated
public class DepositAccountBalanceResponse extends Response {

  @JsonProperty("accountSelected")
  private DepositAccount accountSelected = null;

  public DepositAccount getAccountSelected() {
    return accountSelected;
  }

  public void setAccountSelected(DepositAccount accountSelected) {
    this.accountSelected = accountSelected;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("accountSelected", accountSelected).toString();
  }
}
