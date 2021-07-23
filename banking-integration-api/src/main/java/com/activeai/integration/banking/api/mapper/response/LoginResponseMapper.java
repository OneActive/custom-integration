package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.LoginResponse;
import org.springframework.stereotype.Component;

@Component("loginResponseMapper")
public class LoginResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the LoginResponse
   *
   * @param apiResponseBody
   * @return String of LoginResponse
   */
  public LoginResponse getManipulatedLoginResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, LoginResponse.class);
  }

}
