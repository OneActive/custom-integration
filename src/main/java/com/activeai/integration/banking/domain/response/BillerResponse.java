package com.activeai.integration.banking.domain.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.activeai.integration.banking.model.Biller;
import com.activeai.integration.banking.model.Result;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BillerResponse
 */
@Validated
public class BillerResponse   {
  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("billers")
  @Valid
  private List<Biller> billers = null;

  /**
   * Get result
   * @return result
  **/
  @Valid
  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  /**
   * Get billers
   * @return billers
  **/
  @Valid
  public List<Biller> getBillers() {
    return billers;
  }

  public void setBillers(List<Biller> billers) {
    this.billers = billers;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("result", result).append("billers", billers).toString();
  }
}

