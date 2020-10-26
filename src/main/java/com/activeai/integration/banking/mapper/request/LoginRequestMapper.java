package com.activeai.integration.banking.mapper.request;

import com.activeai.integration.banking.domain.request.UserLoginRequest;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("loginRequestMapper")
public class LoginRequestMapper {

  @Autowired
  private ObjectMapper objectMapper;

  /**
   * In this method you can change the obtained string accordingly to the UserLoginRequest
   * @param userLoginRequest
   * @return String of UserLoginRequest
   */
  public String getLoginRequestBody(UserLoginRequest userLoginRequest) {
    String userLoginRequestString = null;
    try {
      userLoginRequestString = objectMapper.writeValueAsString(userLoginRequest);
    } catch (IOException e) {
      ApplicationLogger.logError("Something went wrong while writing userLoginRequest as String" + ExceptionUtils.getStackTrace(e));
    }
    return userLoginRequestString;
  }

}
