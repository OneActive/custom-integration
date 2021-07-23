package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.CustomerProfileResponse;
import org.springframework.stereotype.Component;

@Component("customerProfileResponseMapper")
public class CustomerProfileResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the CustomerProfileResponse
   *
   * @param apiResponseBody
   * @return String of CustomerProfileResponse
   */
  public CustomerProfileResponse getManipulatedCustomerProfileResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, CustomerProfileResponse.class);
  }

}
