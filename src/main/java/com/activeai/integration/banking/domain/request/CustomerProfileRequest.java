package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.model.CustomerProfile;
import com.activeai.integration.banking.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerProfileRequest extends User {

  @JsonProperty("customerProfile")
  private CustomerProfile customerProfile;

  public CustomerProfile getCustomerProfile() {
    return customerProfile;
  }

  public void setCustomerProfile(CustomerProfile customerProfile) {
    this.customerProfile = customerProfile;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("CustomerProfileRequest{");
    sb.append("customerProfile=").append(customerProfile);
    sb.append('}');
    return sb.toString();
  }
}
