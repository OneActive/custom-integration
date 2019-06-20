package com.activeai.integration.banking.mapper.response;

import org.springframework.stereotype.Component;

@Component("loginResponseMapper")
public class LoginResponseMapper {
  /**
   * In this method you can change the obtained string accordingly to the LoginResponse
   * @param apiResponseBody
   * @return String of LoginResponse
   */
  public String getManipulatedLoginResponse(String apiResponseBody) {
    return apiResponseBody;
  }

}
