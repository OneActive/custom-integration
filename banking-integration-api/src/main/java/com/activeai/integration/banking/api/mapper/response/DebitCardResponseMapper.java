package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.constants.PropertyConstants;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.DebitCardLimitConfirmResponse;
import com.activeai.integration.banking.domain.response.DebitCardLimitResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component("debitCardResponseMapper")
public class DebitCardResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the DebitCardLimitResponse
   *
   * @param apiResponseBody
   * @return String of DebitCardLimitResponse
   */
  public DebitCardLimitResponse getManipulatedDebitCardLimitResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, DebitCardLimitResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the DebitCardLimitConfirmResponse
   *
   * @param apiResponseBody
   * @return String of DebitCardLimitConfirmResponse
   */
  public DebitCardLimitConfirmResponse getManipulatedDebitCardLimitConfirmResponse(String apiResponseBody) {
    DebitCardLimitConfirmResponse debitCardLimitConfirmResponse =
        BankingIntegrationUtil.readValue(apiResponseBody, DebitCardLimitConfirmResponse.class);
    //For Random Generation of Reference Id
    debitCardLimitConfirmResponse.getCardDetail()
        .setReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    return debitCardLimitConfirmResponse;
  }
}
