package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.domain.model.Payee;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * PayeesResponse
 */
@Validated
public class PayeesResponse extends Response {

  @JsonProperty("payees")
  private List<Payee> payees = null;

  /**
   * Get payees
   *
   * @return payees
   **/
  @Valid
  public List<Payee> getPayees() {
    return payees;
  }

  public void setPayees(List<Payee> payees) {
    this.payees = payees;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("payees", payees).toString();
  }
}

