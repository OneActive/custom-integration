package com.activeai.integration.banking.model;

import java.util.Objects;

import com.activeai.integration.banking.constants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CardLimit
 */
@Validated
public class CardLimit {

  @JsonProperty("dailyLimit")
  private Double dailyLimit = null;

  @JsonProperty("overseasDailyLimit")
  private Double overseasDailyLimit = null;

  @JsonProperty("overseasMonthlyLimit")
  private Double overseasMonthlyLimit = null;

  @JsonProperty("responseCode")
  private String responseCode;

  @JsonProperty("newDomesticATMLimit")
  private String newDomesticATMLimit;

  @JsonProperty("newDomesticPOSLimit")
  private String newDomesticPOSLimit;

  @JsonProperty("newInternationalPOSLimit")
  private String newInternationalPOSLimit;

  @JsonProperty("newInternationalATMLimit")
  private String newInternationalATMLimit;

  @JsonProperty("newCreditLimit")
  private String newCreditLimit;

  @JsonProperty("newCashLimit")
  private String newCashLimit;

  @JsonProperty("referenceId")
  private String referenceId;

  @JsonProperty("transactionStatus")
  private StatusEnum transactionStatus;


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

  public CardLimit dailyLimit(Double dailyLimit) {
    this.dailyLimit = dailyLimit;
    return this;
  }

  /**
   * daily limit
   * 
   * @return dailyLimit
   **/
  public Double getDailyLimit() {
    return dailyLimit;
  }

  public void setDailyLimit(Double dailyLimit) {
    this.dailyLimit = dailyLimit;
  }

  public CardLimit overseasDailyLimit(Double overseasDailyLimit) {
    this.overseasDailyLimit = overseasDailyLimit;
    return this;
  }

  /**
   * overseas daily limit example = "8.097541212E7"
   * 
   * @return overseasDailyLimit
   **/
  public Double getOverseasDailyLimit() {
    return overseasDailyLimit;
  }

  public void setOverseasDailyLimit(Double overseasDailyLimit) {
    this.overseasDailyLimit = overseasDailyLimit;
  }

  public CardLimit overseasMonthlyLimit(Double overseasMonthlyLimit) {
    this.overseasMonthlyLimit = overseasMonthlyLimit;
    return this;
  }

  /**
   * overseas monthly limit example = "8.097541212E7"
   * 
   * @return overseasMonthlyLimit
   **/
  public Double getOverseasMonthlyLimit() {
    return overseasMonthlyLimit;
  }

  public void setOverseasMonthlyLimit(Double overseasMonthlyLimit) {
    this.overseasMonthlyLimit = overseasMonthlyLimit;
  }

  /**
   * responseCode
   *
   * @return responseCode
   **/
  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  /**
   * newDomesticATMLimit
   *
   * @return newDomesticATMLimit
   **/
  public String getNewDomesticATMLimit() {
    return newDomesticATMLimit;
  }

  public void setNewDomesticATMLimit(String newDomesticATMLimit) {
    this.newDomesticATMLimit = newDomesticATMLimit;
  }

  /**
   * newDomesticPOSLimit
   *
   * @return newDomesticPOSLimit
   **/
  public String getNewDomesticPOSLimit() {
    return newDomesticPOSLimit;
  }

  public void setNewDomesticPOSLimit(String newDomesticPOSLimit) {
    this.newDomesticPOSLimit = newDomesticPOSLimit;
  }

  /**
   * newInternationalPOSLimit
   *
   * @return newInternationalPOSLimit
   **/
  public String getNewInternationalPOSLimit() {
    return newInternationalPOSLimit;
  }

  public void setNewInternationalPOSLimit(String newInternationalPOSLimit) {
    this.newInternationalPOSLimit = newInternationalPOSLimit;
  }

  /**
   *newInternationalATMLimit
   *
   * @return newInternationalATMLimit
   **/
  public String getNewInternationalATMLimit() {
    return newInternationalATMLimit;
  }

  public void setNewInternationalATMLimit(String newInternationalATMLimit) {
    this.newInternationalATMLimit = newInternationalATMLimit;
  }

  public String getNewCreditLimit() {
    return newCreditLimit;
  }

  public void setNewCreditLimit(String newCreditLimit) {
    this.newCreditLimit = newCreditLimit;
  }

  public String getNewCashLimit() {
    return newCashLimit;
  }

  public void setNewCashLimit(String newCashLimit) {
    this.newCashLimit = newCashLimit;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardLimit cardLimit = (CardLimit) o;
    return Objects.equals(this.dailyLimit, cardLimit.dailyLimit) && Objects.equals(this.overseasDailyLimit, cardLimit.overseasDailyLimit)
        && Objects.equals(this.overseasMonthlyLimit, cardLimit.overseasMonthlyLimit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dailyLimit, overseasDailyLimit, overseasMonthlyLimit);
  }


  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("CardLimit{");
    sb.append("dailyLimit=").append(dailyLimit);
    sb.append(", overseasDailyLimit=").append(overseasDailyLimit);
    sb.append(", overseasMonthlyLimit=").append(overseasMonthlyLimit);
    sb.append(", responseCode='").append(responseCode).append('\'');
    sb.append(", newDomesticATMLimit='").append(newDomesticATMLimit).append('\'');
    sb.append(", newDomesticPOSLimit='").append(newDomesticPOSLimit).append('\'');
    sb.append(", newInternationalPOSLimit='").append(newInternationalPOSLimit).append('\'');
    sb.append(", newInternationalATMLimit='").append(newInternationalATMLimit).append('\'');
    sb.append(", newCreditLimit='").append(newCreditLimit).append('\'');
    sb.append(", newCashLimit='").append(newCashLimit).append('\'');
    sb.append(", transactionStatus='").append(transactionStatus).append('\'');
    sb.append(", referenceId='").append(referenceId).append('\'');
    sb.append('}');
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

