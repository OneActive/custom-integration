package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.AccountBalanceResponse;
import com.activeai.integration.banking.domain.response.DepositAccountBalanceResponse;
import com.activeai.integration.banking.domain.response.LoanAccountBalanceResponse;
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

@Service("accountBalanceService")
public class AccountBalanceService {

  @Autowired private AccountsResponseMapper accountsResponseMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private PropertyUtil propertyUtil;

  /**
   * Fetches Casa Account Balance for selected Casa Account
   * You should map balance inside CasaAccountSelected object along with accountId which is required
   * @param customerId,accountId
   * @return ResponseEntity of type CasaAccountBalanceResponse
   */
  public ResponseEntity<AccountBalanceResponse> getCasaAccountBalanceResponseEntity(String customerId, String accountId) {
    AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();
    try {
      HttpResponse<String>
          response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CASA_ACCOUNT_BALANCE_API_END_POINT, customerId, accountId))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Casa Account Balance Response Body Before Transformation :" + response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountsBalanceResponse(response.getBody());
        ApplicationLogger.logInfo("Casa Account Balance Response Body After Transformation :" + response.getBody());
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
    accountBalanceResponse
        .setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(accountBalanceResponse);
  }

  /**
   * Fetches Deposit Account Balance for selected Account
   * You should map balance inside AccountSelected object along with accountId which is required
   * @param customerId,accountId
   * @return ResponseEntity of type DepositAccountBalanceResponse
   */
  public ResponseEntity<DepositAccountBalanceResponse> getDepositAccountBalanceResponseEntity(String customerId, String accountId) {
    DepositAccountBalanceResponse depositAccountBalanceResponse = new DepositAccountBalanceResponse();
    try {
      HttpResponse<String>
          response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.DEPOSIT_ACCOUNT_BALANCE_API_END_POINT, customerId, accountId))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Deposit Account Balance Response Body Before Transformation :" + response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountsBalanceResponse(response.getBody());
        ApplicationLogger.logInfo("Deposit Account Balance Response Body After Transformation :" + response.getBody());
        depositAccountBalanceResponse = objectMapper.readValue(accountDetailResponseString, DepositAccountBalanceResponse.class);
      }
      return ResponseEntity.ok(depositAccountBalanceResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    depositAccountBalanceResponse
        .setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(depositAccountBalanceResponse);
  }

  /**
   * Fetches Loan Account Balance for selected Account
   * You should map balance inside AccountSelected object along with accountId which is required
   * @param customerId,accountId
   * @return ResponseEntity of type LoanAccountBalanceResponse
   */
  public ResponseEntity<LoanAccountBalanceResponse> getLoanAccountBalanceResponseEntity(String customerId, String accountId) {
    LoanAccountBalanceResponse loanAccountBalanceResponse = new LoanAccountBalanceResponse();
    try {
      HttpResponse<String>
          response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.LOAN_ACCOUNT_BALANCE_API_END_POINT, customerId, accountId))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Loan Account Balance Response Body Before Transformation :" + response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountsBalanceResponse(response.getBody());
        ApplicationLogger.logInfo("Loan Account Balance Response Body After Transformation :" + response.getBody());
        loanAccountBalanceResponse = objectMapper.readValue(accountDetailResponseString, LoanAccountBalanceResponse.class);
      }
      return ResponseEntity.ok(loanAccountBalanceResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    loanAccountBalanceResponse
        .setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(loanAccountBalanceResponse);
  }
}
