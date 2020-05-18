package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.Biller;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * BillerResponse
 */
@Validated
public class BillerResponse extends Response {

  @JsonProperty("billers")
  @Valid
  private List<Biller> billers = null;

  /**
   * Get billers
   *
   * @return billers
   **/
  @Valid
  public List<Biller> getBillers() {
    return billers;
  }

  public void setBillers(List<Biller> billers) {
    this.billers = billers;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("billers", billers).toString();
  }
}

