package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.domain.model.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * AccountDetailResponse
 */
@Validated
public class AccountDetailResponse extends Response {

  @JsonProperty("accountDetails")
  private Account accountDetails = null;

  public AccountDetailResponse accountDetail(Account accountDetail) {
    this.accountDetails = accountDetail;
    return this;
  }

  /**
   * Get accountDetails
   * 
   * @return accountDetails
   **/
  @Valid
  public Account getAccountDetails() {
    return accountDetails;
  }

  public void setAccountDetails(Account accountDetails) {
    this.accountDetails = accountDetails;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("accountDetails", accountDetails).toString();
  }
}

