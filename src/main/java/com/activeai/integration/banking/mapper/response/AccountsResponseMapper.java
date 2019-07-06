package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("accountsResponseMapper")
public class AccountsResponseMapper {

  @Autowired private ObjectMapper objectMapper;

  /**
   * In this method you can change the obtained string accordingly to the AccountsResponse
   * @param apiResponseBody
   * @return AccountsResponse
   */
  public AccountsResponse getManipulatedCasaAccountsResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, AccountsResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the DepositAccountsResponse
   * @param apiResponseBody
   * @return DepositAccountsResponse
   */
  public DepositAccountsResponse getManipulatedDepositAccountsResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, DepositAccountsResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the LoanAccountsResponse
   * @param apiResponseBody
   * @return LoanAccountsResponse
   */
  public LoanAccountsResponse getManipulatedLoanAccountsResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, LoanAccountsResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountDetailsResponse
   * @param apiResponseBody
   * @return AccountDetailsResponse
   */
  public AccountDetailResponse getManipulatedCasaAccountDetailsResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, AccountDetailResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the DepositAccountDetailResponse
   * @param apiResponseBody
   * @return DepositAccountDetailResponse
   */
  public DepositAccountDetailResponse getManipulatedDepositAccountDetailsResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, DepositAccountDetailResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountDetailsResponse
   * @param apiResponseBody
   * @return LoanAccountDetailResponse
   */
  public LoanAccountDetailResponse getManipulatedLoanAccountDetailsResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, LoanAccountDetailResponse.class);
  }


  /**
   * In this method you can change the obtained string accordingly to the AccountsBalanceResponse
   * @param apiResponseBody
   * @return  AccountsBalanceResponse
   */
  public AccountBalanceResponse getManipulatedCasaAccountsBalanceResponse(String apiResponseBody) throws IOException{
      return objectMapper.readValue(apiResponseBody, AccountBalanceResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the DepositAccountBalanceResponse
   * @param apiResponseBody
   * @return  DepositAccountBalanceResponse
   */
  public DepositAccountBalanceResponse getManipulatedDepositAccountsBalanceResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, DepositAccountBalanceResponse.class);
  }

  /**
   * In this method you can change the obtained string accordingly to the LoanAccountBalanceResponse
   * @param apiResponseBody
   * @return  LoanAccountBalanceResponse
   */
  public LoanAccountBalanceResponse getManipulatedLoanAccountsBalanceResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, LoanAccountBalanceResponse.class);
  }
  /**
   * In this method you can change the obtained string accordingly to the AccountTransactionsResponse
   * @param apiResponseBody
   * @return AccountTransactionsResponse
   */
  public AccountTransactionsResponse getManipulatedAccountTransactionsResponse(String apiResponseBody) throws IOException{
    return objectMapper.readValue(apiResponseBody, AccountTransactionsResponse.class);
  }
}
