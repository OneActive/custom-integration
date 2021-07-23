package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.APIConstants;
import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.response.AccountsResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.response.AccountTransactionsResponse;
import com.activeai.integration.banking.domain.response.AccountsResponse;
import com.activeai.integration.banking.domain.response.DepositAccountsResponse;
import com.activeai.integration.banking.domain.response.LoanAccountsResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * This Class methods helps to get account related information using http
 * you can extend this and override methods to handle for other's like soap
 */
@Service("accountsService")
public class AccountsService {

  @Autowired private AccountsResponseMapper accountsResponseMapper;
  @Autowired private PropertyUtil propertyUtil;
  private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

  /**
   * Fetches list of Accounts
   * @param accessToken for future enhancement
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<AccountsResponse> getCasaAccountsResponseEntity(String customerId, String accessToken) {
    AccountsResponse response = new AccountsResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.post(propertyUtil.getAccountAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.CASA_ACCOUNT_API_END_POINT, customerId, null))
              .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger
          .logInfo("Casa API Response status: " + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
              this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Casa Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = accountsResponseMapper.getManipulatedCasaAccountsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Casa Response Body After Transformation :" + response, this.getClass());
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    if (response!=null) {
      response.setResult(propertyUtil
          .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
              APIConstants.INTERNAL_SERVER_ERROR, 500));
    }
    return ResponseEntity.ok(response);
  }

  /**
   * mapping loan accounts response
   *
   * @param customerId
   * @param accessToken for future enhancement
   * @return
   */
  public ResponseEntity<LoanAccountsResponse> getLoanAccountsResponseEntity(String customerId, String accessToken) {
    LoanAccountsResponse response = new LoanAccountsResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAccountAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.LOAN_ACCOUNT_API_END_POINT, customerId, null))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger
          .logInfo("Loan API Response status: " + apiResponse.getStatus() +MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
              this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Loan Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = accountsResponseMapper.getManipulatedLoanAccountsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo(" Loan Response Body After Transformation :" + response, this.getClass());
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR,
            500));
    return ResponseEntity.ok(response);
  }


  /**
   * mapping deposit account respone
   *
   * @param customerId
   * @param accessToken for future enhancement
   * @return
   */
  public ResponseEntity<DepositAccountsResponse> getDepositAccountsResponseEntity(String customerId, String accessToken) {
    DepositAccountsResponse response = new DepositAccountsResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAccountAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.DEPOSIT_ACCOUNT_API_END_POINT, customerId,
              null)).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger
          .logInfo("Deposit API Response status: " + apiResponse.getStatus() +MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
              this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Deposit Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = accountsResponseMapper.getManipulatedDepositAccountsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo(" Deposit Response Body After Transformation :" + response, this.getClass());
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR,
            500));
    return ResponseEntity.ok(response);
  }


  /**
   * Fetches Account Transactions for selected Account
   *
   * @param customerId,accountId
   * @param accessToken for future enhancement
   * @return ResponseEntity of type AccountTransactionsResponse
   */
  public ResponseEntity<AccountTransactionsResponse> getAccountTransactionsResponseEntity(String customerId, String accountId, String accessToken) {
    AccountTransactionsResponse response = new AccountTransactionsResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CASA_ACCOUNT_TRANSACTIONS_HISTORY_API_END_POINT, customerId, accountId))
              .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger
          .logInfo("API Response status: " + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
              this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Account Transactions Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = accountsResponseMapper.getManipulatedAccountTransactionsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Account Transactions Response Body After Transformation :" + response, this.getClass());
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    if (response != null) {
      response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR, 500));
    }
    return ResponseEntity.ok(response);
  }

}
