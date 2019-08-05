package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.DebitCardLimitConfirmRequest;
import com.activeai.integration.banking.domain.response.DebitCardLimitResponse;
import com.activeai.integration.banking.domain.response.DebitCardLimitConfirmResponse;
import com.activeai.integration.banking.mapper.response.DebitCardResponseMapper;
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

@Service("debitCardService")
public class DebitCardService {

  @Autowired private PropertyUtil propertyUtil;
  @Autowired private DebitCardResponseMapper debitCardResponseMapper;
  private static final String error_message_format = "{0} : {1} : {2}";

  /**
   * Fetches Card Details for selected card
   *
   * @param customerId,cardNumber
   * @return ResponseEntity of type DebitCardLimitResponse
   */
  public ResponseEntity<DebitCardLimitResponse> getDebitCardLimitResponseEntity(String customerId, String cardNumber, String accessToken) {
    DebitCardLimitResponse response = new DebitCardLimitResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.DEBIT_CARD_LIMIT_API_END_POINT, customerId, cardNumber))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (Objects.nonNull(apiResponse) && StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Debit Card Limit Response Body Before Transformation :" + apiResponse.getBody());
        response = debitCardResponseMapper.getManipulatedDebitCardLimitResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Debit Card Limit Response Body After Transformation :" + response);
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

  public ResponseEntity<DebitCardLimitConfirmResponse> getDebitLimitConfirmResponseEntity(DebitCardLimitConfirmRequest debitCardLimitConfirmRequest) {
    DebitCardLimitConfirmResponse response = new DebitCardLimitConfirmResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrl(PropertyConstants.DEBIT_CARD_LIMIT_CONFIRM_API_ENDPOINT, debitCardLimitConfirmRequest.getCustomerId(),
              debitCardLimitConfirmRequest.getCardDetails().getCardNumber())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Debit Card Limit Confirm Response Body Before Transformation :" + apiResponse.getBody());
        response = debitCardResponseMapper.getManipulatedDebitCardLimitConfirmResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Debit Card Limit Confirm Response Body After Transformation :" + response);
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
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
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}