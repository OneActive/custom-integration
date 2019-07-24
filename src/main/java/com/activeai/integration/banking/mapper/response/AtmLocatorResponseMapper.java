package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.AtmLocatorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component("atmLocatorResponseMapper")
public class AtmLocatorResponseMapper {
  @Autowired private ObjectMapper objectMapper;

  /**
   * In this method you can change the obtained string accordingly to the DepositServiceResponse
   * @param apiResponseBody
   * @return String of DepositServiceResponse
   */
  public AtmLocatorResponse getManipulatedAtmsResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, AtmLocatorResponse.class);
  }
}
