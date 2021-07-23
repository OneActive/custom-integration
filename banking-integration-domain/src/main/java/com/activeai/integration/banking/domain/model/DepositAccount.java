package com.activeai.integration.banking.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class DepositAccount extends Account {

  /**
   * Maturity Date.
   */
  @JsonProperty("maturityDate")
  private String maturityDate = null;

  /**
   * Maturity amount.
   */
  @JsonProperty("maturityAmount")
  private Double maturityAmount = null;



  /**
   * Maturity Instruction.
   */
  @JsonProperty("maturityInstruction")
  private String maturityInstruction = null;

  /**
   * Initial principal amount.
   */
  @JsonProperty("initialPrincipalAmount")
  private Double initialPrincipalAmount = null;

  /**
   * Interest Rate applied.
   */
  @JsonProperty("interestRate")
  private String interestRate = null;

  /**
   * Tenor term like 12.
   */
  @JsonProperty("tenorTerm")
  private Double tenorTerm = null;

  /**
   * Tenor period like in MONTHS, YEARS.
   */
  @JsonProperty("tenorPeriod")
  private String tenorPeriod = null;

  /**
   * Total interest earned so far.
   */
  @JsonProperty("totalInterestAmountEarned")
  private Double totalInterestAmountEarned = null;

  /**
   * Last date of renewal.
   */
  @JsonProperty("lastRenewalDate")
  private String lastRenewalDate = null;

  @JsonProperty("earlyWithdrawalFee")
  private Double earlyWithdrawalFee = null;
  /***/
  @JsonProperty("earlyWithdrawalAmount")
  private Double earlyWithdrawalAmount = null;

  public Double getEarlyWithdrawalFee() {
    return earlyWithdrawalFee;
  }

  public void setEarlyWithdrawalFee(Double earlyWithdrawalFee) {
    this.earlyWithdrawalFee = earlyWithdrawalFee;
  }

  public Double getEarlyWithdrawalAmount() {
    return earlyWithdrawalAmount;
  }

  public void setEarlyWithdrawalAmount(Double earlyWithdrawalAmount) {
    this.earlyWithdrawalAmount = earlyWithdrawalAmount;
  }

  /**
   * name for the Deposit
   */
  @JsonProperty("depositName")
  private String depositName = null;

  /**
   * @return
   */
  public String getMaturityDate() {
    return maturityDate;
  }

  /**
   * @param maturityDate
   */
  public void setMaturityDate(String maturityDate) {
    this.maturityDate = maturityDate;
  }

  /**
   * @return
   */
  public String getMaturityInstruction() {
    return maturityInstruction;
  }

  /**
   * @param maturityInstruction
   */
  public void setMaturityInstruction(String maturityInstruction) {
    this.maturityInstruction = maturityInstruction;
  }

  /**
   * @return
   */
  public Double getInitialPrincipalAmount() {
    return initialPrincipalAmount;
  }

  /**
   * @param initialPrincipalAmount
   */
  public void setInitialPrincipalAmount(Double initialPrincipalAmount) {
    this.initialPrincipalAmount = initialPrincipalAmount;
  }

  /**
   * @return
   */
  public String getInterestRate() {
    return interestRate;
  }

  /**
   * @param interestRate
   */
  public void setInterestRate(String interestRate) {
    this.interestRate = interestRate;
  }

  /**
   * @return
   */
  public Double getTenorTerm() {
    return tenorTerm;
  }

  /**
   * @param tenorTerm
   */
  public void setTenorTerm(Double tenorTerm) {
    this.tenorTerm = tenorTerm;
  }

  /**
   * @return
   */
  public String getTenorPeriod() {
    return tenorPeriod;
  }

  /**
   * @param tenorPeriod
   */
  public void setTenorPeriod(String tenorPeriod) {
    this.tenorPeriod = tenorPeriod;
  }

  /**
   * @return
   */
  public Double getTotalInterestAmountEarned() {
    return totalInterestAmountEarned;
  }

  /**
   * @param totalInterestAmountEarned
   */
  public void setTotalInterestAmountEarned(Double totalInterestAmountEarned) {
    this.totalInterestAmountEarned = totalInterestAmountEarned;
  }

  /**
   * @return
   */
  public String getLastRenewalDate() {
    return lastRenewalDate;
  }

  /**
   * @param lastRenewalDate
   */
  public void setLastRenewalDate(String lastRenewalDate) {
    this.lastRenewalDate = lastRenewalDate;
  }

  /**
   * @return
   */
  public String getDepositName() {
    return depositName;
  }

  /**
   * @param depositName
   */
  public void setDepositName(String depositName) {
    this.depositName = depositName;
  }

  public Double getMaturityAmount() {
    return maturityAmount;
  }

  public void setMaturityAmount(Double maturityAmount) {
    this.maturityAmount = maturityAmount;

  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("DepositAccount{");
    sb.append("maturityDate='").append(maturityDate).append('\'');
    sb.append(", maturityAmount=").append(maturityAmount);
    sb.append(", maturityInstruction='").append(maturityInstruction).append('\'');
    sb.append(", initialPrincipalAmount=").append(initialPrincipalAmount);
    sb.append(", interestRate='").append(interestRate).append('\'');
    sb.append(", tenorTerm=").append(tenorTerm);
    sb.append(", tenorPeriod='").append(tenorPeriod).append('\'');
    sb.append(", totalInterestAmountEarned=").append(totalInterestAmountEarned);
    sb.append(", lastRenewalDate='").append(lastRenewalDate).append('\'');
    sb.append(", earlyWithdrawalFee=").append(earlyWithdrawalFee);
    sb.append(", earlyWithdrawalAmount=").append(earlyWithdrawalAmount);
    sb.append(", depositName='").append(depositName).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
