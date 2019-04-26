package com.activeai.integration.banking.domain.request;

import java.util.List;

public class FundTransferRequest {

  /** IMPS, RTGS, NEFT. */
  private String type;

  /** Beneficiary of type own, third-party or inter-bank. */
  private String beneficiaryType;

  /**
   * source account to debit from
   */
  private String sourceAccountId;

  /** amount to transfer. */
  private String amount;

  /** purpose of transfer. */
  private String purpose;

  /**
   * selected Payee Id
   */
  private String payeeId;

  /** transaction remarks if any. */
  private String remarks;

  /** The payee type. */
  private String payeeType;

  /** The payee IFSC code. */
  private String payeeIFSCCode;

  /** The payee bank id. */
  private String payeeBankId;

  /** The payee branch id. */
  private String payeeBranchId;

  /**
   * Account Id incase if this is different from account number
   */
  private String payeeAccountId;

  /**
   * Account Number to which amount will be credited
   */
  private String payeeAccountNumber;

  /**
   * In case of Quick transfer, the mobile number to which amount will be credited
   */
  private String payeeMobileNumber;

  /**
   * In case of Quick transfer, the emailId to which amount will be credited
   */
  private String payeeEmailId;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBeneficiaryType() {
    return beneficiaryType;
  }

  public void setBeneficiaryType(String beneficiaryType) {
    this.beneficiaryType = beneficiaryType;
  }

  public String getSourceAccountId() {
    return sourceAccountId;
  }

  public void setSourceAccountId(String sourceAccountId) {
    this.sourceAccountId = sourceAccountId;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public String getPayeeId() {
    return payeeId;
  }

  public void setPayeeId(String payeeId) {
    this.payeeId = payeeId;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getPayeeType() {
    return payeeType;
  }

  public void setPayeeType(String payeeType) {
    this.payeeType = payeeType;
  }

  public String getPayeeIFSCCode() {
    return payeeIFSCCode;
  }

  public void setPayeeIFSCCode(String payeeIFSCCode) {
    this.payeeIFSCCode = payeeIFSCCode;
  }

  public String getPayeeBankId() {
    return payeeBankId;
  }

  public void setPayeeBankId(String payeeBankId) {
    this.payeeBankId = payeeBankId;
  }

  public String getPayeeBranchId() {
    return payeeBranchId;
  }

  public void setPayeeBranchId(String payeeBranchId) {
    this.payeeBranchId = payeeBranchId;
  }

  public String getPayeeAccountId() {
    return payeeAccountId;
  }

  public void setPayeeAccountId(String payeeAccountId) {
    this.payeeAccountId = payeeAccountId;
  }

  public String getPayeeAccountNumber() {
    return payeeAccountNumber;
  }

  public void setPayeeAccountNumber(String payeeAccountNumber) {
    this.payeeAccountNumber = payeeAccountNumber;
  }

  public String getPayeeMobileNumber() {
    return payeeMobileNumber;
  }

  public void setPayeeMobileNumber(String payeeMobileNumber) {
    this.payeeMobileNumber = payeeMobileNumber;
  }

  public String getPayeeEmailId() {
    return payeeEmailId;
  }

  public void setPayeeEmailId(String payeeEmailId) {
    this.payeeEmailId = payeeEmailId;
  }
}
