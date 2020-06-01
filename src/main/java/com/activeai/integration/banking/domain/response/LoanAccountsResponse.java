package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.LoanAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import java.util.List;

public class LoanAccountsResponse extends Response {

  @JsonProperty("loanAccounts")
  private List<LoanAccount> loanAccounts;

  @Valid
  public List<LoanAccount> getLoanAccounts() {
    return loanAccounts;
  }

  public void setLoanAccount(List<LoanAccount> loanAccount) {
    this.loanAccounts = loanAccounts;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("loanAccounts", loanAccounts).toString();
  }
}
