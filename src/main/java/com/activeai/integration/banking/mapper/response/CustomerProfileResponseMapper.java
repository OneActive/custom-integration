package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.CustomerProfileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("customerProfileResponseMapper")
public class CustomerProfileResponseMapper {

  @Autowired private ObjectMapper objectMapper;

  /**
   * In this method you can change the obtained string accordingly to the CustomerProfileResponse
   * @param apiResponseBody
   * @return String of CustomerProfileResponse
   */
  public CustomerProfileResponse getManipulatedCustomerProfileResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, CustomerProfileResponse.class);
  }

}
