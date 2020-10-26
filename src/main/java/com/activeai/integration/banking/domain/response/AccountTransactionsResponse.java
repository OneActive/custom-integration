package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.AccountTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;


/**
 * AccountTransactionsResponse
 */
@Validated
public class AccountTransactionsResponse extends Response {

  @JsonProperty("accountTransactions")
  private List<AccountTransaction> accountTransactions;

  /**
   * Get accountTransactions
   *
   * @return accountTransactions
   **/
  @Valid
  public List<AccountTransaction> getAccountTransactions() {
    return accountTransactions;
  }

  public void setAccountTransactions(List<AccountTransaction> accountTransactions) {
    this.accountTransactions = accountTransactions;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("accountTransactions", accountTransactions).toString();
  }
}

