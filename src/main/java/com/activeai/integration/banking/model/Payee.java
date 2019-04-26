package com.activeai.integration.banking.model;

import java.util.Objects;

import javax.validation.Valid;

import com.activeai.integration.banking.constants.PayeeStatusEnum;
import com.activeai.integration.banking.constants.PayeeTypeEnum;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

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

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payee payee = (Payee) o;
    return Objects.equals(this.payeeId, payee.payeeId) && Objects.equals(this.payeeName, payee.payeeName)
        && Objects.equals(this.payeeNickName, payee.payeeNickName) && Objects.equals(this.payeeBank, payee.payeeBank)
        && Objects.equals(this.payeeBankBranch, payee.payeeBankBranch) && Objects.equals(this.payeeBankIFSC, payee.payeeBankIFSC)
        && Objects.equals(this.payeeBankSWIFT, payee.payeeBankSWIFT) && Objects.equals(this.payeeAccountNo, payee.payeeAccountNo)
        && Objects.equals(this.payeeMaskedAccountNo, payee.payeeMaskedAccountNo)
        && Objects.equals(this.payeeAccountId, payee.payeeAccountId) && Objects.equals(this.currency, payee.currency)
        && Objects.equals(this.status, payee.status) && Objects.equals(this.payeeType, payee.payeeType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payeeId, payeeName, payeeNickName, payeeBank, payeeBankBranch, payeeBankIFSC, payeeBankSWIFT, payeeAccountNo,
        payeeMaskedAccountNo, payeeAccountId, currency, status, payeeType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payee {\n");

    sb.append("    payeeId: ").append(toIndentedString(payeeId)).append("\n");
    sb.append("    payeeName: ").append(toIndentedString(payeeName)).append("\n");
    sb.append("    payeeNickName: ").append(toIndentedString(payeeNickName)).append("\n");
    sb.append("    payeeBank: ").append(toIndentedString(payeeBank)).append("\n");
    sb.append("    payeeBankBranch: ").append(toIndentedString(payeeBankBranch)).append("\n");
    sb.append("    payeeBankIFSC: ").append(toIndentedString(payeeBankIFSC)).append("\n");
    sb.append("    payeeBankSWIFT: ").append(toIndentedString(payeeBankSWIFT)).append("\n");
    sb.append("    payeeAccountNo: ").append(toIndentedString(payeeAccountNo)).append("\n");
    sb.append("    payeeMaskedAccountNo: ").append(toIndentedString(payeeMaskedAccountNo)).append("\n");
    sb.append("    payeeAccountId: ").append(toIndentedString(payeeAccountId)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    payeeType: ").append(toIndentedString(payeeType)).append("\n");
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

