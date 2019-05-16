package com.activeai.integration.banking.domain.response;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.activeai.integration.banking.constants.StatusEnum;
import com.activeai.integration.banking.model.Result;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response
 */
@Validated
public class Response {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("referenceId")
  private String referenceId;

  @JsonProperty("status")
  private StatusEnum status;

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Response{");
    sb.append("result=").append(result);
    sb.append(", referenceId='").append(referenceId).append('\'');
    sb.append(", status=").append(status);
    sb.append('}');
    return sb.toString();
  }
}

