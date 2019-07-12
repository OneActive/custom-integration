package com.activeai.integration.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("DepositPlan{");
    sb.append("amount=").append(amount);
    sb.append(", tenure='").append(tenure).append('\'');
    sb.append(", interestRate='").append(interestRate).append('\'');
    sb.append(", maturityAmount=").append(maturityAmount).append('\'');
    sb.append(", minAmount=").append(minAmount);
    sb.append('}');
    return sb.toString();
  }
}
