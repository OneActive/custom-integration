package com.activeai.integration.banking.mapper.response;

public class DebitCardResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the DebitCardLimitResponse
   *
   * @param apiResponseBody
   * @return String of DebitCardLimitResponse
   */
  public String getManipulatedDebitCardLimitResponse(String apiResponseBody) {
    return apiResponseBody;
  }

  /**
   * In this method you can change the obtained string accordingly to the DebitCardLimitConfirmResponse
   *
   * @param apiResponseBody
   * @return String of DebitCardLimitConfirmResponse
   */
  public String getManipulatedDebitCardLimitConfirmResponse(String apiResponseBody) {
    return apiResponseBody;
  }
}
