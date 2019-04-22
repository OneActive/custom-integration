package com.activeai.integration.banking.model;

import java.util.Objects;
import javax.validation.Valid;
import com.activeai.integration.banking.constants.AccountStatus;
import com.activeai.integration.banking.constants.AccountType;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Account
 */
@Validated
public class Account {

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("productCode")
  private String productCode = null;

  @JsonProperty("product")
  private String product = null;

  @JsonProperty("bankName")
  private String bankName;

  @JsonProperty("accountName")
  private String accountName;

  @JsonProperty("type")
  private AccountType type = null;

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
  private AccountStatus status = null;

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
   * category like ACCOUNT, LOAN, DEPOSIT, etc.
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
  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }
  /**
   * Type of account
   * 
   * @return type
   **/
  public AccountType getType() {
    return type;
  }

  public void setType(AccountType type) {
    this.type = type;
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
  public AccountStatus getStatus() {
    return status;
  }

  public void setStatus(AccountStatus status) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.category, account.category) && Objects.equals(this.productCode, account.productCode)
        && Objects.equals(this.product, account.product) && Objects.equals(this.type, account.type)
        && Objects.equals(this.product, account.product) && Objects.equals(this.accountName, account.accountName)
        && Objects.equals(this.product, account.product) && Objects.equals(this.bankName, account.bankName)
        && Objects.equals(this.displayAccountNumber, account.displayAccountNumber)
        && Objects.equals(this.accountNumber, account.accountNumber) && Objects.equals(this.accountId, account.accountId)
        && Objects.equals(this.branchId, account.branchId) && Objects.equals(this.branchName, account.branchName)
        && Objects.equals(this.status, account.status) && Objects.equals(this.openingDate, account.openingDate)
        && Objects.equals(this.lastStatementDate, account.lastStatementDate)
        && Objects.equals(this.lastStatementBalance, account.lastStatementBalance) && Objects.equals(this.balance, account.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, productCode, product,accountName, bankName, type, displayAccountNumber, accountNumber, accountId, branchId, branchName, status,
        openingDate, lastStatementDate, lastStatementBalance, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");

    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    productCode: ").append(toIndentedString(productCode)).append("\n");
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

