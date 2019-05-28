package com.activeai.integration.banking.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import com.activeai.integration.banking.constants.BillerCategoryTypeEnum;
import com.activeai.integration.banking.constants.PartialPaymentStatus;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Biller
 */
@Validated
public class Biller {

  @JsonProperty("billerId")
  private String billerId = null;

  @JsonProperty("billerName")
  private String billerName = null;

  @JsonProperty("billerCategory")
  private BillerCategoryTypeEnum billerCategory;

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

  /**
   * Biller category
   *
   * @return billerCategory
   **/
  public BillerCategoryTypeEnum getBillerCategory() {
    return billerCategory;
  }

  public void setBillerCategory(BillerCategoryTypeEnum billerCategory) {
    this.billerCategory = billerCategory;
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

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Biller biller = (Biller) o;
    return Objects.equals(this.billerId, biller.billerId) && Objects.equals(this.billerName, biller.billerName)
        && Objects.equals(this.billerCategory, biller.billerCategory) && Objects.equals(this.billerCategoryId, biller.billerCategoryId) && Objects.equals(this.dueAmount, biller.dueAmount) && Objects.equals(this.userIdentityInputs, biller.userIdentityInputs) && Objects.equals(this.partialPayment, biller.partialPayment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(billerId, billerName, billerCategory,billerCategoryId, billNumber,billDate,billDueDate,authenticators,fillers,dueAmount,userIdentityInputs,partialPayment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Biller {\n");

    sb.append("    billerId: ").append(toIndentedString(billerId)).append("\n");
    sb.append("    billerName: ").append(toIndentedString(billerName)).append("\n");
    sb.append("    billerCategory: ").append(toIndentedString(billerCategory)).append("\n");
    sb.append("    billerCategoryId: ").append(toIndentedString(billerCategoryId)).append("\n");
    sb.append("    dueAmount: ").append(toIndentedString(dueAmount)).append("\n");
    sb.append("    userIdentityInputs: ").append(toIndentedString(userIdentityInputs)).append("\n");
    sb.append("    allowPartialPay: ").append(toIndentedString(partialPayment)).append("\n");
    sb.append("    authenticators: ").append(toIndentedString(authenticators)).append("\n");
    sb.append("    billDueDate: ").append(toIndentedString(billDueDate)).append("\n");
    sb.append("    billDate: ").append(toIndentedString(billDate)).append("\n");
    sb.append("    billNumber: ").append(toIndentedString(billNumber)).append("\n");
    sb.append("    fillers: ").append(toIndentedString(fillers)).append("\n");
    sb.append("    lastStatementBalance: ").append(toIndentedString(lastStatementBalance)).append("\n");
    sb.append("    billerType: ").append(toIndentedString(billerType)).append("\n");
    sb.append("    billerPresence: ").append(toIndentedString(billerPresence)).append("\n");
    sb.append("    paymentAllowedPostdueDate: ").append(toIndentedString(paymentAllowedPostdueDate)).append("\n");

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

