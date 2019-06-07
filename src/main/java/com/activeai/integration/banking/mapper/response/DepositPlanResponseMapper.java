package com.activeai.integration.banking.mapper.response;

import org.springframework.stereotype.Component;

@Component("depositPlanResponseMapper")
public class DepositPlanResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the DepositServiceResponse
   * @param apiResponseBody
   * @return String of DepositServiceResponse
   */
  public String getManipulatedNomineeResponse(String apiResponseBody) {
    return apiResponseBody;
  }
  public String getManipulatedDepositPlanResponse(String apiResponseBody) {
    return apiResponseBody;
  }
  public String getManipulatedDepositPlanFinalApiCallResponse(String apiResponseBody) {
    return apiResponseBody;
  }


}
