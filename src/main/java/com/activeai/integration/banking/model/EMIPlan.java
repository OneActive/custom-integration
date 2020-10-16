package com.activeai.integration.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

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

  @JsonProperty("additionalProperties") private Map<String,Object> additionalProperties;

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

  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  public void setAdditionalProperties(Map<String, Object> additionalProperties) {
    this.additionalProperties = additionalProperties;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("amount", amount).append("interestRate", interestRate).append("processingFee", processingFee)
        .append("gst", gst).append("totalAmount", totalAmount).append("transactionSerialNumber", transactionSerialNumber)
        .append("tenure", tenure).append("emiAmount", emiAmount).append("additionalProperties", additionalProperties).toString();
  }
}
