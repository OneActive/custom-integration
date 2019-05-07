package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class LoginResponse{

  @JsonProperty("result")
  private Result result = null;

  @NotNull
  @JsonProperty("customerId")
  private String customerId;

  @JsonProperty("customerName")
  private String customerName;

  @JsonProperty("mobileNumber")
  private String mobileNumber;

  @JsonProperty("emailId")
  private String emailId;

  @JsonProperty("address")
  private String address;

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }
}
