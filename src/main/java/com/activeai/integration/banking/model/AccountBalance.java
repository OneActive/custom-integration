package com.activeai.integration.banking.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AccountBalance
 */
@Validated
public class AccountBalance {

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("availableBalance")
  private Double availableBalance = null;

  @JsonProperty("currentBalance")
  private Double currentBalance = null;

  @JsonProperty("currencyCode")
  private String currencyCode = null;

  public AccountBalance amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Amount
   * 
   * @return amount
   **/
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public AccountBalance availableBalance(Double availableBalance) {
    this.availableBalance = availableBalance;
    return this;
  }

  /**
   * Available balance
   * 
   * @return availableBalance
   **/
  public Double getAvailableBalance() {
    return availableBalance;
  }

  public void setAvailableBalance(Double availableBalance) {
    this.availableBalance = availableBalance;
  }

  public AccountBalance currentBalance(Double currentBalance) {
    this.currentBalance = currentBalance;
    return this;
  }

  /**
   * current balance
   * 
   * @return currentBalance
   **/
  public Double getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(Double currentBalance) {
    this.currentBalance = currentBalance;
  }

  public AccountBalance currencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  /**
   * currency code
   * 
   * @return currencyCode
   **/
  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountBalance accountBalance = (AccountBalance) o;
    return Objects.equals(this.amount, accountBalance.amount) && Objects.equals(this.availableBalance, accountBalance.availableBalance)
        && Objects.equals(this.currentBalance, accountBalance.currentBalance)
        && Objects.equals(this.currencyCode, accountBalance.currencyCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, availableBalance, currentBalance, currencyCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountBalance {\n");

    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    availableBalance: ").append(toIndentedString(availableBalance)).append("\n");
    sb.append("    currentBalance: ").append(toIndentedString(currentBalance)).append("\n");
    sb.append("    currencyCode: ").append(toIndentedString(currencyCode)).append("\n");
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

