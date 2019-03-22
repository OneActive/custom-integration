package com.activeai.integration.banking.model;

import java.util.Date;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AccountTransactions
 */
@Validated
public class AccountTransactions {

  @JsonProperty("txnDate")
  private Date txnDate = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("currency")
  private String currency = null;

  @JsonProperty("isDebit")
  private Boolean isDebit = null;

  @JsonProperty("foreignTxnAmount")
  private Double foreignTxnAmount = null;

  @JsonProperty("foreignTxnCurrency")
  private String foreignTxnCurrency = null;

  @JsonProperty("foreignTxnExchangeRate")
  private Float foreignTxnExchangeRate = null;

  @JsonProperty("referenceId")
  private String referenceId = null;

  public AccountTransactions txnDate(Date txnDate) {
    this.txnDate = txnDate;
    return this;
  }

  /**
   * Transaction Date
   * 
   * @return txnDate
   **/
  public Date getTxnDate() {
    return txnDate;
  }

  public void setTxnDate(Date txnDate) {
    this.txnDate = txnDate;
  }

  public AccountTransactions description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Transaction detail
   * 
   * @return description
   **/
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AccountTransactions category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Transaction category
   * 
   * @return category
   **/
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public AccountTransactions amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Transaction Amount
   * 
   * @return amount
   **/
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public AccountTransactions currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Currency code at which the transaction is made
   * 
   * @return currency
   **/
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public AccountTransactions isDebit(Boolean isDebit) {
    this.isDebit = isDebit;
    return this;
  }

  /**
   * Is debit or credit
   * 
   * @return isDebit
   **/
  public Boolean isIsDebit() {
    return isDebit;
  }

  public void setIsDebit(Boolean isDebit) {
    this.isDebit = isDebit;
  }

  public AccountTransactions foreignTxnAmount(Double foreignTxnAmount) {
    this.foreignTxnAmount = foreignTxnAmount;
    return this;
  }

  /**
   * Transaction amount in foreign currency
   * 
   * @return foreignTxnAmount
   **/
  public Double getForeignTxnAmount() {
    return foreignTxnAmount;
  }

  public void setForeignTxnAmount(Double foreignTxnAmount) {
    this.foreignTxnAmount = foreignTxnAmount;
  }

  public AccountTransactions foreignTxnCurrency(String foreignTxnCurrency) {
    this.foreignTxnCurrency = foreignTxnCurrency;
    return this;
  }

  /**
   * Currency code at which the transaction is made
   * 
   * @return foreignTxnCurrency
   **/
  public String getForeignTxnCurrency() {
    return foreignTxnCurrency;
  }

  public void setForeignTxnCurrency(String foreignTxnCurrency) {
    this.foreignTxnCurrency = foreignTxnCurrency;
  }

  public AccountTransactions foreignTxnExchangeRate(Float foreignTxnExchangeRate) {
    this.foreignTxnExchangeRate = foreignTxnExchangeRate;
    return this;
  }

  /**
   * Exchange rate from source currency to foreign currency
   * 
   * @return foreignTxnExchangeRate
   **/
  public Float getForeignTxnExchangeRate() {
    return foreignTxnExchangeRate;
  }

  public void setForeignTxnExchangeRate(Float foreignTxnExchangeRate) {
    this.foreignTxnExchangeRate = foreignTxnExchangeRate;
  }

  public AccountTransactions referenceId(String referenceId) {
    this.referenceId = referenceId;
    return this;
  }

  /**
   * Transaction Reference Id
   * 
   * @return referenceId
   **/
  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountTransactions accountTransactions = (AccountTransactions) o;
    return Objects.equals(this.txnDate, accountTransactions.txnDate) && Objects.equals(this.description, accountTransactions.description)
        && Objects.equals(this.category, accountTransactions.category) && Objects.equals(this.amount, accountTransactions.amount)
        && Objects.equals(this.currency, accountTransactions.currency) && Objects.equals(this.isDebit, accountTransactions.isDebit)
        && Objects.equals(this.foreignTxnAmount, accountTransactions.foreignTxnAmount)
        && Objects.equals(this.foreignTxnCurrency, accountTransactions.foreignTxnCurrency)
        && Objects.equals(this.foreignTxnExchangeRate, accountTransactions.foreignTxnExchangeRate)
        && Objects.equals(this.referenceId, accountTransactions.referenceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(txnDate, description, category, amount, currency, isDebit, foreignTxnAmount, foreignTxnCurrency,
        foreignTxnExchangeRate, referenceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountTransactions {\n");

    sb.append("    txnDate: ").append(toIndentedString(txnDate)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    isDebit: ").append(toIndentedString(isDebit)).append("\n");
    sb.append("    foreignTxnAmount: ").append(toIndentedString(foreignTxnAmount)).append("\n");
    sb.append("    foreignTxnCurrency: ").append(toIndentedString(foreignTxnCurrency)).append("\n");
    sb.append("    foreignTxnExchangeRate: ").append(toIndentedString(foreignTxnExchangeRate)).append("\n");
    sb.append("    referenceId: ").append(toIndentedString(referenceId)).append("\n");
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

