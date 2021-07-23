package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.constants.PropertyConstants;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.ChequeBookOrderConfirmResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component("checkBookResponseMapper")
public class CheckBookResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the ChequeBookOrderConfirmResponse
   *
   * @param apiResponseBody
   * @return ChequeBookOrderConfirmResponse
   */
  public ChequeBookOrderConfirmResponse getManipulatedCheckBookConfirmResponse(String apiResponseBody) {
    ChequeBookOrderConfirmResponse chequeBookOrderConfirmResponse =
        BankingIntegrationUtil.readValue(apiResponseBody, ChequeBookOrderConfirmResponse.class);
    //For Random Generation of Reference Id
    chequeBookOrderConfirmResponse.getAccounts().get(0)
        .setReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    return chequeBookOrderConfirmResponse;
  }
}
