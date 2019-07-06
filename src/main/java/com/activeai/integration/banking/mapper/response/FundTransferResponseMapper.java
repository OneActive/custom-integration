package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.FundTransferResponse;
import com.activeai.integration.banking.domain.response.PayeesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
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
  public FundTransferResponse getManipulatedFundTransferResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, FundTransferResponse.class);
  }
}
