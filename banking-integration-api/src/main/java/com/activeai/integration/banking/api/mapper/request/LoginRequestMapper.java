package com.activeai.integration.banking.api.mapper.request;

import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.request.UserLoginRequest;
import org.springframework.stereotype.Component;

@Component("loginRequestMapper")
public class LoginRequestMapper {

  /**
   * In this method you can change the obtained string accordingly to the UserLoginRequest
   * @param userLoginRequest
   * @return String of UserLoginRequest
   */
  public String getLoginRequestBody(UserLoginRequest userLoginRequest) {
    String userLoginRequestString = null;
    userLoginRequestString = BankingIntegrationUtil.writeValueAsString(userLoginRequest);
    return userLoginRequestString;
  }

}
