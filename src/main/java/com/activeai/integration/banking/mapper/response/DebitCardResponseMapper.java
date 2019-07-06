package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.DebitCardLimitConfirmResponse;
import com.activeai.integration.banking.domain.response.DebitCardLimitResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("debitCardResponseMapper")
public class DebitCardResponseMapper {

  @Autowired private ObjectMapper objectMapper;

  /**
   * In this method you can change the obtained string accordingly to the DebitCardLimitResponse
   *
   * @param apiResponseBody
   * @return String of DebitCardLimitResponse
   */
  public DebitCardLimitResponse getManipulatedDebitCardLimitResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, DebitCardLimitResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the DebitCardLimitConfirmResponse
   *
   * @param apiResponseBody
   * @return String of DebitCardLimitConfirmResponse
   */
  public DebitCardLimitConfirmResponse getManipulatedDebitCardLimitConfirmResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, DebitCardLimitConfirmResponse.class);
  }
}
