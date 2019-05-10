package com.activeai.integration.banking.mapper.response;

import org.springframework.stereotype.Component;

@Component("fundTransferResponseMapper")
public class FundTransferResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the PayeesResponse
   * @param apiResponseBody
   * @return String of PayeesResponse
   */
  public String getManipulatedPayeesResponse(String apiResponseBody) {
    return apiResponseBody;
  }

  /**
   * In this method you can change the obtained string accordingly to the FundTransferResponse
   * @param apiResponseBody
   * @return String of FundTransferResponse
   */
  public String getManipulatedFundTransferResponse(String apiResponseBody) {
    return apiResponseBody;
  }
}
