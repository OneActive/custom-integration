package com.activeai.integration.banking.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ErrorResult
 */
@Validated
public class ErrorResult {

  @JsonProperty("status")
  private Integer status = null;

  @JsonProperty("message")
  private String message = null;

  @JsonProperty("messageCode")
  private String messageCode = null;

  public ErrorResult status(Integer status) {
    this.status = status;
    return this;
  }

  /**
   * status code
   * 
   * @return status
   **/
  @NotNull
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public ErrorResult message(String message) {
    this.message = message;
    return this;
  }

  /**
   * message description
   * 
   * @return message
   **/
  @NotNull
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorResult messageCode(String messageCode) {
    this.messageCode = messageCode;
    return this;
  }

  /**
   * message code
   * 
   * @return messageCode
   **/
  @NotNull
  public String getMessageCode() {
    return messageCode;
  }

  public void setMessageCode(String messageCode) {
    this.messageCode = messageCode;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResult errorResult = (ErrorResult) o;
    return Objects.equals(this.status, errorResult.status) && Objects.equals(this.message, errorResult.message)
        && Objects.equals(this.messageCode, errorResult.messageCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, message, messageCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResult {\n");

    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    messageCode: ").append(toIndentedString(messageCode)).append("\n");
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

