package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.APIConstants;
import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.response.BillPaymentResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.request.BillPaymentRequest;
import com.activeai.integration.banking.domain.response.BillPaymentResponse;
import com.activeai.integration.banking.domain.response.BillerResponse;
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

@Service("billpaymentService")
public class BillPaymentService {

    @Autowired private BillPaymentResponseMapper billpaymentResponseMapper;
    @Autowired private PropertyUtil propertyUtil;
    private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";


    /**
     * Fetches Registered Biller List for selected Customer
     * @param customerId,accountId
     * @param accessToken for future enhancement
     * @return ResponseEntity of type RegisteredBillerResponse
     */
    public ResponseEntity<BillerResponse> getRegisteredBillerResponseEntity(String customerId, String accessToken) {
        BillerResponse response = new BillerResponse();
        try {
            HttpResponse<String> apiResponse =
                    Unirest.get(propertyUtil.getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.REGISTERED_BILLERS_API_ENDPOINT, customerId,null)).header(
                        APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
            ApplicationLogger
                .logInfo(MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() +MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
                    this.getClass());
            if (StringUtils.isNotEmpty(apiResponse.getBody())) {
                ApplicationLogger
                    .logInfo("Registered Billers Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
                response = billpaymentResponseMapper.getManipulatedRegisteredBillerResponse(apiResponse.getBody());
                ApplicationLogger
                    .logInfo("Registered Billers Response Body After Transformation :" + response, this.getClass());
            }
            return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
        } catch (UnirestException e) {
            ApplicationLogger.logError(MessageFormat
                .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
        }  catch (Exception e) {
            ApplicationLogger.logError(MessageFormat
                .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
        }
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    /**
     *
     * @param customerId
     * @param billerId
     * @param accessToken for future enhancement
     * @return
     */
    public ResponseEntity<BillerResponse> getBillerDetailsResponseEntity(String customerId, String billerId, String accessToken) {
        BillerResponse response = new BillerResponse();
        try {
            HttpResponse<String> apiResponse =
                    Unirest.get(propertyUtil.getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.BILLER_DETAILS_API_ENDPOINT, customerId,billerId)).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
            ApplicationLogger
                .logInfo(MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() +MessageConstants.AND_RESPONSE_STATUS_TEXT+ apiResponse.getStatusText(),
                    this.getClass());
            if (StringUtils.isNotEmpty(apiResponse.getBody())) {
                ApplicationLogger
                    .logInfo("Biller Details Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
                response = billpaymentResponseMapper.getManipulatedBillerDetailsResponse(apiResponse.getBody());
                ApplicationLogger.logInfo("Biller Details Response Body After Transformation :" + response, this.getClass());
            }
            return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
        } catch (UnirestException e) {
            ApplicationLogger.logError(MessageFormat
                .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
        }  catch (Exception e) {
            ApplicationLogger.logError(MessageFormat
                .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
        }
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<BillPaymentResponse> getBillPaymentResponseEntity(BillPaymentRequest billPaymentRequest) {
        BillPaymentResponse response = new BillPaymentResponse();
        try {
            HttpResponse<String> apiResponse =
                    Unirest.post(propertyUtil.getAPIUrl(PropertyConstants.BILL_PAYMENT_API_ENDPOINT, billPaymentRequest.getCustomerId(), billPaymentRequest.getBillerDetails().getBillerId() )).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
            ApplicationLogger
                .logInfo(MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() +MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
                    this.getClass());
            if (StringUtils.isNotEmpty(apiResponse.getBody())) {
                ApplicationLogger
                    .logInfo("Bill Payment Confirm Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
                response = billpaymentResponseMapper.getManipulatedBillPaymentResponse(apiResponse.getBody(), billPaymentRequest);
                ApplicationLogger
                    .logInfo("Bill Payment Confirm Response Body After Transformation :" + response, this.getClass());
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
