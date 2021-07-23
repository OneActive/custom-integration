package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.domain.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * AccountsResponse
 */
@Validated
public class AccountsResponse extends Response {

  @JsonProperty("accounts")
  private List<Account> accounts;

  /**
   * Get accounts
   * 
   * @return accounts
   **/
  @Valid
  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("accounts", accounts).toString();
  }
}

