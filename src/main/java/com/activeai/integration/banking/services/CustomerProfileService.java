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
import java.util.Objects;

@Service("customerProfileService")
public class CustomerProfileService {

  @Autowired private ObjectMapper objectMapper;
  @Autowired private PropertyUtil propertyUtil;
  @Autowired private CustomerProfileResponseMapper customerProfileResponseMapper;

  public ResponseEntity<CustomerProfileResponse> getCustomerProfileResponseEntity(String customerId) {
    CustomerProfileResponse customerProfileResponse = new CustomerProfileResponse();
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CUSTOMER_PROFILE_API_END_POINT, customerId, null))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Customer Profile Response Body Before Transformation :" + response.getBody());
        String customerProfileResponseString = customerProfileResponseMapper.getManipulatedCustomerProfileResponse(response.getBody());
        ApplicationLogger.logInfo("Customer Profile Response Body After Transformation :" + response.getBody());
        customerProfileResponse = objectMapper.readValue(customerProfileResponseString, CustomerProfileResponse.class);
      }
      return ResponseEntity.ok(customerProfileResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    customerProfileResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(customerProfileResponse);
  }

  public ResponseEntity<CustomerProfileResponse> getCustomerProfileEmailResponseEntity(String customerId, CustomerProfileRequest customerProfileRequest) {
    CustomerProfileResponse customerProfileResponse = new CustomerProfileResponse();
    try {
      HttpResponse<String> response =
          Unirest.put(propertyUtil.getAPIUrl(PropertyConstants.UPDATE_EMAIL_API_END_POINT, customerId, null))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Customer Profile Response Body Before Transformation :" + response.getBody());
        String customerProfileResponseString = customerProfileResponseMapper.getManipulatedCustomerProfileResponse(response.getBody());
        ApplicationLogger.logInfo("Customer Profile Response Body After Transformation :" + response.getBody());
        customerProfileResponse = objectMapper.readValue(customerProfileResponseString, CustomerProfileResponse.class);
      }
      return ResponseEntity.ok(customerProfileResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    customerProfileResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(customerProfileResponse);
  }
  public ResponseEntity<CustomerProfileResponse> getCustomerProfilePhoneResponseEntity(String customerId, CustomerProfileRequest customerProfileRequest) {
    CustomerProfileResponse customerProfileResponse = new CustomerProfileResponse();
    try {
      HttpResponse<String> response =
          Unirest.put(propertyUtil.getAPIUrl(PropertyConstants.UPDATE_PHONE_API_END_POINT, customerId, null))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Customer Profile Response Body Before Transformation :" + response.getBody());
        String customerProfileResponseString = customerProfileResponseMapper.getManipulatedCustomerProfileResponse(response.getBody());
        ApplicationLogger.logInfo("Customer Profile Response Body After Transformation :" + response.getBody());
        customerProfileResponse = objectMapper.readValue(customerProfileResponseString, CustomerProfileResponse.class);
      }
      return ResponseEntity.ok(customerProfileResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    customerProfileResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(customerProfileResponse);
  }
  public ResponseEntity<CustomerProfileResponse> getCustomerProfileAddressResponseEntity(String customerId, CustomerProfileRequest customerProfileRequest) {
    CustomerProfileResponse customerProfileResponse = new CustomerProfileResponse();

    try {
      HttpResponse<String> response =
          Unirest.put(propertyUtil.getAPIUrl(PropertyConstants.UPDATE_ADDRESS_API_END_POINT, customerId, null))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Customer Profile Response Body Before Transformation :" + response.getBody());
        String customerProfileResponseString = customerProfileResponseMapper.getManipulatedCustomerProfileResponse(response.getBody());
        ApplicationLogger.logInfo("Customer Profile Response Body After Transformation :" + response.getBody());
        customerProfileResponse = objectMapper.readValue(customerProfileResponseString, CustomerProfileResponse.class);
      }
      return ResponseEntity.ok(customerProfileResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    customerProfileResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(customerProfileResponse);
  }
}
