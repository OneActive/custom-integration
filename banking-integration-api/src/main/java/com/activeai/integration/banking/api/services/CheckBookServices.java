package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.APIConstants;
import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.response.CheckBookResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.request.ChequeBookOrderConfirmRequest;
import com.activeai.integration.banking.domain.response.ChequeBookOrderConfirmResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component("checkBookServices")
public class CheckBookServices {

  @Autowired private CheckBookResponseMapper checkBookResponseMapper;
  @Autowired private PropertyUtil propertyUtil;
  public static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

  public ResponseEntity<ChequeBookOrderConfirmResponse> getChequeBookOrderConfirmResponseEntity(
      ChequeBookOrderConfirmRequest chequeBookOrderConfirmRequest) {
    ChequeBookOrderConfirmResponse response = null;
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAPIUrl(PropertyConstants.CHEQUE_BOOK_ORDER_CONFIRM_API_ENDPOINT, chequeBookOrderConfirmRequest.getCustomerId(),
              chequeBookOrderConfirmRequest.getAccount().getAccountNumber())).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger
          .logInfo(MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() +MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
              this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger
            .logInfo("Credit Card Limit Confirm Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = checkBookResponseMapper.getManipulatedCheckBookConfirmResponse(apiResponse.getBody());
        ApplicationLogger
            .logInfo("Credit Card Limit Confirm Response Body After Transformation :" + response, this.getClass());
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }  catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
