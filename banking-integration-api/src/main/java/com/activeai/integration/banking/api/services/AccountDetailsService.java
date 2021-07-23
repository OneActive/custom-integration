package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.APIConstants;
import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.response.AccountsResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.response.AccountDetailResponse;
import com.activeai.integration.banking.domain.response.DepositAccountDetailResponse;
import com.activeai.integration.banking.domain.response.LoanAccountDetailResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service("accountDetailsService")
public class AccountDetailsService {

  @Autowired private AccountsResponseMapper accountsResponseMapper;
  @Autowired private PropertyUtil propertyUtil;
  private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

  /**
   * Fetches CASA Account Details for selected Account
   *
   * @param customerId,accountId
   * @param accessToken          for future enhancement
   * @return ResponseEntity of type AccountDetailResponse
   */
  public ResponseEntity<AccountDetailResponse> getCasaAccountDetailsResponseEntity(String customerId, String accountId,
      String accessToken) {
    AccountDetailResponse response = new AccountDetailResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.CASA_ACCOUNT_DETAILS_API_END_POINT, customerId,
              accountId)).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Casa Account Details Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = accountsResponseMapper.getManipulatedCasaAccountDetailsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Casa Account Details Response Body After Transformation :" + response, this.getClass());
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
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Deposit Account Details for selected Account
   *
   * @param customerId
   * @param accountId
   * @param accessToken for future enhancement
   * @return ResponseEntity of type DepositAccountDetailResponse
   */
  public ResponseEntity<DepositAccountDetailResponse> getDepositAccountDetailsResponseEntity(String customerId, String accountId,
      String accessToken) {
    DepositAccountDetailResponse response = new DepositAccountDetailResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.DEPOSIT_ACCOUNT_DETAILS_API_END_POINT, customerId,
              accountId)).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Deposit Account Details Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = accountsResponseMapper.getManipulatedDepositAccountDetailsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Deposit Account Details Response Body After Transformation :" + response, this.getClass());
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
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Loan Account Details for selected Account
   *
   * @param customerId
   * @param accountId
   * @param accessToken for future enhancement
   * @return ResponseEntity of type LoanAccountDetailResponse
   */
  public ResponseEntity<LoanAccountDetailResponse> getLoanAccountDetailsResponseEntity(String customerId, String accountId,
      String accessToken) {
    LoanAccountDetailResponse response = new LoanAccountDetailResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.LOAN_ACCOUNT_DETAILS_API_END_POINT, customerId, accountId))
              .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Loan Account Details Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = accountsResponseMapper.getManipulatedLoanAccountDetailsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Loan Account Details Response Body After Transformation :" + response, this.getClass());
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
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }
}
