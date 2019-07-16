package com.activeai.integration.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EMIPlan {

  @JsonProperty("amount") private Double amount;

  /*interest rate based on amount and tenure*/
  @JsonProperty("interestRate") private String interestRate;

  @JsonProperty("processingFee") private Double processingFee;

  @JsonProperty("gst") private Double gst;

  /* total emi amount*/
  @JsonProperty("totalAmount") private Double totalAmount;

  @JsonProperty("transactionSerialNumber") private String transactionSerialNumber;

  @JsonProperty("tenure") private Tenure tenure;

  @JsonProperty("emiAmount") private Double emiAmount;

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(String interestRate) {
    this.interestRate = interestRate;
  }

  public Double getProcessingFee() {
    return processingFee;
  }

  public void setProcessingFee(Double processingFee) {
    this.processingFee = processingFee;
  }

  public Double getGst() {
    return gst;
  }

  public void setGst(Double gst) {
    this.gst = gst;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getTransactionSerialNumber() {
    return transactionSerialNumber;
  }

  public void setTransactionSerialNumber(String transactionSerialNumber) {
    this.transactionSerialNumber = transactionSerialNumber;
  }

  public Tenure getTenure() {
    return tenure;
  }

  public void setTenure(Tenure tenure) {
    this.tenure = tenure;
  }

  public Double getEmiAmount() {
    return emiAmount;
  }

  public void setEmiAmount(Double emiAmount) {
    this.emiAmount = emiAmount;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("EMIPlan{");
    sb.append("amount=").append(amount);
    sb.append(", interestRate='").append(interestRate).append('\'');
    sb.append(", processingFee=").append(processingFee);
    sb.append(", gst=").append(gst);
    sb.append(", totalAmount=").append(totalAmount);
    sb.append(", transactionSerialNumber='").append(transactionSerialNumber).append('\'');
    sb.append(", tenure=").append(tenure);
    sb.append(", emiAmount=").append(emiAmount);
    sb.append('}');
    return sb.toString();
  }
}
