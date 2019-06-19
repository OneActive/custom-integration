package com.activeai.integration.banking.services;

import com.activeai.integration.banking.apiservice.domain.response.CitiAccountResponse;
import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;

import com.activeai.integration.banking.domain.request.ChequeBookOrderConfirmRequest;
import com.activeai.integration.banking.domain.response.*;
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
import org.springframework.http.HttpStatus;
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
   * @param accesstoken   CITI BANAMEX
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<AccountsResponse> getCasaAccountsResponseEntity(String accesstoken) {
    AccountsResponse accountsResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.get("https://sandbox.apihub.citi.com/gcb/api/v1/accounts").header("Authorization", "Bearer " + accesstoken)
              .header("Content-Type", "application/json").header("uuid", "12345")
              .header("client_id", "8929a349-d8b3-4584-8cea-95f56499adef").header("Accept", "application/json").asString();
      ApplicationLogger
          .logInfo("Casa API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo(" Casa Response Body Before Transformation :" + response.getBody());
        //to pojo
        CitiAccountResponse citiAccountResponse = objectMapper.readValue(response.getBody(), CitiAccountResponse.class);
        accountsResponse = accountsResponseMapper.getManipulatedCasaAccountsResponse(citiAccountResponse);
        ApplicationLogger.logInfo("Casa Response Body After Transformation :" + accountsResponse);
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

  public ResponseEntity<LoanAccountsResponse> getLoanAccountsResponseEntity(String customerId) {
    LoanAccountsResponse loanAccountsResponse = new LoanAccountsResponse();
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.LOAN_ACCOUNT_API_END_POINT, customerId, null)).header("cache-control", "no-cache")
              .asString();
      ApplicationLogger.logInfo("Deposit API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo(" Deposit Response Body Before Transformation :" + response.getBody());
        String accountsResponseString = accountsResponseMapper.getManipulatedAccountsResponse(response.getBody());
        ApplicationLogger.logInfo(" Deposit Response Body After Transformation :" + response.getBody());
        loanAccountsResponse = objectMapper.readValue(accountsResponseString, LoanAccountsResponse.class);
      }
      return ResponseEntity.ok(loanAccountsResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    loanAccountsResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(loanAccountsResponse);
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

  public ResponseEntity<ChequeBookOrderConfirmResponse> getChequeBookOrderConfirmResponseEntity(ChequeBookOrderConfirmRequest chequeBookOrderConfirmRequest) {
    ChequeBookOrderConfirmResponse chequeBookOrderConfirmResponse = null;
    try {
      HttpResponse<String> response = Unirest.get(propertyUtil
          .getAPIUrl(PropertyConstants.CHEQUE_BOOK_ORDER_CONFIRM_API_ENDPOINT, chequeBookOrderConfirmRequest.getCustomerId(),
              chequeBookOrderConfirmRequest.getAccount().getAccountNumber())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Credit Card Limit Confirm Response Body Before Transformation :" + response.getBody());
        String chequeBookOrderConfirmResponseString = accountsResponseMapper.getManipulatedAccountsResponse(response.getBody());
        ApplicationLogger.logInfo("Credit Card Limit Confirm Response Body After Transformation :" + response.getBody());
        chequeBookOrderConfirmResponse = objectMapper.readValue(chequeBookOrderConfirmResponseString, ChequeBookOrderConfirmResponse.class);
      }
      return new ResponseEntity<>(chequeBookOrderConfirmResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(chequeBookOrderConfirmResponse, HttpStatus.OK);
  }
}
