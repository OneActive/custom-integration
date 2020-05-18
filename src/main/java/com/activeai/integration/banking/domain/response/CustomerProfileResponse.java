package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.constants.StatusEnum;
import com.activeai.integration.banking.model.CustomerProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * CustomerProfileResponse
 */
@Validated
public class CustomerProfileResponse extends Response {

  @JsonProperty("customerProfile")
  private CustomerProfile customerProfile = null;

  @JsonProperty("customerProfileUpdationStatus")
  private StatusEnum customerProfileUpdationStatus = null;

  /**
   * Get customerProfile
   *
   * @return customerProfile
   **/
  @Valid
  public CustomerProfile getCustomerProfile() {
    return customerProfile;
  }

  public void setCustomerProfile(CustomerProfile customerProfile) {
    this.customerProfile = customerProfile;
  }

  public StatusEnum getCustomerProfileUpdationStatus() {
    return customerProfileUpdationStatus;
  }

  public void setCustomerProfileUpdationStatus(StatusEnum customerProfileUpdationStatus) {
    this.customerProfileUpdationStatus = customerProfileUpdationStatus;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("customerProfile", customerProfile)
        .append("customerProfileUpdationStatus", customerProfileUpdationStatus).toString();
  }
}

