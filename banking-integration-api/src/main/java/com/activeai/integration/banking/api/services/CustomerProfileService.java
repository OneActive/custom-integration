package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.APIConstants;
import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.response.CustomerProfileResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.request.CustomerProfileRequest;
import com.activeai.integration.banking.domain.response.CustomerProfileResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service("customerProfileService")
public class CustomerProfileService {

  @Autowired private PropertyUtil propertyUtil;
  @Autowired private CustomerProfileResponseMapper customerProfileResponseMapper;
  private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

  public ResponseEntity<CustomerProfileResponse> getCustomerProfileResponseEntity(String customerId) {
    CustomerProfileResponse response = new CustomerProfileResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CUSTOMER_PROFILE_API_END_POINT, customerId, null))
              .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      return processCustomerProfileResponse(apiResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }


  /**
   * mapping customer profile email response
   *
   * @param customerId
   * @param customerProfileRequest for future enhancement
   * @return
   */
  public ResponseEntity<CustomerProfileResponse> getCustomerProfileEmailResponseEntity(String customerId,
      CustomerProfileRequest customerProfileRequest) {
    CustomerProfileResponse response = new CustomerProfileResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.put(propertyUtil.getAPIUrl(PropertyConstants.UPDATE_EMAIL_API_END_POINT, customerId, null))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      return processCustomerProfileResponse(apiResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * mapping customer profile phone response
   *
   * @param customerId
   * @param customerProfileRequest for future enhancement
   * @return
   */
  public ResponseEntity<CustomerProfileResponse> getCustomerProfilePhoneResponseEntity(String customerId,
      CustomerProfileRequest customerProfileRequest) {
    CustomerProfileResponse response = new CustomerProfileResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.put(propertyUtil.getAPIUrl(PropertyConstants.UPDATE_PHONE_API_END_POINT, customerId, null))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      return processCustomerProfileResponse(apiResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }


  /**
   * mapping customer profile address response
   *
   * @param customerId
   * @param customerProfileRequest for future enhancement
   * @return
   */
  public ResponseEntity<CustomerProfileResponse> getCustomerProfileAddressResponseEntity(String customerId,
      CustomerProfileRequest customerProfileRequest) {
    CustomerProfileResponse response = new CustomerProfileResponse();

    try {
      HttpResponse<String> apiResponse =
          Unirest.put(propertyUtil.getAPIUrl(PropertyConstants.UPDATE_ADDRESS_API_END_POINT, customerId, null))
              .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      return processCustomerProfileResponse(apiResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * mapping customer profile response
   *
   * @param apiResponse
   * @return
   */
  private ResponseEntity<CustomerProfileResponse> processCustomerProfileResponse(HttpResponse<String> apiResponse) {
    CustomerProfileResponse response = null;
    ApplicationLogger.logInfo(
        MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
        this.getClass());
    if (StringUtils.isNotEmpty(apiResponse.getBody())) {
      ApplicationLogger.logInfo(MessageConstants.RESPONSE_BEFORE_TRANSFORMATION + apiResponse.getBody(), this.getClass());
      response = customerProfileResponseMapper.getManipulatedCustomerProfileResponse(apiResponse.getBody());
      ApplicationLogger.logInfo(MessageConstants.RESPONSE_AFTER_TRANSFORMATION + response, this.getClass());
    }
    return ResponseEntity.ok(response);
  }
}
