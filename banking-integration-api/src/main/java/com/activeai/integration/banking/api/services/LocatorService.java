package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.response.AtmLocatorResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.request.AtmLocatorRequest;
import com.activeai.integration.banking.domain.response.AtmLocatorResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service("locatorService")
public class LocatorService {

  private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";
  @Autowired 
  AtmLocatorResponseMapper atmLocatorResponseMapper;
  @Autowired
  private PropertyUtil propertyUtil;

  public ResponseEntity<AtmLocatorResponse> getNearestAtmsAddressResponseEntity(AtmLocatorRequest atmLocatorRequest) {
    AtmLocatorResponse response = new AtmLocatorResponse();
    try {

      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrlForAtmLocator(com.activeai.integration.banking.api.constants.PropertyConstants.ATM_LOCATOR_ADDRESS_API_END_POINT,
              atmLocatorRequest)).header("cache-control", "no-cache").asString();

      ApplicationLogger
          .logInfo("Atm Locator Response status: " + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
              this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Atm Locator Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        AtmLocatorResponse atmLocatorResponse = BankingIntegrationUtil.readValue(apiResponse.getBody(), AtmLocatorResponse.class);
        response = atmLocatorResponseMapper.getManipulatedAtmsAddressResponse(atmLocatorResponse, atmLocatorRequest);
        ApplicationLogger.logInfo("Atm Locator Response Body After Transformation :" + response, this.getClass());
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
    response.setResult(propertyUtil.frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  public ResponseEntity<AtmLocatorResponse> getNearestAtmsGeocodesResponseEntity(AtmLocatorRequest atmLocatorRequest) {
    AtmLocatorResponse response = new AtmLocatorResponse();
    try {

      HttpResponse<String> apiResponse =
          Unirest
              .post(propertyUtil.getAPIUrlForAtmLocator(PropertyConstants.ATM_LOCATOR_GEOCODES_API_END_POINT, atmLocatorRequest)).header("cache-control", "no-cache").asString();

      ApplicationLogger
          .logInfo("Atm Locator Response status: " + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
              this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Atm Locator Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = atmLocatorResponseMapper.getManipulatedAtmsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Atm Locator Response Body After Transformation :" + response, this.getClass());
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }  catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }
  }


