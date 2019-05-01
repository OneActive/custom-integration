package com.activeai.integration.banking.mapper.response;

import org.springframework.stereotype.Component;

@Component("customerProfileResponseMapper")
public class CustomerProfileResponseMapper {


  /**
   * In this method you can change the obtained string accordingly to the CustomerProfileResponse
   * @param apiResponseBody
   * @return String of CustomerProfileResponse
   */
  public String getManipulatedCustomerProfileResponse(String apiResponseBody) {
    return apiResponseBody;
  }

}
