package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.APIConstants;
import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.response.DepositPlanResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.request.DepositServiceRequest;
import com.activeai.integration.banking.domain.response.DepositServiceResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service("depositPlanService")
public class DepositService {

  @Autowired private DepositPlanResponseMapper depositPlanResponseMapper;
  @Autowired private PropertyUtil propertyUtil;
  private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

  public ResponseEntity<DepositServiceResponse> getDepositPlansResponseEntity(DepositServiceRequest depositServiceRequest) {
    DepositServiceResponse response = new DepositServiceResponse();
    try {

      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.DEPOSIT_PLAN_API_END_POINT,
              depositServiceRequest.getCustomerId(), depositServiceRequest.getCreditableAccounts().getAccountId()))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          "Deposit Plan API Response status: " + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse
              .getStatusText(), this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Deposit Plan Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = depositPlanResponseMapper.getManipulatedDepositPlanResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Deposit Plan Response Body After Transformation :" + response, this.getClass());
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

  public ResponseEntity<DepositServiceResponse> getDepositPlanNomineesResponseEntity(DepositServiceRequest depositServiceRequest) {
    DepositServiceResponse response = new DepositServiceResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.NOMINEE_API_END_POINT,
              depositServiceRequest.getCustomerId(), depositServiceRequest.getCreditableAccounts().getAccountId()))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          "Nominee API Response status: " + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Nominee Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = depositPlanResponseMapper.getManipulatedNomineeResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Nominee Response Body After Transformation :" + response, this.getClass());
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

  public ResponseEntity<DepositServiceResponse> getDepositPlanFinalResponseResponseEntity(DepositServiceRequest depositServiceRequest) {
    DepositServiceResponse response = new DepositServiceResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrl(PropertyConstants.OPEN_FD_API_END_POINT, depositServiceRequest.getCustomerId(),
              depositServiceRequest.getCreditableAccounts().getAccountId())).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE)
          .asString();
      ApplicationLogger.logInfo(
          "Deposit Plan Final API Response status: " + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse
              .getStatusText(), this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Deposit Plan Final API Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = depositPlanResponseMapper.getManipulatedDepositPlanFinalApiCallResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Deposit Plan Final API Response Body After Transformation :" + response, this.getClass());
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
