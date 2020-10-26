package com.activeai.integration.banking.model;

import com.activeai.integration.banking.constants.PayeeStatusEnum;
import com.activeai.integration.banking.constants.PayeeTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Map;

/**
 * Payee
 */
@Validated
public class Payee {

  @JsonProperty("payeeId")
  private String payeeId = null;

  @JsonProperty("payeeName")
  private String payeeName = null;

  @JsonProperty("payeeNickName")
  private String payeeNickName = null;

  @JsonProperty("payeeBank")
  private String payeeBank = null;

  @JsonProperty("payeeBankBranch")
  private String payeeBankBranch = null;

  @JsonProperty("payeeBankIFSC")
  private String payeeBankIFSC = null;

  @JsonProperty("payeeBankSWIFT")
  private String payeeBankSWIFT = null;

  @JsonProperty("payeeAccountNo")
  private String payeeAccountNo = null;

  @JsonProperty("payeeMaskedAccountNo")
  private String payeeMaskedAccountNo = null;

  @JsonProperty("payeeAccountId")
  private String payeeAccountId = null;

  @JsonProperty("currency")
  private String currency = null;

  @JsonProperty("status")
  private PayeeStatusEnum status = null;

  @JsonProperty("payeeType")
  private PayeeTypeEnum payeeType = null;

  @JsonProperty("additionalProperties")
  private Map<String,Object> additionalProperties;

  public String getPayeeId() {
    return payeeId;
  }

  public void setPayeeId(String payeeId) {
    this.payeeId = payeeId;
  }

  /**
   * Payee ID
   * 
   * @return payeeId
   **/
  @Valid


  /**
   * Payee Name
   * 
   * @return payeeName
   **/
  public String getPayeeName() {
    return payeeName;
  }

  public void setPayeeName(String payeeName) {
    this.payeeName = payeeName;
  }

  /**
   * Payee Nick Name
   * 
   * @return payeeNickName
   **/
  public String getPayeeNickName() {
    return payeeNickName;
  }

  public void setPayeeNickName(String payeeNickName) {
    this.payeeNickName = payeeNickName;
  }

  /**
   * Payee Bank name
   * 
   * @return payeeBank
   **/
  public String getPayeeBank() {
    return payeeBank;
  }

  public void setPayeeBank(String payeeBank) {
    this.payeeBank = payeeBank;
  }

  /**
   * Payee Bank Branch
   * 
   * @return payeeBankBranch
   **/
  public String getPayeeBankBranch() {
    return payeeBankBranch;
  }

  public void setPayeeBankBranch(String payeeBankBranch) {
    this.payeeBankBranch = payeeBankBranch;
  }

  /**
   * IFSC Code of the branch where the account is opened
   * 
   * @return payeeBankIFSC
   **/
  public String getPayeeBankIFSC() {
    return payeeBankIFSC;
  }

  public void setPayeeBankIFSC(String payeeBankIFSC) {
    this.payeeBankIFSC = payeeBankIFSC;
  }

  /**
   * Payee Bank Swift code
   * 
   * @return payeeBankSWIFT
   **/
  public String getPayeeBankSWIFT() {
    return payeeBankSWIFT;
  }

  public void setPayeeBankSWIFT(String payeeBankSWIFT) {
    this.payeeBankSWIFT = payeeBankSWIFT;
  }

  /**
   * Payee account number
   * 
   * @return payeeAccountNo
   **/
  @Valid
  public String getPayeeAccountNo() {
    return payeeAccountNo;
  }

  public void setPayeeAccountNo(String payeeAccountNo) {
    this.payeeAccountNo = payeeAccountNo;
  }

  /**
   * Payee masked account number
   * 
   * @return payeeMaskedAccountNo
   **/
  public String getPayeeMaskedAccountNo() {
    return payeeMaskedAccountNo;
  }

  public void setPayeeMaskedAccountNo(String payeeMaskedAccountNo) {
    this.payeeMaskedAccountNo = payeeMaskedAccountNo;
  }

  /**
   * Payee account id
   * 
   * @return payeeAccountId
   **/
  @Valid
  public String getPayeeAccountId() {
    return payeeAccountId;
  }

  public void setPayeeAccountId(String payeeAccountId) {
    this.payeeAccountId = payeeAccountId;
  }

  /**
   * currency in which the account is opened
   * 
   * @return currency
   **/
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * account status - active, inactive
   * 
   * @return status
   **/
  public PayeeStatusEnum getStatus() {
    return status;
  }

  public void setStatus(PayeeStatusEnum status) {
    this.status = status;
  }

  /**
   * account status - active, inactive
   * 
   * @return payeeType
   **/
  public PayeeTypeEnum getPayeeType() {
    return payeeType;
  }

  public void setPayeeType(PayeeTypeEnum payeeType) {
    this.payeeType = payeeType;
  }

  /**
   * get Additional Properties
   *
   * @return additionalProperties
   */
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  public void setAdditionalProperties(Map<String, Object> additionalProperties) {
    this.additionalProperties = additionalProperties;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("payeeId", payeeId).append("payeeName", payeeName).append("payeeNickName", payeeNickName)
        .append("payeeBank", payeeBank).append("payeeBankBranch", payeeBankBranch).append("payeeBankIFSC", payeeBankIFSC)
        .append("payeeBankSWIFT", payeeBankSWIFT).append("payeeAccountNo", payeeAccountNo)
        .append("payeeMaskedAccountNo", payeeMaskedAccountNo).append("payeeAccountId", payeeAccountId).append("currency", currency)
        .append("status", status).append("payeeType", payeeType).append("additionalProperties", additionalProperties).toString();
  }
}

