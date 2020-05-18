package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class LoginResponse extends Response {

  @JsonProperty("user")
  private User user;

  @JsonProperty("customerSegments")
  private List<String> customerSegments;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<String> getCustomerSegments() {
    return customerSegments;
  }

  public void setCustomerSegments(List<String> customerSegments) {
    this.customerSegments = customerSegments;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("user", user).append("customerSegments", customerSegments).toString();
  }
}
