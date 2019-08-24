package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.ChequeBookOrderConfirmResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("checkBookResponseMapper")
public class CheckBookResponseMapper {


  @Autowired private ObjectMapper objectMapper;

  /**
   * In this method you can change the obtained string accordingly to the ChequeBookOrderConfirmResponse
   * @param apiResponseBody
   * @return ChequeBookOrderConfirmResponse
   */
  public ChequeBookOrderConfirmResponse getManipulatedCheckBookConfirmResponse(String apiResponseBody) throws IOException {
    ChequeBookOrderConfirmResponse chequeBookOrderConfirmResponse =
        objectMapper.readValue(apiResponseBody, ChequeBookOrderConfirmResponse.class);
    //For Random Generation of Reference Id
    chequeBookOrderConfirmResponse.getAccounts().get(0).setReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    return chequeBookOrderConfirmResponse;
  }
}
