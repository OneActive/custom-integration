package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.DepositServiceRequest;;
import com.activeai.integration.banking.domain.response.DepositServiceResponse;
import com.activeai.integration.banking.mapper.response.DepositPlanResponseMapper;
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

@Service("depositPlanService")
public class DepositService {

  @Autowired private DepositPlanResponseMapper depositPlanResponseMapper;
  @Autowired private PropertyUtil propertyUtil;
  private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

  public ResponseEntity<DepositServiceResponse> getDepositPlansResponseEntity(DepositServiceRequest depositServiceRequest) {
    DepositServiceResponse response = new DepositServiceResponse();
    try {

      HttpResponse<String> apiResponse =
          Unirest
              .post(propertyUtil.getAPIUrl(PropertyConstants.DEPOSIT_PLAN_API_END_POINT, depositServiceRequest.getCustomerId(),depositServiceRequest.getCreditableAccounts().getAccountId())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("Deposit Plan API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Deposit Plan Response Body Before Transformation :" + apiResponse.getBody());
        response = depositPlanResponseMapper.getManipulatedDepositPlanResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Deposit Plan Response Body After Transformation :" + response);
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

  public ResponseEntity<DepositServiceResponse> getDepositPlanNomineesResponseEntity(DepositServiceRequest depositServiceRequest) {
    DepositServiceResponse response = new DepositServiceResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest
              .post(propertyUtil.getAPIUrl(PropertyConstants.NOMINEE_API_END_POINT, depositServiceRequest.getCustomerId(),
                  depositServiceRequest.getCreditableAccounts().getAccountId())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("Nominee API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Nominee Response Body Before Transformation :" + apiResponse.getBody());
        response = depositPlanResponseMapper.getManipulatedNomineeResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Nominee Response Body After Transformation :" + response);
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

  public ResponseEntity<DepositServiceResponse> getDepositPlanFinalResponseResponseEntity(DepositServiceRequest depositServiceRequest) {
    DepositServiceResponse response = new DepositServiceResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest
              .post(propertyUtil.getAPIUrl(PropertyConstants.OPEN_FD_API_END_POINT, depositServiceRequest.getCustomerId(),
                  depositServiceRequest.getCreditableAccounts().getAccountId())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("Deposit Plan Final API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Deposit Plan Final API Response Body Before Transformation :" + apiResponse.getBody());
        response = depositPlanResponseMapper.getManipulatedDepositPlanFinalApiCallResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Deposit Plan Final API Response Body After Transformation :" + response);
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
