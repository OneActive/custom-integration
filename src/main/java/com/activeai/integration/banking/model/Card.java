package com.activeai.integration.banking.model;

import java.util.Objects;

import com.activeai.integration.banking.constants.CardIssuer;
import com.activeai.integration.banking.constants.CardStatus;
import com.activeai.integration.banking.constants.CardType;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Card
 */
@Validated
public class Card {

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
  private CardType type = null;

  @JsonProperty("displayAccountNumber")
  private String displayAccountNumber = null;

  @JsonProperty("accountNumber")
  private String accountNumber = null;

  @JsonProperty("accountId")
  private String accountId = null;

  @JsonProperty("cardIssuer")
  private CardIssuer cardIssuer = null;

  @JsonProperty("branchId")
  private String branchId = null;

  @JsonProperty("branchName")
  private String branchName = null;

  @JsonProperty("status")
  private CardStatus status = null;

  @JsonProperty("oversearCardActivated")
  private Boolean oversearCardActivated = null;

  @JsonProperty("openingBalance")
  private Double openingBalance = null;

  @JsonProperty("closingBalance")
  private Double closingBalance = null;

  @JsonProperty("amountDue")
  private Double amountDue = null;

  @JsonProperty("minimumPayment")
  private Double minimumPayment = null;

  @JsonProperty("paymentDueDate")
  private String paymentDueDate = null;

  @JsonProperty("creditLimit")
  private Double creditLimit = null;

  @JsonProperty("availableCreditLimit")
  private Double availableCreditLimit = null;

  @JsonProperty("temporaryCreditLimit")
  private Double temporaryCreditLimit = null;

  @JsonProperty("permanentCreditLimit")
  private Double permanentCreditLimit = null;

  @JsonProperty("lastStatementDate")
  private String lastStatementDate = null;

  @JsonProperty("lastStatementBalance")
  private Double lastStatementBalance = null;

  @JsonProperty("currencyCode")
  private String currencyCode;

  @JsonProperty("expiryDate")
  private String expiryDate;

  @JsonProperty("activationDate")
  private String activationDate;

  public String getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
  }

  public String getActivationDate() {
    return activationDate;
  }

  public void setActivationDate(String activationDate) {
    this.activationDate = activationDate;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  /**
   * category like CARD
   * 
   * @return category
   **/
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Card productCode(String productCode) {
    this.productCode = productCode;
    return this;
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
   * Type of card
   * 
   * @return type
   **/
  public CardType getType() {
    return type;
  }

  public void setType(CardType type) {
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
   * Display Account number
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
   * Card issues like visa, master card, amex, Diners
   * 
   * @return cardIssuer
   **/
  public CardIssuer getCardIssuer() {
    return cardIssuer;
  }

  public void setCardIssuer(CardIssuer cardIssuer) {
    this.cardIssuer = cardIssuer;
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
   * Account status dormant / active / closed / blocker
   * 
   * @return status
   **/
  public CardStatus getStatus() {
    return status;
  }

  public void setStatus(CardStatus status) {
    this.status = status;
  }

  /**
   * Is card activated for overseas use
   * 
   * @return oversearCardActivated
   **/
  public Boolean isOversearCardActivated() {
    return oversearCardActivated;
  }

  public void setOversearCardActivated(Boolean oversearCardActivated) {
    this.oversearCardActivated = oversearCardActivated;
  }

  /**
   * Opening balance
   * 
   * @return openingBalance
   **/
  public Double getOpeningBalance() {
    return openingBalance;
  }

  public void setOpeningBalance(Double openingBalance) {
    this.openingBalance = openingBalance;
  }

  /**
   * Closing balance
   * 
   * @return closingBalance
   **/
  public Double getClosingBalance() {
    return closingBalance;
  }

  public void setClosingBalance(Double closingBalance) {
    this.closingBalance = closingBalance;
  }

  /**
   * Amount Due
   * 
   * @return amountDue
   **/
  public Double getAmountDue() {
    return amountDue;
  }

  public void setAmountDue(Double amountDue) {
    this.amountDue = amountDue;
  }

  /**
   * Minimum payment
   * 
   * @return minimumPayment
   **/
  public Double getMinimumPayment() {
    return minimumPayment;
  }

  public void setMinimumPayment(Double minimumPayment) {
    this.minimumPayment = minimumPayment;
  }

  public String getPaymentDueDate() {
    return paymentDueDate;
  }

  public void setPaymentDueDate(String paymentDueDate) {
    this.paymentDueDate = paymentDueDate;
  }

  public void setLastStatementDate(String lastStatementDate) {
    this.lastStatementDate = lastStatementDate;
  }

  public Card creditLimit(Double creditLimit) {
    this.creditLimit = creditLimit;
    return this;
  }

  /**
   * Credit limit
   * 
   * @return creditLimit
   **/
  public Double getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(Double creditLimit) {
    this.creditLimit = creditLimit;
  }

  public Card availableCreditLimit(Double availableCreditLimit) {
    this.availableCreditLimit = availableCreditLimit;
    return this;
  }

  /**
   * Available Credit limit
   * 
   * @return availableCreditLimit
   **/
  public Double getAvailableCreditLimit() {
    return availableCreditLimit;
  }

  public void setAvailableCreditLimit(Double availableCreditLimit) {
    this.availableCreditLimit = availableCreditLimit;
  }

  public Card temporaryCreditLimit(Double temporaryCreditLimit) {
    this.temporaryCreditLimit = temporaryCreditLimit;
    return this;
  }

  /**
   * Temporary Credit limit
   * 
   * @return temporaryCreditLimit
   **/
  public Double getTemporaryCreditLimit() {
    return temporaryCreditLimit;
  }

  public void setTemporaryCreditLimit(Double temporaryCreditLimit) {
    this.temporaryCreditLimit = temporaryCreditLimit;
  }

  public Card permanentCreditLimit(Double permanentCreditLimit) {
    this.permanentCreditLimit = permanentCreditLimit;
    return this;
  }

  /**
   * Permanent Credit limit
   * 
   * @return permanentCreditLimit
   **/
  public Double getPermanentCreditLimit() {
    return permanentCreditLimit;
  }

  public void setPermanentCreditLimit(Double permanentCreditLimit) {
    this.permanentCreditLimit = permanentCreditLimit;
  }

  public Card lastStatementBalance(Double lastStatementBalance) {
    this.lastStatementBalance = lastStatementBalance;
    return this;
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

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Card card = (Card) o;
    return Objects.equals(this.category, card.category) && Objects.equals(this.productCode, card.productCode)
        && Objects.equals(this.product, card.product) && Objects.equals(this.type, card.type)
        && Objects.equals(this.product, card.product) && Objects.equals(this.accountName, card.accountName)
        && Objects.equals(this.product, card.product) && Objects.equals(this.bankName, card.bankName)
        && Objects.equals(this.displayAccountNumber, card.displayAccountNumber) && Objects.equals(this.accountNumber, card.accountNumber)
        && Objects.equals(this.accountId, card.accountId) && Objects.equals(this.cardIssuer, card.cardIssuer)
        && Objects.equals(this.branchId, card.branchId) && Objects.equals(this.branchName, card.branchName)
        && Objects.equals(this.status, card.status) && Objects.equals(this.oversearCardActivated, card.oversearCardActivated)
        && Objects.equals(this.openingBalance, card.openingBalance) && Objects.equals(this.closingBalance, card.closingBalance)
        && Objects.equals(this.amountDue, card.amountDue) && Objects.equals(this.minimumPayment, card.minimumPayment)
        && Objects.equals(this.paymentDueDate, card.paymentDueDate) && Objects.equals(this.creditLimit, card.creditLimit)
        && Objects.equals(this.availableCreditLimit, card.availableCreditLimit)
        && Objects.equals(this.temporaryCreditLimit, card.temporaryCreditLimit)
        && Objects.equals(this.permanentCreditLimit, card.permanentCreditLimit)
        && Objects.equals(this.lastStatementDate, card.lastStatementDate)
        && Objects.equals(this.lastStatementBalance, card.lastStatementBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, productCode, product, accountName, bankName, type, displayAccountNumber, accountNumber, accountId, cardIssuer, branchId,
        branchName, status, oversearCardActivated, openingBalance, closingBalance, amountDue, minimumPayment, paymentDueDate, creditLimit,
        availableCreditLimit, temporaryCreditLimit, permanentCreditLimit, lastStatementDate, lastStatementBalance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Card {\n");

    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    productCode: ").append(toIndentedString(productCode)).append("\n");
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    AccountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    displayAccountNumber: ").append(toIndentedString(displayAccountNumber)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    cardIssuer: ").append(toIndentedString(cardIssuer)).append("\n");
    sb.append("    branchId: ").append(toIndentedString(branchId)).append("\n");
    sb.append("    branchName: ").append(toIndentedString(branchName)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    oversearCardActivated: ").append(toIndentedString(oversearCardActivated)).append("\n");
    sb.append("    openingBalance: ").append(toIndentedString(openingBalance)).append("\n");
    sb.append("    closingBalance: ").append(toIndentedString(closingBalance)).append("\n");
    sb.append("    amountDue: ").append(toIndentedString(amountDue)).append("\n");
    sb.append("    minimumPayment: ").append(toIndentedString(minimumPayment)).append("\n");
    sb.append("    paymentDueDate: ").append(toIndentedString(paymentDueDate)).append("\n");
    sb.append("    creditLimit: ").append(toIndentedString(creditLimit)).append("\n");
    sb.append("    availableCreditLimit: ").append(toIndentedString(availableCreditLimit)).append("\n");
    sb.append("    temporaryCreditLimit: ").append(toIndentedString(temporaryCreditLimit)).append("\n");
    sb.append("    permanentCreditLimit: ").append(toIndentedString(permanentCreditLimit)).append("\n");
    sb.append("    lastStatementDate: ").append(toIndentedString(lastStatementDate)).append("\n");
    sb.append("    lastStatementBalance: ").append(toIndentedString(lastStatementBalance)).append("\n");
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
