package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("loginResponseMapper")
public class LoginResponseMapper {

  @Autowired private ObjectMapper objectMapper;
  /**
   * In this method you can change the obtained string accordingly to the LoginResponse
   * @param apiResponseBody
   * @return String of LoginResponse
   */
  public LoginResponse getManipulatedLoginResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, LoginResponse.class);
  }

}
