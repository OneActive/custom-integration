package com.activeai.integration.banking.model;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AccountDetailResponse
 */
@Validated
public class AccountDetailResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("accountDetail")
  private Account accountDetail = null;

  public AccountDetailResponse result(Result result) {
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

  public AccountDetailResponse accountDetail(Account accountDetail) {
    this.accountDetail = accountDetail;
    return this;
  }

  /**
   * Get accountDetail
   * 
   * @return accountDetail
   **/
  @Valid
  public Account getAccountDetail() {
    return accountDetail;
  }

  public void setAccountDetail(Account accountDetail) {
    this.accountDetail = accountDetail;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountDetailResponse accountDetailResponse = (AccountDetailResponse) o;
    return Objects.equals(this.result, accountDetailResponse.result)
        && Objects.equals(this.accountDetail, accountDetailResponse.accountDetail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, accountDetail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountDetailResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    accountDetail: ").append(toIndentedString(accountDetail)).append("\n");
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

