package com.activeai.integration.banking.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * User
 */
@Validated
public class User {

  /**
   * Unique ID for each customer which given by bank
   * helps to store and access customer details
   */
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

  @JsonProperty("accessToken")
  private String accessToken;

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

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

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("customerId='").append(customerId).append('\'');
    sb.append(", customerName='").append(customerName).append('\'');
    sb.append(", mobileNumber='").append(mobileNumber).append('\'');
    sb.append(", emailId='").append(emailId).append('\'');
    sb.append(", address='").append(address).append('\'');
    sb.append(", accessToken='").append(accessToken).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

