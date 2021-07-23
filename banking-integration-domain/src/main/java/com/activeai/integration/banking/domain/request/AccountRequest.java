package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.domain.constants.AccountTypeEnum;
import com.activeai.integration.banking.domain.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest extends User {

  @JsonProperty("accountId")
  private String accountId;

  @JsonProperty("accountType")
  private AccountTypeEnum accountType;

  @JsonProperty("loginId")
  private String loginId;

  @JsonProperty("accountNumber")
  private String accountNumber;

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public AccountTypeEnum getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("AccountRequest{");
    sb.append("accountId='").append(accountId).append('\'');
    sb.append(", accountType=").append(accountType);
    sb.append(", loginId='").append(loginId).append('\'');
    sb.append(", accountNumber='").append(accountNumber).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
