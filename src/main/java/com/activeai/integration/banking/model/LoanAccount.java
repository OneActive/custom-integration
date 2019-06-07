package com.activeai.integration.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class LoanAccount extends Account {

  @JsonProperty("originalPrincipalAmount")
  private Double originalPrincipalAmount;

  @JsonProperty("emiPaymentMethod")
  private String emiPaymentMethod;

  @JsonProperty("originalClosingDate")
  private String originalClosingDate;

  @JsonProperty("loanFundingDate")
  private String loanFundingDate;

  @JsonProperty("interestCalculationDate")
  private String interestCalculationDate;

  @JsonProperty("interestRate")
  private String interestRate;

  @JsonProperty("tenorTerm")
  private Double tenorTerm;

  @JsonProperty("tenorPeriod")
  private String tenorPeriod;

  @JsonProperty("maturityDate")
  private String maturityDate;

  @JsonProperty("interestAnnualPercentageRate")
  private String interestAnnualPercentageRate;

  public Double getOriginalPrincipalAmount() {
    return originalPrincipalAmount;
  }

  public void setOriginalPrincipalAmount(Double originalPrincipalAmount) {
    this.originalPrincipalAmount = originalPrincipalAmount;
  }

  public String getEmiPaymentMethod() {
    return emiPaymentMethod;
  }

  public void setEmiPaymentMethod(String emiPaymentMethod) {
    this.emiPaymentMethod = emiPaymentMethod;
  }

  public String getOriginalClosingDate() {
    return originalClosingDate;
  }

  public void setOriginalClosingDate(String originalClosingDate) {
    this.originalClosingDate = originalClosingDate;
  }

  public String getLoanFundingDate() {
    return loanFundingDate;
  }

  public void setLoanFundingDate(String loanFundingDate) {
    this.loanFundingDate = loanFundingDate;
  }

  public String getInterestCalculationDate() {
    return interestCalculationDate;
  }

  public void setInterestCalculationDate(String interestCalculationDate) {
    this.interestCalculationDate = interestCalculationDate;
  }

  public String getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(String interestRate) {
    this.interestRate = interestRate;
  }

  public Double getTenorTerm() {
    return tenorTerm;
  }

  public void setTenorTerm(Double tenorTerm) {
    this.tenorTerm = tenorTerm;
  }

  public String getTenorPeriod() {
    return tenorPeriod;
  }

  public void setTenorPeriod(String tenorPeriod) {
    this.tenorPeriod = tenorPeriod;
  }

  public String getMaturityDate() {
    return maturityDate;
  }

  public void setMaturityDate(String maturityDate) {
    this.maturityDate = maturityDate;
  }

  public String getInterestAnnualPercentageRate() {
    return interestAnnualPercentageRate;
  }

  public void setInterestAnnualPercentageRate(String interestAnnualPercentageRate) {
    this.interestAnnualPercentageRate = interestAnnualPercentageRate;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("LoanAccount{");
    sb.append("originalPrincipalAmount=").append(originalPrincipalAmount);
    sb.append(", emiPaymentMethod='").append(emiPaymentMethod).append('\'');
    sb.append(", originalClosingDate='").append(originalClosingDate).append('\'');
    sb.append(", loanFundingDate='").append(loanFundingDate).append('\'');
    sb.append(", interestCalculationDate='").append(interestCalculationDate).append('\'');
    sb.append(", interestRate='").append(interestRate).append('\'');
    sb.append(", tenorTerm=").append(tenorTerm);
    sb.append(", tenorPeriod='").append(tenorPeriod).append('\'');
    sb.append(", maturityDate='").append(maturityDate).append('\'');
    sb.append(", interestAnnualPercentageRate='").append(interestAnnualPercentageRate).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
