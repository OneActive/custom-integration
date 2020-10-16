package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Response
 */
@Validated
public class Response {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("referenceId")
  private String referenceId = null;

  @JsonProperty("additionalProperties")
  private Map<String, Object> additionalProperties = new HashMap<>();

  public Response result(Result result) {
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
  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  @NotNull
  @Valid
  public String getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
  }

  /**
   * Get Additional Properties
   *
   * @return additionalProperties
   **/
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  public void setAdditionalProperties(Map<String, Object> additionalProperties) {
    this.additionalProperties = additionalProperties;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("result", result).append("referenceId", referenceId)
        .append("additionalProperties", additionalProperties).toString();
  }
}

