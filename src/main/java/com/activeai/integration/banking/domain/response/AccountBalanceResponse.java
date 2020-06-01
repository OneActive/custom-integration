package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * CasaAccountBalanceResponse
 */
@Validated
public class AccountBalanceResponse extends Response {

  @JsonProperty("accountSelected")
  private Account accountSelected = null;

  public Account getAccountSelected() {
    return accountSelected;
  }

  public void setAccountSelected(Account accountSelected) {
    this.accountSelected = accountSelected;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("accountSelected", accountSelected).toString();
  }
}

