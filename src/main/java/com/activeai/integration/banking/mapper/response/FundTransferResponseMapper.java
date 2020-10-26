package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.FundTransferRequest;
import com.activeai.integration.banking.domain.response.FundTransferResponse;
import com.activeai.integration.banking.domain.response.PayeesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("fundTransferResponseMapper")
public class FundTransferResponseMapper {

  @Autowired private ObjectMapper objectMapper;
  /**
   * In this method you can change the obtained string accordingly to the PayeesResponse
   * @param apiResponseBody
   * @return PayeesResponse
   */
  public PayeesResponse getManipulatedPayeesResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, PayeesResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the FundTransferResponse
   * @param apiResponseBody
   * @return FundTransferResponse
   */
  public FundTransferResponse getManipulatedFundTransferResponse(String apiResponseBody, FundTransferRequest fundTransferRequest)
      throws IOException {
    FundTransferResponse fundTransferResponse = objectMapper.readValue(apiResponseBody, FundTransferResponse.class);
    //For Random Generation of Reference Id
    fundTransferResponse.setTxnReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    fundTransferResponse.setTransferAmount(Double.valueOf(fundTransferRequest.getAmount()));
    return fundTransferResponse;
  }
}
