package com.activeai.integration.banking.domain.response;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import com.activeai.integration.banking.constants.StatusEnum;
import com.activeai.integration.banking.model.Result;
import com.activeai.integration.banking.model.User;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * FundTransferResponse
 */
@Validated
public class FundTransferResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("txnReferenceId")
  private String txnReferenceId = null;

  @JsonProperty("transactionStatus")
  private StatusEnum transactionStatus = null;

  /** The transaction fee in case if sent by API, the same value will be displayed . */
  @JsonProperty("transactionFee")
  private Double transactionFee;

  /** The transfer amount. */
  @JsonProperty("transferAmount")
  private Double transferAmount;

  /** if fee applicable for this transaction,
   *    if transactionFee is set by API, this will be displayed to the user
   *    else transactionFee will be calculated by the system, based on business rules
   */
  @JsonProperty("isChargeTransactionFee")
  private boolean isChargeTransactionFee;

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public String getTxnReferenceId() {
    return txnReferenceId;
  }

  public void setTxnReferenceId(String txnReferenceId) {
    this.txnReferenceId = txnReferenceId;
  }

  public StatusEnum getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(StatusEnum transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  public Double getTransactionFee() {
    return transactionFee;
  }

  public void setTransactionFee(Double transactionFee) {
    this.transactionFee = transactionFee;
  }

  public Double getTransferAmount() {
    return transferAmount;
  }

  public void setTransferAmount(Double transferAmount) {
    this.transferAmount = transferAmount;
  }

  public boolean isChargeTransactionFee() {
    return isChargeTransactionFee;
  }

  public void setChargeTransactionFee(boolean chargeTransactionFee) {
    isChargeTransactionFee = chargeTransactionFee;
  }

  @Override public String toString() {
    final StringBuffer sb = new StringBuffer("FundTransferResponse{");
    sb.append("result=").append(result);
    sb.append(", txnReferenceId='").append(txnReferenceId).append('\'');
    sb.append(", transactionStatus=").append(transactionStatus);
    sb.append(", transactionFee=").append(transactionFee);
    sb.append(", transferAmount=").append(transferAmount);
    sb.append(", isChargeTransactionFee=").append(isChargeTransactionFee);
    sb.append('}');
    return sb.toString();
  }
}

