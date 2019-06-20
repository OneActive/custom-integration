package com.activeai.integration.banking.mapper.response;

import org.springframework.stereotype.Component;

@Component("accountsResponseMapper")
public class AccountsResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the AccountsResponse
   * @param apiResponseBody
   * @return String of AccountsResponse
   */
  public String getManipulatedAccountsResponse(String apiResponseBody) {
    return apiResponseBody;
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountDetailsResponse
   * @param apiResponseBody
   * @return String of AccountDetailsResponse
   */
  public String getManipulatedAccountDetailsResponse(String apiResponseBody) {
    return apiResponseBody;
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountsBalanceResponse
   * @param apiResponseBody
   * @return String of AccountsBalanceResponse
   */
  public String getManipulatedAccountsBalanceResponse(String apiResponseBody) {
    return apiResponseBody;
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountTransactionsResponse
   * @param apiResponseBody
   * @return String of AccountTransactionsResponse
   */
  public String getManipulatedAccountTransactionsResponse(String apiResponseBody) {
    return apiResponseBody;
  }
}
