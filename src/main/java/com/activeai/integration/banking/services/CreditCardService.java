package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.CreditCardLimitConfirmRequest;
import com.activeai.integration.banking.domain.request.DebitCardLimitConfirmRequest;
import com.activeai.integration.banking.domain.response.CreditCardLimitConfirmResponse;
import com.activeai.integration.banking.domain.response.CreditCardLimitResponse;
import com.activeai.integration.banking.domain.response.DebitCardLimitConfirmResponse;
import com.activeai.integration.banking.mapper.response.CreditCardResponseMapper;
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

@Service("creditCardService")
public class CreditCardService {

    @Autowired private PropertyUtil propertyUtil;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private CreditCardResponseMapper creditCardResponseMapper;

    public ResponseEntity<CreditCardLimitResponse> getCreditCardLimitResponseEntity(String customerId, String cardNumber) {
        CreditCardLimitResponse creditCardLimitResponse = null;
        try {
            HttpResponse<String> response =
                Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARD_LIMIT_API_END_POINT, customerId, cardNumber))
                    .header("cache-control", "no-cache").asString();
            ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
            if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
                ApplicationLogger.logInfo("Credit Card Limit Response Body Before Transformation :" + response.getBody());
                String creditCardLimitResponseString = creditCardResponseMapper.getManipulatedCreditCardLimitResponse(response.getBody());
                ApplicationLogger.logInfo("Credit Card Limit Response Body After Transformation :" + response.getBody());
                creditCardLimitResponse = objectMapper.readValue(creditCardLimitResponseString, CreditCardLimitResponse.class);
            }
            return ResponseEntity.ok(creditCardLimitResponse);
        } catch (UnirestException e) {
            ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
        } catch (IOException e) {
            ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
        } catch (Exception e) {
            ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
        }
        creditCardLimitResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
        return ResponseEntity.ok(creditCardLimitResponse);
    }

    public ResponseEntity<CreditCardLimitConfirmResponse> getCreditLimitConfirmResponseEntity(CreditCardLimitConfirmRequest creditCardLimitConfirmRequest) {
        CreditCardLimitConfirmResponse creditCardLimitConfirmResponse = null;
        try {
            HttpResponse<String> response = Unirest.get(propertyUtil
                .getAPIUrl(PropertyConstants.CREDIT_CARD_LIMIT_CONFIRM_API_ENDPOINT, creditCardLimitConfirmRequest.getCustomerId(),
                    creditCardLimitConfirmRequest.getCardDetails().getCardNumber())).header("cache-control", "no-cache").asString();
            ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
            if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
                ApplicationLogger.logInfo("Credit Card Limit Confirm Response Body Before Transformation :" + response.getBody());
                String creditCardLimitConfirmResponseString = creditCardResponseMapper.getManipulatedCreditCardLimitConfirmResponse(response.getBody());
                ApplicationLogger.logInfo("Credit Card Limit Confirm Response Body After Transformation :" + response.getBody());
                creditCardLimitConfirmResponse = objectMapper.readValue(creditCardLimitConfirmResponseString, CreditCardLimitConfirmResponse.class);
            }
            return new ResponseEntity<>(creditCardLimitConfirmResponse, HttpStatus.valueOf(response.getStatus()));
        } catch (UnirestException e) {
            ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
        } catch (IOException e) {
            ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
        } catch (Exception e) {
            ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
        }
        return new ResponseEntity<>(creditCardLimitConfirmResponse, HttpStatus.OK);
    }
}
