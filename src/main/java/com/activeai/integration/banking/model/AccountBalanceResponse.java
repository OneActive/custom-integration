package com.activeai.integration.banking.model;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AccountBalanceResponse
 */
@Validated
public class AccountBalanceResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("accountBalance")
  private AccountBalance accountBalance = null;

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

  public AccountBalanceResponse accountBalance(AccountBalance accountBalance) {
    this.accountBalance = accountBalance;
    return this;
  }

  /**
   * Get accountBalance
   * 
   * @return accountBalance
   **/
  @Valid
  public AccountBalance getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(AccountBalance accountBalance) {
    this.accountBalance = accountBalance;
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
        && Objects.equals(this.accountBalance, accountBalanceResponse.accountBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, accountBalance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountBalanceResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    accountBalance: ").append(toIndentedString(accountBalance)).append("\n");
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

