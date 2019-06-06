package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.DepositServiceRequest;;
import com.activeai.integration.banking.domain.response.DepositServiceResponse;
import com.activeai.integration.banking.mapper.response.DepositPlanResponseMapper;
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

@Service("depositPlanService")
public class DepositService {

  @Autowired private DepositPlanResponseMapper depositPlanResponseMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private PropertyUtil propertyUtil;

  public ResponseEntity<DepositServiceResponse> getDepositPlansResponseEntity(DepositServiceRequest depositServiceRequest) {
    DepositServiceResponse depositServiceResponse = new DepositServiceResponse();
    try {

      HttpResponse<String> response =
          Unirest
              .post(propertyUtil.getAPIUrl(PropertyConstants.DEPOSIT_PLAN_API_END_POINT, depositServiceRequest.getCustomerId(),depositServiceRequest.getCreditableAccounts().getAccountId())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("Deposit Plan API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo(" Deposit Plan Response Body Before Transformation :" + response.getBody());
        String depositPlanResponseString = depositPlanResponseMapper.getManipulatedDepositPlanResponse(response.getBody());
        ApplicationLogger.logInfo("Deposit Plan Response Body After Transformation :" + response.getBody());
        depositServiceResponse = objectMapper.readValue(depositPlanResponseString, DepositServiceResponse.class);
      }
      return ResponseEntity.ok(depositServiceResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    depositServiceResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(depositServiceResponse);
  }

  public ResponseEntity<DepositServiceResponse> getDepositPlanNomineesResponseEntity(DepositServiceRequest depositServiceRequest) {
    DepositServiceResponse depositServiceResponse = new DepositServiceResponse();
    try {
      HttpResponse<String> response =
          Unirest
              .post(propertyUtil.getAPIUrl(PropertyConstants.NOMINEE_API_END_POINT, depositServiceRequest.getCustomerId(),
                  depositServiceRequest.getCreditableAccounts().getAccountId())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("Nominee API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo(" Nominee Response Body Before Transformation :" + response.getBody());
        String nomineesResponseString = depositPlanResponseMapper.getManipulatedNomineeResponse(response.getBody());
        ApplicationLogger.logInfo("Nominee Response Body After Transformation :" + response.getBody());
        depositServiceResponse = objectMapper.readValue(nomineesResponseString, DepositServiceResponse.class);
      }
      return ResponseEntity.ok(depositServiceResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    depositServiceResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(depositServiceResponse);
  }

  public ResponseEntity<DepositServiceResponse> getDepositPlanFinalResponseResponseEntity(DepositServiceRequest depositServiceRequest) {
    DepositServiceResponse depositServiceResponse = new DepositServiceResponse();
    try {
      HttpResponse<String> response =
          Unirest
              .post(propertyUtil.getAPIUrl(PropertyConstants.OPEN_FD_API_END_POINT, depositServiceRequest.getCustomerId(),
                  depositServiceRequest.getCreditableAccounts().getAccountId())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("Deposit Plan Final API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo(" Deposit Plan Final API Response Body Before Transformation :" + response.getBody());
        String depositPlanFinalAPiCallResponseString = depositPlanResponseMapper.getManipulatedDepositPlanFinalApiCallResponse(response.getBody());
        ApplicationLogger.logInfo("Deposit Plan Final API Response Body After Transformation :" + response.getBody());
        depositServiceResponse = objectMapper.readValue(depositPlanFinalAPiCallResponseString, DepositServiceResponse.class);
      }
      return ResponseEntity.ok(depositServiceResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    depositServiceResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(depositServiceResponse);
  }
}
