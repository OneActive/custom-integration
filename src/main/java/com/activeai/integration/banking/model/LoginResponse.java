package com.activeai.integration.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse{

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("customerProfile")
  private CustomerProfile customerProfile;

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }
}
