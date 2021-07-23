package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.constants.PropertyConstants;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component("cardsResponseMapper")
public class CardsResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the CardsResponse
   *
   * @param apiResponseBody
   * @return CardsResponse
   */
  public CardsResponse getManipulatedCardsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, CardsResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the CardTransactionsResponse
   *
   * @param apiResponseBody
   * @return String of CardTransactionsResponse
   */
  public CardTransactionsResponse getManipulatedCardTransactionsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, CardTransactionsResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the CardDetailsResponse
   *
   * @param apiResponseBody
   * @return String of CardDetailsResponse
   */
  public CardDetailResponse getManipulatedCardDetailsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, CardDetailResponse.class);
  }


  /**
   * In this method you can change the obtained string accordingly to the ResetPinConfirmResponse
   *
   * @param apiResponseBody
   * @return String of ResetPinConfirmResponse
   */
  public ResetPinConfirmResponse getManipulatedResetPinConfirmResponse(String apiResponseBody) {
    ResetPinConfirmResponse resetPinConfirmResponse = BankingIntegrationUtil.readValue(apiResponseBody, ResetPinConfirmResponse.class);
    //For Random generation of Reference Id
    resetPinConfirmResponse.getCardDetail().setReferenceId(
        RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    return resetPinConfirmResponse;
  }

  /**
   * In this method you can change the obtained string accordingly to the ReplaceCardConfirmResponse
   *
   * @param apiResponseBody
   * @return String of ReplaceCardConfirmResponse
   */
  public ReplaceCardConfirmResponse getManipulatedReplaceCardConfirmResponse(String apiResponseBody) {
    ReplaceCardConfirmResponse replaceCardConfirmResponse =
        BankingIntegrationUtil.readValue(apiResponseBody, ReplaceCardConfirmResponse.class);
    //For Random generation of Reference Id
    replaceCardConfirmResponse.getCardDetail().setReferenceId(
        RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    return replaceCardConfirmResponse;
  }

  public InternationalUsageResponse getManipulatedInternationalUsageResponse(String apiResponseBody) {
    InternationalUsageResponse internationalUsageResponse =
        BankingIntegrationUtil.readValue(apiResponseBody, InternationalUsageResponse.class);
    //For Random generation of Reference Id
    internationalUsageResponse.setReferenceId(
        RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    return internationalUsageResponse;
  }

  public ConvertEMIResponse getManipulatedConvertEMIResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, ConvertEMIResponse.class);
  }

  public ConvertEMIConfirmResponse getManipulatedConvertEMIConfirmResponse(String apiResponseBody) {
    ConvertEMIConfirmResponse convertEMIConfirmResponse =
        BankingIntegrationUtil.readValue(apiResponseBody, ConvertEMIConfirmResponse.class);
    //For Random generation of Reference Id
    convertEMIConfirmResponse.setReferenceId(
        RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    return convertEMIConfirmResponse;
  }

  public CardPaymentResponse getManipulatedCardPaymentResponse(String apiResponseBody) {
    CardPaymentResponse cardPaymentResponse = BankingIntegrationUtil.readValue(apiResponseBody, CardPaymentResponse.class);
    //For Random generation of Transaction Id
    cardPaymentResponse.setTxnReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    return cardPaymentResponse;
  }
}
