package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.Result;
import com.activeai.integration.banking.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LoginResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("user")
  private User user;

  @JsonProperty("customerSegments")
  private List<String> customerSegments;

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

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
    final StringBuffer sb = new StringBuffer("LoginResponse{");
    sb.append("result=").append(result);
    sb.append(", user=").append(user);
    sb.append(", customerSegments=").append(customerSegments);
    sb.append('}');
    return sb.toString();
  }
}
