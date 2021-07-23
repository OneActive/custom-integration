package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.domain.constants.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

@Validated
public class BlockCardResponse extends Response {

  @JsonProperty("status")
  private StatusEnum status;

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("status", status).toString();
  }
}
