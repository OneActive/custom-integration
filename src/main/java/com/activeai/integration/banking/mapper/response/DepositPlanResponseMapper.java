package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.DepositServiceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
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

  public DepositServiceResponse getManipulatedDepositPlanFinalApiCallResponse(String apiResponseBody) throws IOException {
    DepositServiceResponse depositServiceResponse = objectMapper.readValue(apiResponseBody, DepositServiceResponse.class);
    //For Random Generation of Reference Id
    depositServiceResponse.setReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    return depositServiceResponse;
  }

}
