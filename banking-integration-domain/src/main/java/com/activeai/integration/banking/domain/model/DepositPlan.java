package com.activeai.integration.banking.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

public class DepositPlan {

  /*deposit amount*/
  @JsonProperty("amount") private Double amount = null;

  /*tenure of deposit*/
  @JsonProperty("tenure") private Tenure tenure = null;

  /*interest rate based on amount and tenure*/
  @JsonProperty("interestRate") private String interestRate = null;

  /*total amount get after tenure*/
  @JsonProperty("maturityAmount") private Double maturityAmount = null;

  /*total min amount to be deposited*/
  @JsonProperty("minAmount")
  private Double minAmount = null;

  /*additional Properties*/
  @JsonProperty("additionalProperties")
  private Map<String,Object> additionalProperties;

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Tenure getTenure() {
    return tenure;
  }

  public void setTenure(Tenure tenure) {
    this.tenure = tenure;
  }

  public String getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(String interestRate) {
    this.interestRate = interestRate;
  }

  public Double getMaturityAmount() {
    return maturityAmount;
  }

  public void setMaturityAmount(Double maturityAmount) {
    this.maturityAmount = maturityAmount;
  }


  public Double getMinAmount() {
    return minAmount;
  }

  public void setMinAmount(Double minAmount) {
    this.minAmount = minAmount;
  }

  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  public void setAdditionalProperties(Map<String, Object> additionalProperties) {
    this.additionalProperties = additionalProperties;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("amount", amount).append("tenure", tenure).append("interestRate", interestRate)
        .append("maturityAmount", maturityAmount).append("minAmount", minAmount).append("additionalProperties", additionalProperties)
        .toString();
  }
}
