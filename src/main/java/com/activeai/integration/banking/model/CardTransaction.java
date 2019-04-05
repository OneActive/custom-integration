package com.activeai.integration.banking.model;

import java.util.Date;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CardTransaction
 */
@Validated
public class CardTransaction {

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
    CardTransaction cardTransaction = (CardTransaction) o;
    return Objects.equals(this.txnDate, cardTransaction.txnDate) && Objects.equals(this.description, cardTransaction.description)
        && Objects.equals(this.category, cardTransaction.category) && Objects.equals(this.amount, cardTransaction.amount)
        && Objects.equals(this.currency, cardTransaction.currency) && Objects.equals(this.isDebit, cardTransaction.isDebit)
        && Objects.equals(this.foreignTxnAmount, cardTransaction.foreignTxnAmount)
        && Objects.equals(this.foreignTxnCurrency, cardTransaction.foreignTxnCurrency)
        && Objects.equals(this.foreignTxnExchangeRate, cardTransaction.foreignTxnExchangeRate)
        && Objects.equals(this.referenceId, cardTransaction.referenceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(txnDate, description, category, amount, currency, isDebit, foreignTxnAmount, foreignTxnCurrency,
        foreignTxnExchangeRate, referenceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardTransaction {\n");

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

