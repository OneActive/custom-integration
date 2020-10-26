package com.activeai.integration.banking.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

public class UserLoginRequest {

  @NotNull
  @JsonProperty("userID")
  private String userID;

  @NotNull
  @JsonProperty("password")
  private String password;

  @JsonProperty("accessToken")
  private String accessToken;

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
