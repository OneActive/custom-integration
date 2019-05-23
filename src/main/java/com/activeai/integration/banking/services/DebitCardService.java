package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.BillPaymentRequest;
import com.activeai.integration.banking.domain.request.DebitCardLimitRequest;
import com.activeai.integration.banking.domain.response.BillPaymentResponse;
import com.activeai.integration.banking.domain.response.DebitCardLimitResponse;
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

import java.io.IOException;
import java.util.Objects;

public class DebitCardService {

  @Autowired private PropertyUtil propertyUtil;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private DebitCardResponseMapper debitCardResponseMapper;

  /**
   * Fetches Card Details for selected card
   *
   * @param customerId,cardId
   * @return ResponseEntity of type DebitCardLimitResponse
   */
  public ResponseEntity<DebitCardLimitResponse> getDebitCardLimitResponseEntity(String customerId, String cardId) {
    DebitCardLimitResponse debitCardLimitResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.DEBIT_CARD_LIMIT_API_END_POINT, customerId, cardId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Debit Card Limit Response Body Before Transformation :" + response.getBody());
        String debitCardLimitResponseString = debitCardResponseMapper.getManipulatedDebitCardLimitResponse(response.getBody());
        ApplicationLogger.logInfo("Debit Card Limit Response Body After Transformation :" + response.getBody());
        debitCardLimitResponse = objectMapper.readValue(debitCardLimitResponseString, DebitCardLimitResponse.class);
      }
      return ResponseEntity.ok(debitCardLimitResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    debitCardLimitResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(debitCardLimitResponse);
  }

  public ResponseEntity<DebitCardLimitResponse> getDebitLimitConfirmResponseEntity(DebitCardLimitRequest debitCardLimitRequest) {
    DebitCardLimitResponse debitCardLimitResponse = null;
    try {
      HttpResponse<String> response = Unirest.get(propertyUtil
          .getAPIUrl(PropertyConstants.DEBIT_CARD_LIMIT_CONFIRM_API_ENDPOINT, debitCardLimitRequest.getCustomerId(),
              debitCardLimitRequest.getBillerDetails().getBillerId())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Debit Card Limit Confirm Response Body Before Transformation :" + response.getBody());
        String debitCardLimitConfirmResponseString = debitCardResponseMapper.getManipulatedDebitCardLimitConfirmResponse(response.getBody());
        ApplicationLogger.logInfo("Debit Card Limit Confirm Response Body After Transformation :" + response.getBody());
        debitCardLimitResponse = objectMapper.readValue(debitCardLimitConfirmResponseString, DebitCardLimitResponse.class);
      }
      return new ResponseEntity<>(debitCardLimitResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(debitCardLimitResponse, HttpStatus.OK);
  }

}