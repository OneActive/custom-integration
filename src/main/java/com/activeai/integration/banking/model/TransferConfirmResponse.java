package com.activeai.integration.banking.model;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * TransferConfirmResponse
 */
@Validated
public class TransferConfirmResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("txnReferenceId")
  private String txnReferenceId = null;

  /**
   * Status of transaction
   */
  public enum StatusEnum {
    DONE("DONE");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  public TransferConfirmResponse result(Result result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * 
   * @return result
   **/
  @Valid
  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public TransferConfirmResponse txnReferenceId(String txnReferenceId) {
    this.txnReferenceId = txnReferenceId;
    return this;
  }

  /**
   * Transaction Reference Id
   * 
   * @return txnReferenceId
   **/
  public String getTxnReferenceId() {
    return txnReferenceId;
  }

  public void setTxnReferenceId(String txnReferenceId) {
    this.txnReferenceId = txnReferenceId;
  }

  public TransferConfirmResponse status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Status of transaction
   * 
   * @return status
   **/
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransferConfirmResponse transferConfirmResponse = (TransferConfirmResponse) o;
    return Objects.equals(this.result, transferConfirmResponse.result)
        && Objects.equals(this.txnReferenceId, transferConfirmResponse.txnReferenceId)
        && Objects.equals(this.status, transferConfirmResponse.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, txnReferenceId, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransferConfirmResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    txnReferenceId: ").append(toIndentedString(txnReferenceId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

