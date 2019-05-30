package com.activeai.integration.banking.domain.response;

import java.util.Objects;

import javax.validation.Valid;

import com.activeai.integration.banking.model.Account;
import com.activeai.integration.banking.model.Result;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CasaAccountBalanceResponse
 */
@Validated
public class AccountBalanceResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("accountSelected")
  private Account accountSelected = null;

  public AccountBalanceResponse result(Result result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * 
   * @return result
   **/
  @Valid
  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public Account getAccountSelected() {
    return accountSelected;
  }

  public void setAccountSelected(Account accountSelected) {
    this.accountSelected = accountSelected;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountBalanceResponse accountBalanceResponse = (AccountBalanceResponse) o;
    return Objects.equals(this.result, accountBalanceResponse.result)
        && Objects.equals(this.accountSelected, accountBalanceResponse.accountSelected);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, accountSelected);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountBalanceResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    accountSelected: ").append(toIndentedString(accountSelected)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

