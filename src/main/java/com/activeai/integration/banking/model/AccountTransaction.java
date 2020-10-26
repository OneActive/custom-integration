package com.activeai.integration.banking.model;

import com.activeai.integration.banking.constants.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

/**
 * AccountTransaction
 */
@Validated
public class AccountTransaction {

  @JsonProperty("transactionId")
  private String transactionId;

  @JsonProperty("accountNumber")
  private String accountNumber;

  @JsonProperty("accountId")
  private String accountId;

  @JsonProperty("txnDate")
  private String txnDate = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("currency")
  private String currency = null;

  @JsonProperty("transactionType")
  private TransactionTypeEnum transactionType = null;

  @JsonProperty("foreignTxnAmount")
  private Double foreignTxnAmount = null;

  @JsonProperty("foreignTxnCurrency")
  private String foreignTxnCurrency = null;

  @JsonProperty("foreignTxnExchangeRate")
  private Float foreignTxnExchangeRate = null;

  @JsonProperty("referenceId")
  private String referenceId = null;

  @JsonProperty("merchantName")
  private String merchantName;

  @JsonProperty("categoryType")
  private String categoryType;

  @JsonProperty("categorySubType")
  private String categorySubType;

  @JsonProperty("additionalProperties")
  private Map<String,Object> additionalProperties;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getTxnDate() {
    return txnDate;
  }

  public void setTxnDate(String txnDate) {
    this.txnDate = txnDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public TransactionTypeEnum getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
  }

  public Double getForeignTxnAmount() {
    return foreignTxnAmount;
  }

  public void setForeignTxnAmount(Double foreignTxnAmount) {
    this.foreignTxnAmount = foreignTxnAmount;
  }

  public String getForeignTxnCurrency() {
    return foreignTxnCurrency;
  }

  public void setForeignTxnCurrency(String foreignTxnCurrency) {
    this.foreignTxnCurrency = foreignTxnCurrency;
  }

  public Float getForeignTxnExchangeRate() {
    return foreignTxnExchangeRate;
  }

  public void setForeignTxnExchangeRate(Float foreignTxnExchangeRate) {
    this.foreignTxnExchangeRate = foreignTxnExchangeRate;
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public String getMerchantName() {
    return merchantName;
  }

  public void setMerchantName(String merchantName) {
    this.merchantName = merchantName;
  }

  public String getCategoryType() {
    return categoryType;
  }

  public void setCategoryType(String categoryType) {
    this.categoryType = categoryType;
  }

  public String getCategorySubType() {
    return categorySubType;
  }

  public void setCategorySubType(String categorySubType) {
    this.categorySubType = categorySubType;
  }

  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  public void setAdditionalProperties(Map<String, Object> additionalProperties) {
    this.additionalProperties = additionalProperties;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("transactionId", transactionId).append("accountNumber", accountNumber)
        .append("accountId", accountId).append("txnDate", txnDate).append("description", description).append("category", category)
        .append("amount", amount).append("currency", currency).append("transactionType", transactionType)
        .append("foreignTxnAmount", foreignTxnAmount).append("foreignTxnCurrency", foreignTxnCurrency)
        .append("foreignTxnExchangeRate", foreignTxnExchangeRate).append("referenceId", referenceId).append("merchantName", merchantName)
        .append("categoryType", categoryType).append("categorySubType", categorySubType)
        .append("additionalProperties", additionalProperties).toString();
  }
}

