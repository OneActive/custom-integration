package com.activeai.integration.banking.model;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AccountsResponse
 */
@Validated
public class AccountsResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("accounts")
  private List<Account> accounts;

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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountsResponse accountsResponse = (AccountsResponse) o;
    return Objects.equals(this.result, accountsResponse.result) && Objects.equals(this.accounts, accountsResponse.accounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, accounts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountsResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
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

