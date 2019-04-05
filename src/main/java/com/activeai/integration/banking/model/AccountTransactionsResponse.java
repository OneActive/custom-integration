package com.activeai.integration.banking.model;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * AccountTransactionsResponse
 */
@Validated
public class AccountTransactionsResponse {
  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("accountTransactions")
  private List<AccountTransaction> accountTransactions;

  public AccountTransactionsResponse result(Result result) {
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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountTransactionsResponse accountTransactionsResponse = (AccountTransactionsResponse) o;
    return Objects.equals(this.result, accountTransactionsResponse.result)
        && Objects.equals(this.accountTransactions, accountTransactionsResponse.accountTransactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, accountTransactions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountTransactionsResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    accountTransactions: ").append(toIndentedString(accountTransactions)).append("\n");
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

