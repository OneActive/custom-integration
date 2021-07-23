package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.constants.PropertyConstants;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.request.FundTransferRequest;
import com.activeai.integration.banking.domain.response.FundTransferResponse;
import com.activeai.integration.banking.domain.response.PayeesResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component("fundTransferResponseMapper")
public class FundTransferResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the PayeesResponse
   *
   * @param apiResponseBody
   * @return PayeesResponse
   */
  public PayeesResponse getManipulatedPayeesResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, PayeesResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the FundTransferResponse
   *
   * @param apiResponseBody
   * @return FundTransferResponse
   */
  public FundTransferResponse getManipulatedFundTransferResponse(String apiResponseBody, FundTransferRequest fundTransferRequest) {
    FundTransferResponse fundTransferResponse = BankingIntegrationUtil.readValue(apiResponseBody, FundTransferResponse.class);
    //For Random Generation of Reference Id
    fundTransferResponse.setTxnReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    fundTransferResponse.setTransferAmount(Double.valueOf(fundTransferRequest.getAmount()));
    return fundTransferResponse;
  }
}
