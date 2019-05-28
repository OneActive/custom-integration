package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;

import com.activeai.integration.banking.domain.response.AccountBalanceResponse;
import com.activeai.integration.banking.domain.response.AccountDetailResponse;
import com.activeai.integration.banking.domain.response.AccountsResponse;
import com.activeai.integration.banking.domain.response.DepositAccountsResponse;
import com.activeai.integration.banking.domain.response.AccountTransactionsResponse;
import com.activeai.integration.banking.mapper.response.AccountsResponseMapper;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.PropertyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

/**
 * This Class methods helps to get account related information using http
 * you can extend this and override methods to handle for other's like soap
 */
@Service("accountsService")
public class AccountsService {

  @Autowired private AccountsResponseMapper accountsResponseMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private PropertyUtil propertyUtil;

  /**
   * Fetches list of Accounts
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<AccountsResponse> getCasaAccountsResponseEntity(String customerId) {
    AccountsResponse accountsResponse = new AccountsResponse();
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CASA_ACCOUNT_API_END_POINT, customerId,null)).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("Casa API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo(" Casa Response Body Before Transformation :" + response.getBody());
        String accountsResponseString = accountsResponseMapper.getManipulatedAccountsResponse(response.getBody());
        ApplicationLogger.logInfo("Casa Response Body After Transformation :" + response.getBody());
        accountsResponse = objectMapper.readValue(accountsResponseString, AccountsResponse.class);
      }
      return ResponseEntity.ok(accountsResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    accountsResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(accountsResponse);
  }
  public ResponseEntity<DepositAccountsResponse> getDepositAccountsResponseEntity(String customerId) {
    DepositAccountsResponse depositAccountsResponse = new DepositAccountsResponse();
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.DEPOSIT_ACCOUNT_API_END_POINT, customerId, null)).header("cache-control", "no-cache")
              .asString();
      ApplicationLogger.logInfo("Deposit API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo(" Deposit Response Body Before Transformation :" + response.getBody());
        String accountsResponseString = accountsResponseMapper.getManipulatedAccountsResponse(response.getBody());
        ApplicationLogger.logInfo(" Deposit Response Body After Transformation :" + response.getBody());
        depositAccountsResponse = objectMapper.readValue(accountsResponseString, DepositAccountsResponse.class);
      }
      return ResponseEntity.ok(depositAccountsResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    depositAccountsResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(depositAccountsResponse);
  }
  /**
   * Fetches Account Details for selected Account
   *
   * @param customerId,accountId
   * @return ResponseEntity of type AccountDetailResponse
   */
  public ResponseEntity<AccountDetailResponse> getAccountDetailsResponseEntity(String customerId, String accountId) {
    AccountDetailResponse accountDetailsResponse = new AccountDetailResponse();
    try {
      HttpResponse<String> response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CASA_ACCOUNT_DETAILS_API_END_POINT, customerId, accountId))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Account Details Response Body Before Transformation :" + response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Account Details Response Body After Transformation :" + response.getBody());
        accountDetailsResponse = objectMapper.readValue(accountDetailResponseString, AccountDetailResponse.class);
      }
      return ResponseEntity.ok(accountDetailsResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    accountDetailsResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(accountDetailsResponse);
  }

  /**
   * Fetches Account Balance for selected Account
   * You should map balance inside AccountSelected object along with accountId which is required
   * @param customerId,accountId
   * @return ResponseEntity of type AccountBalanceResponse
   */
  public ResponseEntity<AccountBalanceResponse> getAccountBalanceResponseEntity(String customerId, String accountId) {
    AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();
    try {
      HttpResponse<String> response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CASA_ACCOUNT_BALANCE_API_END_POINT, customerId, accountId))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Account Balance Response Body Before Transformation :" + response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountsBalanceResponse(response.getBody());
        ApplicationLogger.logInfo("Account Balance Response Body After Transformation :" + response.getBody());
        accountBalanceResponse = objectMapper.readValue(accountDetailResponseString, AccountBalanceResponse.class);
      }
      return ResponseEntity.ok(accountBalanceResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    accountBalanceResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(accountBalanceResponse);
  }

  /**
   * Fetches Account Transactions for selected Account
   *
   * @param customerId,accountId
   * @return ResponseEntity of type AccountTransactionsResponse
   */
  public ResponseEntity<AccountTransactionsResponse> getAccountTransactionsResponseEntity(String customerId, String accountId) {
    AccountTransactionsResponse accountTransactionsResponse = new AccountTransactionsResponse();
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CASA_ACCOUNT_TRANSACTIONS_HISTORY_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Account Transactions Response Body Before Transformation :" + response.getBody());
        String accountTransactionsResponseString = accountsResponseMapper.getManipulatedAccountTransactionsResponse(response.getBody());
        ApplicationLogger.logInfo("Account Transactions Response Body After Transformation :" + response.getBody());
        accountTransactionsResponse = objectMapper.readValue(accountTransactionsResponseString, AccountTransactionsResponse.class);
      }
      return ResponseEntity.ok(accountTransactionsResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    accountTransactionsResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(accountTransactionsResponse);
  }

}