package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.constants.PropertyConstants;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.DepositServiceResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component("depositPlanResponseMapper")
public class DepositPlanResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the DepositServiceResponse
   *
   * @param apiResponseBody
   * @return String of DepositServiceResponse
   */
  public DepositServiceResponse getManipulatedNomineeResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, DepositServiceResponse.class);
  }

  public DepositServiceResponse getManipulatedDepositPlanResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, DepositServiceResponse.class);
  }

  public DepositServiceResponse getManipulatedDepositPlanFinalApiCallResponse(String apiResponseBody) {
    DepositServiceResponse depositServiceResponse = BankingIntegrationUtil.readValue(apiResponseBody, DepositServiceResponse.class);
    //For Random Generation of Reference Id
    depositServiceResponse.setReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    return depositServiceResponse;
  }

}
