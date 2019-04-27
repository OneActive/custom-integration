package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.mapper.AccountsResponseMapper;
import com.activeai.integration.banking.model.AccountBalanceResponse;
import com.activeai.integration.banking.model.AccountDetailResponse;
import com.activeai.integration.banking.model.AccountTransactionsResponse;
import com.activeai.integration.banking.model.AccountsResponse;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.FetchAPIUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * This Class methods helps to get account related information using http
 * you can extend this and override methods to handle for other's like soap
 */
@Component("accountsService")
public class AccountsService {

  @Autowired private AccountsResponseMapper accountsResponseMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private FetchAPIUrl fetchAPIUrl;

  /**
   * Fetches list of Accounts
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<AccountsResponse> getAccountsResponseEntity(String customerId) {
    AccountsResponse accountsResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.get(fetchAPIUrl.getAPIUrl(PropertyConstants.ACCOUNTS_API_URL, customerId)).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Account Response Body Before Transformation :" + response.getBody());
        String accountsResponseString = accountsResponseMapper.getManipulatedAccountsResponse(response.getBody());
        ApplicationLogger.logInfo("Account Response Body After Transformation :" + response.getBody());
        accountsResponse = objectMapper.readValue(accountsResponseString, AccountsResponse.class);
      }
      return new ResponseEntity<>(accountsResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(accountsResponse, HttpStatus.EXPECTATION_FAILED);
  }

  /**
   * Fetches Account Details for selected Account
   *
   * @param customerId,accountId
   * @return ResponseEntity of type AccountDetailResponse
   */
  public ResponseEntity<AccountDetailResponse> getAccountDetailsResponseEntity(String customerId, String accountId) {
    AccountDetailResponse accountsResponse = null;
    try {
      HttpResponse<String> response = Unirest.get(fetchAPIUrl.getAPIUrl(PropertyConstants.ACCOUNT_DETAILS_API, customerId, accountId))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Account Details Response Body Before Transformation :" + response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Account Details Response Body After Transformation :" + response.getBody());
        accountsResponse = objectMapper.readValue(accountDetailResponseString, AccountDetailResponse.class);
      }
      return new ResponseEntity<>(accountsResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(accountsResponse, HttpStatus.EXPECTATION_FAILED);
  }

  /**
   * Fetches Account Balance for selected Account
   * You should map balance inside AccountSelected object along with accountId which is required
   * @param customerId,accountId
   * @return ResponseEntity of type AccountBalanceResponse
   */
  public ResponseEntity<AccountBalanceResponse> getAccountBalanceResponseEntity(String customerId, String accountId) {
    AccountBalanceResponse accountBalanceResponse = null;
    try {
      HttpResponse<String> response = Unirest.get(fetchAPIUrl.getAPIUrl(PropertyConstants.ACCOUNT_BALANCE_API, customerId, accountId))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Account Balance Response Body Before Transformation :" + response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountsBalanceResponse(response.getBody());
        ApplicationLogger.logInfo("Account Balance Response Body After Transformation :" + response.getBody());
        accountBalanceResponse = objectMapper.readValue(accountDetailResponseString, AccountBalanceResponse.class);
      }
      return new ResponseEntity<>(accountBalanceResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(accountBalanceResponse, HttpStatus.EXPECTATION_FAILED);
  }

  /**
   * Fetches Account Transactions for selected Account
   *
   * @param customerId,accountId
   * @return ResponseEntity of type AccountTransactionsResponse
   */
  public ResponseEntity<AccountTransactionsResponse> getAccountTransactionsResponseEntity(String customerId, String accountId) {
    AccountTransactionsResponse accountTransactionsResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.get(fetchAPIUrl.getAPIUrl(PropertyConstants.ACCOUNT_TRANSACTIONS_HISTORY_API, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Account Transactions Response Body Before Transformation :" + response.getBody());
        String accountTransactionsResponseString = accountsResponseMapper.getManipulatedAccountTransactionsResponse(response.getBody());
        ApplicationLogger.logInfo("Account Transactions Response Body After Transformation :" + response.getBody());
        accountTransactionsResponse = objectMapper.readValue(accountTransactionsResponseString, AccountTransactionsResponse.class);
      }
      return new ResponseEntity<>(accountTransactionsResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(accountTransactionsResponse, HttpStatus.EXPECTATION_FAILED);
  }

}