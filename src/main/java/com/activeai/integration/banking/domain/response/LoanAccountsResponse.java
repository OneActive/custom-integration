package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.LoanAccount;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.List;

public class LoanAccountsResponse extends Response{

  @JsonProperty("loanAccounts")
  private List<LoanAccount> loanAccounts;

  @Valid
  public List<LoanAccount> getLoanAccounts() {
    return loanAccounts;
  }

  public void setLoanAccount(List<LoanAccount> loanAccount) {
    this.loanAccounts = loanAccounts;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("LoanAccountsResponse{");
    sb.append("loanAccounts=").append(loanAccounts);
    sb.append('}');
    return sb.toString();
  }
}
