package com.activeai.integration.banking.model;

import com.activeai.integration.banking.constants.AccountProductEnum;
import com.activeai.integration.banking.constants.AccountStatusEnum;
import com.activeai.integration.banking.constants.AccountTypeEnum;
import com.activeai.integration.banking.constants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Account
 */
@Validated
public class Account {

  @JsonProperty("productCode")
  private String productCode = null;

  @JsonProperty("product")
  private AccountProductEnum product = null;

  @JsonProperty("bankName")
  private String bankName;

  @JsonProperty("accountName")
  private String accountName;

  @JsonProperty("accountType")
  private AccountTypeEnum accountType = null;

  @JsonProperty("displayAccountNumber")
  private String displayAccountNumber = null;

  @JsonProperty("accountNumber")
  private String accountNumber = null;

  @JsonProperty("accountId")
  private String accountId = null;

  @JsonProperty("branchId")
  private String branchId = null;

  @JsonProperty("branchName")
  private String branchName = null;

  @JsonProperty("status")
  private AccountStatusEnum status = null;

  @JsonProperty("openingDate")
  private String openingDate = null;

  @JsonProperty("lastStatementDate")
  private String lastStatementDate = null;

  @JsonProperty("lastStatementBalance")
  private Double lastStatementBalance = null;

  @JsonProperty("balance")
  private AccountBalance balance = null;

  @JsonProperty("branchAddress")
  private String branchAddress;

  @JsonProperty("ifscCode")
  private String ifscCode;

  @JsonProperty("leavesCount")
  private String leavesCount;

  @JsonProperty("referenceId")
  private String referenceId;

  @JsonProperty("transactionStatus")
  private StatusEnum transactionStatus;

  /**
   * To make Account Debitable set isDebitable as true
   */
  @JsonProperty("isDebitable")
  private boolean isDebitable;

  /**
   * To make Account Creditable set isCreditable as true
   */
  @JsonProperty("isCreditable")
  private boolean isCreditable;

  /**
   * To add extra params in the response
   */
  @JsonProperty("additionalProperties")
  private Map<String,Object>additionalProperties = new HashMap<>();

  public String getIfscCode() {
    return ifscCode;
  }

  public void setIfscCode(String ifscCode) {
    this.ifscCode = ifscCode;
  }

  public String getBranchAddress() {
    return branchAddress;
  }

  public void setBranchAddress(String branchAddress) {
    this.branchAddress = branchAddress;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  /**
   * Product code
   * 
   * @return productCode
   **/
  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  /**
   * Product name
   * 
   * @return product
   **/
  public AccountProductEnum getProduct() {
    return product;
  }

  public void setProduct(AccountProductEnum product) {
    this.product = product;
  }

  /**
   * Type of account
   * 
   * @return accountType
   **/
  public AccountTypeEnum getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
  }

  /**
   * Display Account number (in masked form)
   * 
   * @return displayAccountNumber
   **/
  public String getDisplayAccountNumber() {
    return displayAccountNumber;
  }

  public void setDisplayAccountNumber(String displayAccountNumber) {
    this.displayAccountNumber = displayAccountNumber;
  }

  /**
   * Display Account number (in masked form)
   * 
   * @return accountNumber
   **/
  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * Account Id for communication where in actual a/c no is not sent
   * 
   * @return accountId
   **/
  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  /**
   * Branch ID
   * 
   * @return branchId
   **/
  public String getBranchId() {
    return branchId;
  }

  public void setBranchId(String branchId) {
    this.branchId = branchId;
  }

  /**
   * Branch Name
   * 
   * @return branchName
   **/
  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }

  /**
   * Account status dormant / active / closed
   * 
   * @return status
   **/
  public AccountStatusEnum getStatus() {
    return status;
  }

  public void setStatus(AccountStatusEnum status) {
    this.status = status;
  }

  public String getOpeningDate() {
    return openingDate;
  }

  public void setOpeningDate(String openingDate) {
    this.openingDate = openingDate;
  }

  public String getLastStatementDate() {
    return lastStatementDate;
  }

  public void setLastStatementDate(String lastStatementDate) {
    this.lastStatementDate = lastStatementDate;
  }

  /**
   * last statement balance
   * 
   * @return lastStatementBalance
   **/
  public Double getLastStatementBalance() {
    return lastStatementBalance;
  }

  public void setLastStatementBalance(Double lastStatementBalance) {
    this.lastStatementBalance = lastStatementBalance;
  }

  /**
   * Get balance
   * 
   * @return balance
   **/
  @Valid
  public AccountBalance getBalance() {
    return balance;
  }

  public void setBalance(AccountBalance balance) {
    this.balance = balance;
  }


  public String getLeavesCount() {
    return leavesCount;
  }

  public void setLeavesCount(String leavesCount) {
    this.leavesCount = leavesCount;
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public StatusEnum getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(StatusEnum transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  /**
   * Getter and Setter Methods for isDebitable
   * @return
   */
  public boolean isDebitable() {
    return isDebitable;
  }

  public void setDebitable(boolean debitable) {
    isDebitable = debitable;
  }

  /**
   * Getter and Setter Methods for isCreditable
   * @return
   */
  public boolean isCreditable() {
    return isCreditable;
  }

  public void setCreditable(boolean creditable) {
    isCreditable = creditable;
  }

  /**
   * Get Additional Properties
   *
   * @return additionalProperties
   **/
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  public void setAdditionalProperties(Map<String, Object> additionalProperties) {
    this.additionalProperties = additionalProperties;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("productCode", productCode).append("product", product).append("bankName", bankName)
        .append("accountName", accountName).append("accountType", accountType).append("displayAccountNumber", displayAccountNumber)
        .append("accountNumber", accountNumber).append("accountId", accountId).append("branchId", branchId).append("branchName", branchName)
        .append("status", status).append("openingDate", openingDate).append("lastStatementDate", lastStatementDate)
        .append("lastStatementBalance", lastStatementBalance).append("balance", balance).append("branchAddress", branchAddress)
        .append("ifscCode", ifscCode).append("leavesCount", leavesCount).append("referenceId", referenceId)
        .append("transactionStatus", transactionStatus).append("isDebitable", isDebitable).append("isCreditable", isCreditable)
        .append("additionalProperties", additionalProperties).toString();
  }
}

