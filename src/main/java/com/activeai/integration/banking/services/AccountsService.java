package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.AccountTransactionsResponse;
import com.activeai.integration.banking.domain.response.AccountsResponse;
import com.activeai.integration.banking.domain.response.DepositAccountsResponse;
import com.activeai.integration.banking.domain.response.LoanAccountsResponse;
import com.activeai.integration.banking.mapper.response.AccountsResponseMapper;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.PropertyUtil;
import com.activeai.integration.data.service.AccountServiceData;
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
import java.util.Objects;

/**
 * This Class methods helps to get account related information using http
 * you can extend this and override methods to handle for other's like soap
 */
@Service("accountsService")
public class AccountsService {

  @Autowired private AccountsResponseMapper accountsResponseMapper;
  @Autowired private PropertyUtil propertyUtil;
  @Autowired private AccountServiceData accountServiceData;
  private static final String error_message_format = "{0} : {1} : {2}";

  /**
   * Fetches list of Accounts
   * @param accessToken
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<AccountsResponse> getCasaAccountsResponseEntity(String customerId, String accessToken) {
    // Fetch accounts from cache you can remove this on real integration
    AccountsResponse response = accountServiceData.getAccountsResponse(customerId);
    if(Objects.nonNull(response)){
      return ResponseEntity.ok(response);
    }
    // Till this
    try {
      HttpResponse<String> apiResponse =
          Unirest.post(propertyUtil.getAccountAPIUrl(PropertyConstants.CASA_ACCOUNT_API_END_POINT, customerId,null)).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("Casa API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Casa Response Body Before Transformation :" + apiResponse.getBody());
        response = accountsResponseMapper.getManipulatedCasaAccountsResponse(apiResponse.getBody());
        // Caching Account Response, Remove this later
        accountServiceData.cacheAccountResponse(customerId, response);
        // Till this
        ApplicationLogger.logInfo("Casa Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }
  public ResponseEntity<LoanAccountsResponse> getLoanAccountsResponseEntity(String customerId, String accessToken) {
    LoanAccountsResponse response = new LoanAccountsResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAccountAPIUrl(PropertyConstants.LOAN_ACCOUNT_API_END_POINT, customerId, null)).header("cache-control", "no-cache")
              .asString();
      ApplicationLogger.logInfo("Deposit API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Deposit Response Body Before Transformation :" + apiResponse.getBody());
        response = accountsResponseMapper.getManipulatedLoanAccountsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo(" Deposit Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }
  public ResponseEntity<DepositAccountsResponse> getDepositAccountsResponseEntity(String customerId, String accessToken) {
    DepositAccountsResponse response = new DepositAccountsResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAccountAPIUrl(PropertyConstants.DEPOSIT_ACCOUNT_API_END_POINT, customerId, null)).header("cache-control", "no-cache")
              .asString();
      ApplicationLogger.logInfo("Deposit API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Deposit Response Body Before Transformation :" + apiResponse.getBody());
        response = accountsResponseMapper.getManipulatedDepositAccountsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo(" Deposit Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }


  /**
   * Fetches Account Transactions for selected Account
   *
   * @param customerId,accountId
   * @return ResponseEntity of type AccountTransactionsResponse
   */
  public ResponseEntity<AccountTransactionsResponse> getAccountTransactionsResponseEntity(String customerId, String accountId, String accessToken) {
    // Fetch accounts from cache you can remove this later
    AccountTransactionsResponse response = accountServiceData.getAccountTransactionsResponse(customerId, accountId);
    if (Objects.nonNull(response)) {
      return ResponseEntity.ok(response);
    }
    // Till this
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CASA_ACCOUNT_TRANSACTIONS_HISTORY_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Account Transactions Response Body Before Transformation :" + apiResponse.getBody());
        response = accountsResponseMapper.getManipulatedAccountTransactionsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Account Transactions Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

}
