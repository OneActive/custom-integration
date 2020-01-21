package com.activeai.integration.banking.model;

import java.util.Objects;

import com.activeai.integration.banking.constants.CardIssuerEnum;
import com.activeai.integration.banking.constants.CardStatusEnum;
import com.activeai.integration.banking.constants.CardTypeEnum;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Card
 */
@Validated
public class Card {

  @JsonProperty("productCode")
  private String productCode = null;

  @JsonProperty("bankName")
  private String bankName;

  @JsonProperty("accountName")
  private String accountName;

  @JsonProperty("cardType")
  private CardTypeEnum cardType = null;

  @JsonProperty("displayCardNumber")
  private String displayCardNumber = null;

  @JsonProperty("cardNumber")
  private String cardNumber = null;

  @JsonProperty("accountId")
  private String accountId = null;

  @JsonProperty("cardIssuer")
  private CardIssuerEnum cardIssuer = null;

  @JsonProperty("branchId")
  private String branchId = null;

  @JsonProperty("branchName")
  private String branchName = null;

  @JsonProperty("cardStatus")
  private CardStatusEnum cardStatus = null;

  @JsonProperty("overseasCardActivated")
  private Boolean overseasCardActivated = null;

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

  @JsonProperty("availableCreditLimit")
  private Double availableCreditLimit = null;

 @JsonProperty("availableCash")
  private Double availableCash=null;

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

  @JsonProperty("branchAddress")
  private String branchAddress;

  @JsonProperty("outStandingAmount")
  private Double outStandingAmount;

  @JsonProperty("cardHolderName")
  private String cardHolderName;

  @JsonProperty("cardName")
  private String cardName = null;

  @JsonProperty("productType")
  private String productType = null;

  @JsonProperty("maxDomesticATMLimit")
  private String maxDomesticATMLimit = null;

  @JsonProperty("maxCreditLimit")
  private String maxCreditLimit = null;

  @JsonProperty("maxCashLimit")
  private String maxCashLimit = null;

  @JsonProperty("maxDomesticPOSLimit")
  private String maxDomesticPOSLimit = null;

  @JsonProperty("domesticATMLimit")
  private String domesticATMLimit = null;

  @JsonProperty("domesticPOSLimit")
  private String domesticPOSLimit = null;

  @JsonProperty("maxInternationalATMLimit")
  private String maxInternationalATMLimit;

  @JsonProperty("maxInternationalPOSLimit")
  private String maxInternationalPOSLimit;

  @JsonProperty("internationalATMLimit")
  private String internationalATMLimit;

  @JsonProperty("internationalPOSLimit")
  private String internationalPOSLimit;

  @JsonProperty("creditLimit")
  private Double creditLimit = null;

  @JsonProperty("cashLimit")
  private String cashLimit = null;

  @JsonProperty("isInternationalEnabled")
  private boolean isInternationalEnabled;

  /**
   * To make Card Debitable set isDebitable as true
   */
  @JsonProperty("isDebitable")
  private boolean isDebitable;

  /**
   * To make Card Creditable set isCreditable as true
   */
  @JsonProperty("isCreditable")
  private boolean isCreditable;

  public Double getCreditLimit() {
    return creditLimit;
  }

  public String getCardHolderName() {
    return cardHolderName;
  }

  public void setCardHolderName(String cardHolderName) {
    this.cardHolderName = cardHolderName;
  }

  public Double getOutStandingAmount() {
    return outStandingAmount;
  }

  public void setOutStandingAmount(Double outStandingAmount) {
    this.outStandingAmount = outStandingAmount;
  }

  public String getBranchAddress() {
    return branchAddress;
  }

  public void setBranchAddress(String branchAddress) {
    this.branchAddress = branchAddress;
  }

  public Boolean getOverseasCardActivated() {
    return overseasCardActivated;
  }

  public String getLastStatementDate() {
    return lastStatementDate;
  }

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
   * Type of card
   *
   * @return cardType
   **/
  public CardTypeEnum getCardType() {
    return cardType;
  }

  public void setCardType(CardTypeEnum cardType) {
    this.cardType = cardType;
  }

  /**
   * Display Account number (in masked form)
   *
   * @return displayCardNumber
   **/
  public String getDisplayCardNumber() {
    return displayCardNumber;
  }

  public void setDisplayCardNumber(String displayCardNumber) {
    this.displayCardNumber = displayCardNumber;
  }

  /**
   * Display Account number
   *
   * @return CardNumber
   **/
  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
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
  public CardIssuerEnum getCardIssuer() {
    return cardIssuer;
  }

  public void setCardIssuer(CardIssuerEnum cardIssuer) {
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
  public CardStatusEnum getCardStatus() {
    return cardStatus;
  }

  public void setCardStatus(CardStatusEnum cardStatus) {
    this.cardStatus = cardStatus;
  }

  /**
   * Is card activated for overseas use
   *
   * @return overseasCardActivated
   **/
  public Boolean isOversearCardActivated() {
    return overseasCardActivated;
  }

  public void setOverseasCardActivated(Boolean overseasCardActivated) {
    this.overseasCardActivated = overseasCardActivated;
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
  /**
   * available cash
   *
   * @return availableCash
   **/
  public Double getAvailableCash() { return availableCash; }

  /**
   * cardName
   *
   * @return cardName
   **/
  public String getCardName() {
    return cardName;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  /**
   * productType
   *
   * @return productType
   **/
  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  /**
   * maxDomesticATMLimit
   *
   * @return maxDomesticATMLimit
   **/
  public String getMaxDomesticATMLimit() {
    return maxDomesticATMLimit;
  }

  public void setMaxDomesticATMLimit(String maxDomesticATMLimit) {
    this.maxDomesticATMLimit = maxDomesticATMLimit;
  }

  /**
   * maxDomesticPOSLimit
   *
   * @return maxDomesticPOSLimit
   **/
  public String getMaxDomesticPOSLimit() {
    return maxDomesticPOSLimit;
  }

  public void setMaxDomesticPOSLimit(String maxDomesticPOSLimit) {
    this.maxDomesticPOSLimit = maxDomesticPOSLimit;
  }

  /**
   * domesticATMLimit
   *
   * @return domesticATMLimit
   **/
  public String getDomesticATMLimit() {
    return domesticATMLimit;
  }

  public void setDomesticATMLimit(String domesticATMLimit) {
    this.domesticATMLimit = domesticATMLimit;
  }

  /**
   * domesticPOSLimit
   *
   * @return domesticPOSLimit
   **/
  public String getDomesticPOSLimit() {
    return domesticPOSLimit;
  }

  public void setDomesticPOSLimit(String domesticPOSLimit) {
    this.domesticPOSLimit = domesticPOSLimit;
  }

  /**
   * maxInternationalATMLimit
   *
   * @return maxInternationalATMLimit
   **/
  public String getMaxInternationalATMLimit() {
    return maxInternationalATMLimit;
  }

  public void setMaxInternationalATMLimit(String maxInternationalATMLimit) {
    this.maxInternationalATMLimit = maxInternationalATMLimit;
  }

  /**
   * maxInternationalPOSLimit
   *
   * @return maxInternationalPOSLimit
   **/
  public String getMaxInternationalPOSLimit() {
    return maxInternationalPOSLimit;
  }

  public void setMaxInternationalPOSLimit(String maxInternationalPOSLimit) {
    this.maxInternationalPOSLimit = maxInternationalPOSLimit;
  }

  /**
   * internationalATMLimit
   *
   * @return internationalATMLimit
   **/
  public String getInternationalATMLimit() {
    return internationalATMLimit;
  }

  public void setInternationalATMLimit(String internationalATMLimit) {
    this.internationalATMLimit = internationalATMLimit;
  }

  /**
   * internationalPOSLimit
   *
   * @return internationalPOSLimit
   **/
  public String getInternationalPOSLimit() {
    return internationalPOSLimit;
  }

  public void setInternationalPOSLimit(String internationalPOSLimit) {
    this.internationalPOSLimit = internationalPOSLimit;
  }


  /**
   * internationalEnabled
   *
   * @return internationalEnabled
   **/

  public boolean isInternationalEnabled() {
    return isInternationalEnabled;
  }

  public void setInternationalEnabled(boolean internationalEnabled) {
    isInternationalEnabled = internationalEnabled;
  }


  public void setAvailableCash(Double availableCash) { this.availableCash = availableCash; }

  public String getMaxCreditLimit() {
    return maxCreditLimit;
  }

  public void setMaxCreditLimit(String maxCreditLimit) {
    this.maxCreditLimit = maxCreditLimit;
  }

  public String getMaxCashLimit() {
    return maxCashLimit;
  }

  public void setMaxCashLimit(String maxCashLimit) {
    this.maxCashLimit = maxCashLimit;
  }

  public void setCreditLimit(Double creditLimit) {
    this.creditLimit = creditLimit;
  }

  public String getCashLimit() {
    return cashLimit;
  }

  public void setCashLimit(String cashLimit) {
    this.cashLimit = cashLimit;
  }

  /**
   * Getter and Setter Methods for isDebitable Flag
   * @return
   */
  public boolean isDebitable() {
    return isDebitable;
  }

  public void setDebitable(boolean debitable) {
    isDebitable = debitable;
  }

  /**
   * Getter and Setter for isCreditable Flag
   * @return
   */
  public boolean isCreditable() {
    return isCreditable;
  }

  public void setCreditable(boolean creditable) {
    isCreditable = creditable;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Card{");
    sb.append("productCode='").append(productCode).append('\'');
    sb.append(", bankName='").append(bankName).append('\'');
    sb.append(", accountName='").append(accountName).append('\'');
    sb.append(", cardType=").append(cardType);
    sb.append(", displayCardNumber='").append(displayCardNumber).append('\'');
    sb.append(", cardNumber='").append(cardNumber).append('\'');
    sb.append(", accountId='").append(accountId).append('\'');
    sb.append(", cardIssuer=").append(cardIssuer);
    sb.append(", branchId='").append(branchId).append('\'');
    sb.append(", branchName='").append(branchName).append('\'');
    sb.append(", cardStatus=").append(cardStatus);
    sb.append(", overseasCardActivated=").append(overseasCardActivated);
    sb.append(", openingBalance=").append(openingBalance);
    sb.append(", closingBalance=").append(closingBalance);
    sb.append(", amountDue=").append(amountDue);
    sb.append(", minimumPayment=").append(minimumPayment);
    sb.append(", paymentDueDate='").append(paymentDueDate).append('\'');
    sb.append(", availableCreditLimit=").append(availableCreditLimit);
    sb.append(", temporaryCreditLimit=").append(temporaryCreditLimit);
    sb.append(", permanentCreditLimit=").append(permanentCreditLimit);
    sb.append(", lastStatementDate='").append(lastStatementDate).append('\'');
    sb.append(", lastStatementBalance=").append(lastStatementBalance);
    sb.append(", currencyCode='").append(currencyCode).append('\'');
    sb.append(", expiryDate='").append(expiryDate).append('\'');
    sb.append(", activationDate='").append(activationDate).append('\'');
    sb.append(", branchAddress='").append(branchAddress).append('\'');
    sb.append(", outStandingAmount=").append(outStandingAmount);
    sb.append(", cardHolderName='").append(cardHolderName).append('\'');
    sb.append(", cardName='").append(cardName).append('\'');
    sb.append(", productType='").append(productType).append('\'');
    sb.append(", maxDomesticATMLimit='").append(maxDomesticATMLimit).append('\'');
    sb.append(", maxDomesticPOSLimit='").append(maxDomesticPOSLimit).append('\'');
    sb.append(", domesticATMLimit='").append(domesticATMLimit).append('\'');
    sb.append(", domesticPOSLimit='").append(domesticPOSLimit).append('\'');
    sb.append(", maxInternationalATMLimit='").append(maxInternationalATMLimit).append('\'');
    sb.append(", maxInternationalPOSLimit='").append(maxInternationalPOSLimit).append('\'');
    sb.append(", maxCreditLimit='").append(maxCreditLimit).append('\'');
    sb.append(", maxCashLimit='").append(maxCashLimit).append('\'');
    sb.append(", creditLimit='").append(creditLimit).append('\'');
    sb.append(", cashLimit='").append(cashLimit).append('\'');
    sb.append(", internationalATMLimit='").append(internationalATMLimit).append('\'');
    sb.append(", internationalPOSLimit='").append(internationalPOSLimit).append('\'');
    sb.append(", isInternationalEnabled=").append(isInternationalEnabled);
    sb.append(", isCreditable='").append(isCreditable).append('\'');
    sb.append(", isDebitable=").append(isDebitable);
    sb.append('}');
    return sb.toString();
  }
}

