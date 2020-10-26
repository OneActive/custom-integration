package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.AccountBalanceResponse;
import com.activeai.integration.banking.domain.response.DepositAccountBalanceResponse;
import com.activeai.integration.banking.domain.response.LoanAccountBalanceResponse;
import com.activeai.integration.banking.mapper.response.AccountsResponseMapper;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.PropertyUtil;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;

@Service("accountBalanceService") public class AccountBalanceService {

  @Autowired private AccountsResponseMapper accountsResponseMapper;
  @Autowired private PropertyUtil propertyUtil;
  private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

  /**
   * Fetches Casa Account Balance for selected Casa Account
   * You should map balance inside CasaAccountSelected object along with accountId which is required
   *
   * @param customerId,accountId
   * @return ResponseEntity of type CasaAccountBalanceResponse
   */
  public ResponseEntity<AccountBalanceResponse> getCasaAccountBalanceResponseEntity(String customerId, String accountId, String accessToken) {
    AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CASA_ACCOUNT_BALANCE_API_END_POINT, customerId, accountId)).header("cache-control", "no-cache").asString();
      ApplicationLogger
          .logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Casa Account Balance Response Body Before Transformation :" + apiResponse.getBody());
        accountBalanceResponse = accountsResponseMapper.getManipulatedCasaAccountsBalanceResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Casa Account Balance Response Body After Transformation :" + accountBalanceResponse);
      }
      return ResponseEntity.ok(accountBalanceResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    accountBalanceResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(accountBalanceResponse);
  }

  /**
   * Fetches Deposit Account Balance for selected Account
   * You should map balance inside AccountSelected object along with accountId which is required
   *
   * @param customerId,accountId
   * @return ResponseEntity of type DepositAccountBalanceResponse
   */
  public ResponseEntity<DepositAccountBalanceResponse> getDepositAccountBalanceResponseEntity(String customerId, String accountId, String accessToken) {
    DepositAccountBalanceResponse response = new DepositAccountBalanceResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.DEPOSIT_ACCOUNT_BALANCE_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger
          .logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Deposit Account Balance Response Body Before Transformation :" + apiResponse.getBody());
        response = accountsResponseMapper.getManipulatedDepositAccountsBalanceResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Deposit Account Balance Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Loan Account Balance for selected Account
   * You should map balance inside AccountSelected object along with accountId which is required
   *
   * @param customerId,accountId
   * @return ResponseEntity of type LoanAccountBalanceResponse
   */
  public ResponseEntity<LoanAccountBalanceResponse> getLoanAccountBalanceResponseEntity(String customerId, String accountId, String accessToken) {
    LoanAccountBalanceResponse response = new LoanAccountBalanceResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.LOAN_ACCOUNT_BALANCE_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger
          .logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Loan Account Balance Response Body Before Transformation :" + apiResponse.getBody());
        response = accountsResponseMapper.getManipulatedLoanAccountsBalanceResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Loan Account Balance Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }
}
