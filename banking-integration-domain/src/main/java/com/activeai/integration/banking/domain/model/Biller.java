package com.activeai.integration.banking.domain.model;

import com.activeai.integration.banking.domain.constants.BillerCategoryTypeEnum;
import com.activeai.integration.banking.domain.constants.PartialPaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * Biller
 */
@Validated
public class Biller {

  @JsonProperty("billerId")
  private String billerId = null;

  @JsonProperty("billerName")
  private String billerName = null;

  /**
   * Biller Category Type like ADHOC , REGISTERED
   */
  @JsonProperty("billerCategoryType")
  private BillerCategoryTypeEnum billerCategoryType;

  /**
   * Biller Category Name like Mobile,Electricity,Charity
   */
  private String billerCategoryName;

  @JsonProperty("billerCategoryId")
  private String billerCategoryId;

  @JsonProperty("partialPayment")
  private PartialPaymentStatus partialPayment;

  @JsonProperty("dueAmount")
  private Double dueAmount;

  @JsonProperty("userIdentityInputs")
  private List<UserIdentityInput> userIdentityInputs = new ArrayList<>();

  @JsonProperty("authenticators")
  private List<String> authenticators = new ArrayList<>();

  @JsonProperty("fillers")
  private List<String> fillers = new ArrayList<>();

  @JsonProperty("billDate")
  private String billDate;

  @JsonProperty("billNumber")
  private String billNumber;

  @JsonProperty("payWithOutBill")
  private String payWithOutBill;

  @JsonProperty("billDueDate")
  private String billDueDate;

  @JsonProperty("lastStatementBalance")
  private Double lastStatementBalance;

  @JsonProperty("billerType")
  private String billerType;

  @JsonProperty("billerPresence")
  private String billerPresence;

  @JsonProperty("paymentAllowedPostdueDate")
  private String paymentAllowedPostdueDate;

  public Double getLastStatementBalance() {
    return lastStatementBalance;
  }

  public void setLastStatementBalance(Double lastStatementBalance) {
    this.lastStatementBalance = lastStatementBalance;
  }

  public Biller billerId(String billerId) {
    this.billerId = billerId;
    return this;
  }

  /**
   * Biller ID
   * 
   * @return billerId
   **/
  public String getBillerId() {
    return billerId;
  }

  public void setBillerId(String billerId) {
    this.billerId = billerId;
  }

  /**
   * BillerCategoryID
   *
   * @return billerICategoryId
   **/
  public String getBillerCategoryId() {
    return billerCategoryId;
  }

  public void setBillerCategoryId(String billerCategoryId) {
    this.billerCategoryId = billerCategoryId;
  }

  /**
   * Biller Name
   * 
   * @return billerName
   **/
  public String getBillerName() {
    return billerName;
  }

  public void setBillerName(String billerName) {
    this.billerName = billerName;
  }

  public BillerCategoryTypeEnum getBillerCategoryType() {
    return billerCategoryType;
  }

  public void setBillerCategoryType(BillerCategoryTypeEnum billerCategoryType) {
    this.billerCategoryType = billerCategoryType;
  }

  public String getBillerCategoryName() {
    return billerCategoryName;
  }

  public void setBillerCategoryName(String billerCategoryName) {
    this.billerCategoryName = billerCategoryName;
  }

  /**
   * Biller category
   *
   * @return billerCategory
   **/

  public Double getDueAmount() {
    return dueAmount;
  }

  public void setDueAmount(Double dueAmount) {
    this.dueAmount = dueAmount;
  }

  /**
   * Does this biller Partial Payment
   *
   * @return Partial Payment
   **/

  public PartialPaymentStatus getPartialPayment() {
    return partialPayment;
  }

  public void setPartialPayment(PartialPaymentStatus partialPayment) {
    this.partialPayment = partialPayment;
  }

  /**
   * Does this biller userIdentityInput
   *
   *
   * @return userIdentityInput
   **/
  public List<UserIdentityInput> getUserIdentityInputs() {
    return userIdentityInputs;
  }

  public void setUserIdentityInputs(List<UserIdentityInput> userIdentityInputs) {
    this.userIdentityInputs = userIdentityInputs;
  }

  /**
   * Does this biller authenticators
   *
   *
   * @return authenticators
   **/
  public List<String> getAuthenticators() {
    return authenticators;
  }

  public void setAuthenticators(List<String> authenticators) {
    this.authenticators = authenticators;
  }

  /**
   * Does this biller fillers
   *
   *
   * @return fillers
   **/
  public List<String> getFillers() {
    return fillers;
  }

  public void setFillers(List<String> fillers) {
    this.fillers = fillers;
  }

  /**
   * Does this biller billDate
   *
   *
   * @return billDate
   **/
  public String getBillDate() {
    return billDate;
  }

  public void setBillDate(String billDate) {
    this.billDate = billDate;
  }

  /**
   * Does this biller billDueDate
   *
   *
   * @return billDueDate
   **/
  public String getBillDueDate() {
    return billDueDate;
  }

  public void setBillDueDate(String billDueDate) {
    this.billDueDate = billDueDate;
  }

  /**
   * Does this biller BillNumber
   *
   *
   * @return billNumber
   **/
  public String getBillNumber() {
    return billNumber;
  }

  public void setBillNumber(String billNumber) {
    this.billNumber = billNumber;
  }

  /**
   * Does this biller PaytwithoutBill
   *
   *
   *
   * @return PaytwithoutBill
   **/
  public String getPayWithOutBill() {
    return payWithOutBill;
  }

  public void setPayWithOutBill(String payWithOutBill) {
    this.payWithOutBill = payWithOutBill;
  }


  public String getBillerType() {
    return billerType;
  }

  public void setBillerType(String billerType) {
    this.billerType = billerType;
  }

  public String getBillerPresence() {
    return billerPresence;
  }

  public void setBillerPresence(String billerPresence) {
    this.billerPresence = billerPresence;
  }

  public String getPaymentAllowedPostdueDate() {
    return paymentAllowedPostdueDate;
  }

  public void setPaymentAllowedPostdueDate(String paymentAllowedPostdueDate) {
    this.paymentAllowedPostdueDate = paymentAllowedPostdueDate;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("billerId", billerId).append("billerName", billerName)
        .append("billerCategoryTypeEnum", billerCategoryType).append("billerCategoryName", billerCategoryName)
        .append("billerCategoryId", billerCategoryId).append("partialPayment", partialPayment).append("dueAmount", dueAmount)
        .append("userIdentityInputs", userIdentityInputs).append("authenticators", authenticators).append("fillers", fillers)
        .append("billDate", billDate).append("billNumber", billNumber).append("payWithOutBill", payWithOutBill)
        .append("billDueDate", billDueDate).append("lastStatementBalance", lastStatementBalance).append("billerType", billerType)
        .append("billerPresence", billerPresence).append("paymentAllowedPostdueDate", paymentAllowedPostdueDate).toString();
  }
}

