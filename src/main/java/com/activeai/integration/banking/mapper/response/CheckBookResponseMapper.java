package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.ChequeBookOrderConfirmResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("checkBookResponseMapper")
public class CheckBookResponseMapper {


  @Autowired private ObjectMapper objectMapper;

  /**
   * In this method you can change the obtained string accordingly to the ChequeBookOrderConfirmResponse
   * @param apiResponseBody
   * @return ChequeBookOrderConfirmResponse
   */
  public ChequeBookOrderConfirmResponse getManipulatedCheckBookConfirmResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, ChequeBookOrderConfirmResponse.class);
  }
}
