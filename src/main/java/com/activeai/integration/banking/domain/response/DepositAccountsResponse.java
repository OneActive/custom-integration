package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.DepositAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public class DepositAccountsResponse extends Response {

  @JsonProperty("depositAccounts")
 private List<DepositAccount> depositAccounts;

  @Valid
  public List<DepositAccount> getDepositAccounts() {
    return depositAccounts;
  }

  public void setDepositAccounts(List<DepositAccount> depositAccounts) {
    this.depositAccounts = depositAccounts;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("depositAccounts", depositAccounts).toString();
  }
}
