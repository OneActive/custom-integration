package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.*;
import org.springframework.stereotype.Component;

@Component("accountsResponseMapper")
public class AccountsResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the AccountsResponse
   *
   * @param apiResponseBody
   * @return AccountsResponse
   */
  public AccountsResponse getManipulatedCasaAccountsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, AccountsResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the DepositAccountsResponse
   *
   * @param apiResponseBody
   * @return DepositAccountsResponse
   */
  public DepositAccountsResponse getManipulatedDepositAccountsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, DepositAccountsResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the LoanAccountsResponse
   *
   * @param apiResponseBody
   * @return LoanAccountsResponse
   */
  public LoanAccountsResponse getManipulatedLoanAccountsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, LoanAccountsResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountDetailsResponse
   *
   * @param apiResponseBody
   * @return AccountDetailsResponse
   */
  public AccountDetailResponse getManipulatedCasaAccountDetailsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, AccountDetailResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the DepositAccountDetailResponse
   *
   * @param apiResponseBody
   * @return DepositAccountDetailResponse
   */
  public DepositAccountDetailResponse getManipulatedDepositAccountDetailsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, DepositAccountDetailResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountDetailsResponse
   *
   * @param apiResponseBody
   * @return LoanAccountDetailResponse
   */
  public LoanAccountDetailResponse getManipulatedLoanAccountDetailsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, LoanAccountDetailResponse.class);
  }


  /**
   * In this method you can change the obtained string accordingly to the AccountsBalanceResponse
   *
   * @param apiResponseBody
   * @return AccountsBalanceResponse
   */
  public AccountBalanceResponse getManipulatedCasaAccountsBalanceResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, AccountBalanceResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the DepositAccountBalanceResponse
   *
   * @param apiResponseBody
   * @return DepositAccountBalanceResponse
   */
  public DepositAccountBalanceResponse getManipulatedDepositAccountsBalanceResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, DepositAccountBalanceResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the LoanAccountBalanceResponse
   *
   * @param apiResponseBody
   * @return LoanAccountBalanceResponse
   */
  public LoanAccountBalanceResponse getManipulatedLoanAccountsBalanceResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, LoanAccountBalanceResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountTransactionsResponse
   *
   * @param apiResponseBody
   * @return AccountTransactionsResponse
   */
  public AccountTransactionsResponse getManipulatedAccountTransactionsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, AccountTransactionsResponse.class);
  }
}
