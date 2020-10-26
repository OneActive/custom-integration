package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.ChequeBookOrderConfirmRequest;
import com.activeai.integration.banking.domain.response.ChequeBookOrderConfirmResponse;
import com.activeai.integration.banking.mapper.response.CheckBookResponseMapper;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.PropertyUtil;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
              chequeBookOrderConfirmRequest.getAccount().getAccountNumber())).header("cache-control", "no-cache").asString();
      ApplicationLogger
          .logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Credit Card Limit Confirm Response Body Before Transformation :" + apiResponse.getBody());
        response = checkBookResponseMapper.getManipulatedCheckBookConfirmResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Credit Card Limit Confirm Response Body After Transformation :" + response);
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
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
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
