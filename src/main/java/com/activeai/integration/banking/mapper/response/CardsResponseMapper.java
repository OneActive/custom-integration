package com.activeai.integration.banking.mapper.response;

import org.springframework.stereotype.Component;

@Component("cardsResponseMapper")
public class CardsResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the CardsResponse
   * @param apiResponseBody
   * @return String of CardsResponse
   */
  public String getManipulatedCardsResponse(String apiResponseBody) {
    return apiResponseBody;
  }

  /**
   * In this method you can change the obtained string accordingly to the CardTransactionsResponse
   * @param apiResponseBody
   * @return String of CardTransactionsResponse
   */
  public String getManipulatedCardTransactionsResponse(String apiResponseBody) {
    return apiResponseBody;
  }

  /**
   * In this method you can change the obtained string accordingly to the CardDetailsResponse
   * @param apiResponseBody
   * @return String of CardDetailsResponse
   */
  public String getManipulatedCardDetailsResponse(String apiResponseBody) {
    return apiResponseBody;
  }


  /**
   * In this method you can change the obtained string accordingly to the ResetPinConfirmResponse
   * @param apiResponseBody
   * @return String of ResetPinConfirmResponse
   */
  public String getManipulatedResetPinConfirmResponse(String apiResponseBody) {
    return apiResponseBody;
  }

  /**
   * In this method you can change the obtained string accordingly to the ReplaceCardConfirmResponse
   * @param apiResponseBody
   * @return String of ReplaceCardConfirmResponse
   */
  public String getManipulatedReplaceCardConfirmResponse(String apiResponseBody) {
    return apiResponseBody;
  }
}
