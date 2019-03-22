package com.activeai.integration.banking.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ErrorResponse
 */
@Validated
public class ErrorResponse {

  @JsonProperty("result")
  private ErrorResult result = null;

  public ErrorResponse result(ErrorResult result) {
    this.result = result;
    return this;
  }

  /**
   * Get result
   * 
   * @return result
   **/
  @NotNull
  @Valid
  public ErrorResult getResult() {
    return result;
  }

  public void setResult(ErrorResult result) {
    this.result = result;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.result, errorResponse.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
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

