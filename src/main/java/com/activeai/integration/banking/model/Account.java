package com.activeai.integration.banking.model;

import com.activeai.integration.banking.constants.AccountProductEnum;
import com.activeai.integration.banking.constants.AccountStatusEnum;
import com.activeai.integration.banking.constants.AccountTypeEnum;
import com.activeai.integration.banking.constants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

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

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.productCode, account.productCode)
        && Objects.equals(this.product, account.product) && Objects.equals(this.accountType, account.accountType)
        && Objects.equals(this.product, account.product) && Objects.equals(this.accountName, account.accountName)
        && Objects.equals(this.product, account.product) && Objects.equals(this.bankName, account.bankName)
        && Objects.equals(this.displayAccountNumber, account.displayAccountNumber)
        && Objects.equals(this.accountNumber, account.accountNumber) && Objects.equals(this.accountId, account.accountId)
        && Objects.equals(this.branchId, account.branchId) && Objects.equals(this.branchName, account.branchName)
        && Objects.equals(this.status, account.status) && Objects.equals(this.openingDate, account.openingDate)
        && Objects.equals(this.lastStatementDate, account.lastStatementDate)
        && Objects.equals(this.leavesCount, account.leavesCount)
        && Objects.equals(this.referenceId, account.referenceId)
        && Objects.equals(this.transactionStatus, account.transactionStatus)
        && Objects.equals(this.lastStatementBalance, account.lastStatementBalance) && Objects.equals(this.balance, account.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productCode, product,accountName, bankName, accountType, displayAccountNumber, accountNumber, accountId, branchId, branchName, status,
        openingDate, lastStatementDate, lastStatementBalance, balance, leavesCount, referenceId, transactionStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");

    sb.append("    productCode: ").append(toIndentedString(productCode)).append("\n");
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    displayAccountNumber: ").append(toIndentedString(displayAccountNumber)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    branchId: ").append(toIndentedString(branchId)).append("\n");
    sb.append("    branchName: ").append(toIndentedString(branchName)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    openingDate: ").append(toIndentedString(openingDate)).append("\n");
    sb.append("    lastStatementDate: ").append(toIndentedString(lastStatementDate)).append("\n");
    sb.append("    lastStatementBalance: ").append(toIndentedString(lastStatementBalance)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    branchAddress: ").append(toIndentedString(branchAddress)).append("\n");
    sb.append("    leavesCount: ").append(toIndentedString(leavesCount)).append("\n");
    sb.append("    referenceId: ").append(toIndentedString(referenceId)).append("\n");
    sb.append("    transactionStatus: ").append(toIndentedString(transactionStatus)).append("\n");
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

