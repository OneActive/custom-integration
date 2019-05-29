package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.AccountDetailResponse;
import com.activeai.integration.banking.domain.response.DepositAccountDetailResponse;
import com.activeai.integration.banking.domain.response.LoanAccountDetailResponse;
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

@Service("accountDetailsService")
public class AccountDetailsService {
  @Autowired private AccountsResponseMapper accountsResponseMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private PropertyUtil propertyUtil;

  /**
   * Fetches Account Details for selected Account
   *
   * @param customerId,accountId
   * @return ResponseEntity of type AccountDetailResponse
   */

  public ResponseEntity<AccountDetailResponse> getCasaAccountDetailsResponseEntity(String customerId, String accountId) {
    AccountDetailResponse accountDetailsResponse = new AccountDetailResponse();
    try {
      HttpResponse<String>
          response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CASA_ACCOUNT_DETAILS_API_END_POINT, customerId, accountId))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Casa Account Details Response Body Before Transformation :" + response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Casa Account Details Response Body After Transformation :" + response.getBody());
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

  public ResponseEntity<DepositAccountDetailResponse> getDepositAccountDetailsResponseEntity(String customerId, String accountId) {
    DepositAccountDetailResponse depositAccountDetailResponse = new DepositAccountDetailResponse();
    try {
      HttpResponse<String>
          response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.DEPOSIT_ACCOUNT_DETAILS_API_END_POINT, customerId, accountId))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Deposit Account Details Response Body Before Transformation :" + response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Deposit Account Details Response Body After Transformation :" + response.getBody());
        depositAccountDetailResponse = objectMapper.readValue(accountDetailResponseString, DepositAccountDetailResponse.class);
      }
      return ResponseEntity.ok(depositAccountDetailResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    depositAccountDetailResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(depositAccountDetailResponse);
  }

  public ResponseEntity<LoanAccountDetailResponse> getLoanAccountDetailsResponseEntity(String customerId, String accountId) {
    LoanAccountDetailResponse loanAccountDetailResponse = new LoanAccountDetailResponse();
    try {
      HttpResponse<String>
          response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.LOAN_ACCOUNT_DETAILS_API_END_POINT, customerId, accountId))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Deposit Account Details Response Body Before Transformation :" + response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Deposit Account Details Response Body After Transformation :" + response.getBody());
        loanAccountDetailResponse = objectMapper.readValue(accountDetailResponseString, LoanAccountDetailResponse.class);
      }
      return ResponseEntity.ok(loanAccountDetailResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    loanAccountDetailResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(loanAccountDetailResponse);
  }
}
