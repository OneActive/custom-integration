package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.APIConstants;
import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.response.CreditCardResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.request.CreditCardLimitConfirmRequest;
import com.activeai.integration.banking.domain.response.CreditCardLimitConfirmResponse;
import com.activeai.integration.banking.domain.response.CreditCardLimitResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service("creditCardService")
public class CreditCardService {

    @Autowired private PropertyUtil propertyUtil;
    @Autowired private CreditCardResponseMapper creditCardResponseMapper;
    private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

    /**
     * mapping credit card limit response
     *
     * @param customerId
     * @param cardNumber
     * @param accessToken for future enhancement
     * @return
     */
    public ResponseEntity<CreditCardLimitResponse> getCreditCardLimitResponseEntity(String customerId, String cardNumber,
        String accessToken) {
        CreditCardLimitResponse response = new CreditCardLimitResponse();
        try {
            HttpResponse<String> apiResponse = Unirest.get(propertyUtil
                .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.CREDIT_CARD_LIMIT_API_END_POINT, customerId,
                    cardNumber)).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
            ApplicationLogger.logInfo(
                MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse
                    .getStatusText(), this.getClass());
            if (StringUtils.isNotEmpty(apiResponse.getBody())) {
                ApplicationLogger
                    .logInfo("Credit Card Limit Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
                response = creditCardResponseMapper.getManipulatedCreditCardLimitResponse(apiResponse.getBody());
                ApplicationLogger.logInfo("Credit Card Limit Response Body After Transformation :" + response, this.getClass());
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
        response.setResult(propertyUtil
            .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
                APIConstants.INTERNAL_SERVER_ERROR, 500));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<CreditCardLimitConfirmResponse> getCreditLimitConfirmResponseEntity(
        CreditCardLimitConfirmRequest creditCardLimitConfirmRequest) {
        CreditCardLimitConfirmResponse response = new CreditCardLimitConfirmResponse();
        try {
            HttpResponse<String> apiResponse = Unirest.get(propertyUtil
                .getAPIUrl(PropertyConstants.CREDIT_CARD_LIMIT_CONFIRM_API_ENDPOINT, creditCardLimitConfirmRequest.getCustomerId(),
                    creditCardLimitConfirmRequest.getCardDetails().getCardNumber()))
                .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
            ApplicationLogger.logInfo(
                MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse
                    .getStatusText(), this.getClass());
            if (StringUtils.isNotEmpty(apiResponse.getBody())) {
                ApplicationLogger
                    .logInfo("Credit Card Limit Confirm Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
                response = creditCardResponseMapper.getManipulatedCreditCardLimitConfirmResponse(apiResponse.getBody());
                ApplicationLogger.logInfo("Credit Card Limit Confirm Response Body After Transformation :" + response, this.getClass());
            }
            return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
        } catch (UnirestException e) {
            ApplicationLogger.logError(MessageFormat
                .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
                    this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
        } catch (Exception e) {
            ApplicationLogger.logError(MessageFormat
                .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(),
                    ExceptionUtils.getStackTrace(e)));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
