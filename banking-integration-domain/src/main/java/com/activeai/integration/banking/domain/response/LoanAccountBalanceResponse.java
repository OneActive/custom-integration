package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.domain.model.LoanAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

@Validated
public class LoanAccountBalanceResponse extends Response {

  @JsonProperty("accountSelected")
  private LoanAccount accountSelected = null;

  public LoanAccount getAccountSelected() {
    return accountSelected;
  }

  public void setAccountSelected(LoanAccount accountSelected) {
    this.accountSelected = accountSelected;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("accountSelected", accountSelected).toString();
  }
}
