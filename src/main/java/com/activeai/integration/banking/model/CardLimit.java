package com.activeai.integration.banking.model;

import java.util.Objects;

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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardLimit {\n");

    sb.append("    dailyLimit: ").append(toIndentedString(dailyLimit)).append("\n");
    sb.append("    overseasDailyLimit: ").append(toIndentedString(overseasDailyLimit)).append("\n");
    sb.append("    overseasMonthlyLimit: ").append(toIndentedString(overseasMonthlyLimit)).append("\n");
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

