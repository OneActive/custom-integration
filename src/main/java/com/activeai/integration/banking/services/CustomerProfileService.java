package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.CustomerProfileRequest;
import com.activeai.integration.banking.domain.response.CustomerProfileResponse;
import com.activeai.integration.banking.mapper.response.CustomerProfileResponseMapper;
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
import java.text.MessageFormat;
import java.util.Objects;

@Service("customerProfileService")
public class CustomerProfileService {

  @Autowired private PropertyUtil propertyUtil;
  @Autowired private CustomerProfileResponseMapper customerProfileResponseMapper;
  private static final String error_message_format = "{0} : {1} : {2}";

  public ResponseEntity<CustomerProfileResponse> getCustomerProfileResponseEntity(String customerId) {
    CustomerProfileResponse response = new CustomerProfileResponse();
    try {
      HttpResponse<String> appiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CUSTOMER_PROFILE_API_END_POINT, customerId, null))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + appiResponse.getStatus() + " and response status text :" + appiResponse.getStatusText());
      if (StringUtils.isNotEmpty(appiResponse.getBody())) {
        ApplicationLogger.logInfo("Customer Profile Response Body Before Transformation :" + appiResponse.getBody());
        response = customerProfileResponseMapper.getManipulatedCustomerProfileResponse(appiResponse.getBody());
        ApplicationLogger.logInfo("Customer Profile Response Body After Transformation :" + response);
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

  public ResponseEntity<CustomerProfileResponse> getCustomerProfileEmailResponseEntity(String customerId, CustomerProfileRequest customerProfileRequest) {
    CustomerProfileResponse response = new CustomerProfileResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.put(propertyUtil.getAPIUrl(PropertyConstants.UPDATE_EMAIL_API_END_POINT, customerId, null))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Update Email Response Body Before Transformation :" + apiResponse.getBody());
        response = customerProfileResponseMapper.getManipulatedCustomerProfileResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Update Email  Response Body After Transformation :" + response);
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
  public ResponseEntity<CustomerProfileResponse> getCustomerProfilePhoneResponseEntity(String customerId, CustomerProfileRequest customerProfileRequest) {
    CustomerProfileResponse response = new CustomerProfileResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.put(propertyUtil.getAPIUrl(PropertyConstants.UPDATE_PHONE_API_END_POINT, customerId, null))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Update Phone Response Body Before Transformation :" + apiResponse.getBody());
        response = customerProfileResponseMapper.getManipulatedCustomerProfileResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Update Phone Response Body After Transformation :" + response);
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
  public ResponseEntity<CustomerProfileResponse> getCustomerProfileAddressResponseEntity(String customerId, CustomerProfileRequest customerProfileRequest) {
    CustomerProfileResponse response = new CustomerProfileResponse();

    try {
      HttpResponse<String> apiResponse =
          Unirest.put(propertyUtil.getAPIUrl(PropertyConstants.UPDATE_ADDRESS_API_END_POINT, customerId, null))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Update Address Response Body Before Transformation :" + apiResponse.getBody());
        response = customerProfileResponseMapper.getManipulatedCustomerProfileResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Update Address Response Body After Transformation :" + response);
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
