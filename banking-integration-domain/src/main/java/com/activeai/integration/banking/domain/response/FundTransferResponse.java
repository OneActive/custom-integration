package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.domain.constants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * FundTransferResponse
 */
@Validated
public class FundTransferResponse extends Response {

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
    return new ToStringBuilder(this).append("txnReferenceId", txnReferenceId).append("transactionStatus", transactionStatus)
        .append("transactionFee", transactionFee).append("transferAmount", transferAmount)
        .append("isChargeTransactionFee", isChargeTransactionFee).toString();
  }
}

