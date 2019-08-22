package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.*;
import com.activeai.integration.banking.model.CardLimit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("cardsResponseMapper") public class CardsResponseMapper {

  @Autowired private ObjectMapper objectMapper;

  /**
   * In this method you can change the obtained string accordingly to the CardsResponse
   *
   * @param apiResponseBody
   * @return CardsResponse
   */
  public CardsResponse getManipulatedCardsResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, CardsResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the CardTransactionsResponse
   *
   * @param apiResponseBody
   * @return String of CardTransactionsResponse
   */
  public CardTransactionsResponse getManipulatedCardTransactionsResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, CardTransactionsResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the CardDetailsResponse
   *
   * @param apiResponseBody
   * @return String of CardDetailsResponse
   */
  public CardDetailResponse getManipulatedCardDetailsResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, CardDetailResponse.class);
  }


  /**
   * In this method you can change the obtained string accordingly to the ResetPinConfirmResponse
   *
   * @param apiResponseBody
   * @return String of ResetPinConfirmResponse
   */
  public ResetPinConfirmResponse getManipulatedResetPinConfirmResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, ResetPinConfirmResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the ReplaceCardConfirmResponse
   *
   * @param apiResponseBody
   * @return String of ReplaceCardConfirmResponse
   */
  public ReplaceCardConfirmResponse getManipulatedReplaceCardConfirmResponse(String apiResponseBody) throws IOException {
    ReplaceCardConfirmResponse replaceCardConfirmResponse = objectMapper.readValue(apiResponseBody, ReplaceCardConfirmResponse.class);
    replaceCardConfirmResponse.getCardDetail().setReferenceId(RandomStringUtils.random(8, true, true));
    return replaceCardConfirmResponse;
  }

  public InternationalUsageResponse getManipulatedInternationalUsageResponse(String apiResponseBody) throws IOException {
    InternationalUsageResponse internationalUsageResponse = objectMapper.readValue(apiResponseBody, InternationalUsageResponse.class);
    internationalUsageResponse.setReferenceId(RandomStringUtils.random(8, true, true));
    return internationalUsageResponse;
  }

  public ConvertEMIResponse getManipulatedConvertEMIResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, ConvertEMIResponse.class);
  }

  public ConvertEMIConfirmResponse getManipulatedConvertEMIConfirmResponse(String apiResponseBody) throws IOException {
    ConvertEMIConfirmResponse convertEMIConfirmResponse = objectMapper.readValue(apiResponseBody, ConvertEMIConfirmResponse.class);
    convertEMIConfirmResponse.setReferenceId(RandomStringUtils.random(8, true, true));
    return convertEMIConfirmResponse;
  }

  public CardPaymentResponse getManipulatedCardPaymentResponse(String apiResponseBody) throws IOException {
    CardPaymentResponse cardPaymentResponse = objectMapper.readValue(apiResponseBody, CardPaymentResponse.class);
    cardPaymentResponse.setTxnReferenceId(RandomStringUtils.random(8, true, true));
    return cardPaymentResponse;
  }
}
