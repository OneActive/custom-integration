package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.DepositServiceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("depositPlanResponseMapper")
public class DepositPlanResponseMapper {

  @Autowired private ObjectMapper objectMapper;

  /**
   * In this method you can change the obtained string accordingly to the DepositServiceResponse
   * @param apiResponseBody
   * @return String of DepositServiceResponse
   */
  public DepositServiceResponse getManipulatedNomineeResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, DepositServiceResponse.class);
  }

  public DepositServiceResponse getManipulatedDepositPlanResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, DepositServiceResponse.class);
  }

  public DepositServiceResponse getManipulatedDepositPlanFinalApiCallResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, DepositServiceResponse.class);
  }

}
