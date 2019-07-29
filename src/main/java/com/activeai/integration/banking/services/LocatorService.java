package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.AtmLocatorRequest;
import com.activeai.integration.banking.domain.response.AtmLocatorResponse;
import com.activeai.integration.banking.mapper.response.AtmLocatorResponseMapper;
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
import java.text.MessageFormat;
import java.util.List;

@Service("locatorService")
public class LocatorService {
  @Autowired AtmLocatorResponseMapper atmLocatorResponseMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired
  private PropertyUtil propertyUtil;
  private static final String error_message_format = "{0} : {1} : {2}";

  public ResponseEntity<AtmLocatorResponse> getNearestAtmsAddressResponseEntity(AtmLocatorRequest atmLocatorRequest) {
    AtmLocatorResponse response = new AtmLocatorResponse();
    try {

      HttpResponse<String> apiResponse =
          Unirest
              .post(propertyUtil.getAPIUrlForAtmLocator(PropertyConstants.ATM_LOCATOR_ADDRESS_API_END_POINT, atmLocatorRequest)).header("cache-control", "no-cache").asString();

      ApplicationLogger.logInfo("Atm Locator Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Atm Locator Response Body Before Transformation :" + apiResponse.getBody());
        AtmLocatorResponse atmLocatorResponse =objectMapper.readValue(apiResponse.getBody(), AtmLocatorResponse.class);
        response = atmLocatorResponseMapper.getManipulatedAtmsAddressResponse(atmLocatorResponse,atmLocatorRequest);
        ApplicationLogger.logInfo("Atm Locator Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  public ResponseEntity<AtmLocatorResponse> getNearestAtmsGeocodesResponseEntity(AtmLocatorRequest atmLocatorRequest) {
    AtmLocatorResponse response = new AtmLocatorResponse();
    try {

      HttpResponse<String> apiResponse =
          Unirest
              .post(propertyUtil.getAPIUrlForAtmLocator(PropertyConstants.ATM_LOCATOR_GEOCODES_API_END_POINT, atmLocatorRequest)).header("cache-control", "no-cache").asString();

      ApplicationLogger.logInfo("Atm Locator Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" Atm Locator Response Body Before Transformation :" + apiResponse.getBody());
        response = atmLocatorResponseMapper.getManipulatedAtmsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Atm Locator Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }
  }


