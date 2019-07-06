package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.BillPaymentRequest;
import com.activeai.integration.banking.domain.response.BillPaymentResponse;
import com.activeai.integration.banking.domain.response.BillerResponse;
import com.activeai.integration.banking.mapper.response.BillPaymentResponseMapper;
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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.MessageFormat;

@Service("billpaymentService")
public class BillPaymentService {

    @Autowired private BillPaymentResponseMapper billpaymentResponseMapper;
    @Autowired private PropertyUtil propertyUtil;
    private static final String error_message_format = "{0} : {1} : {2}";


    /**
     * Fetches Registered Biller List for selected Customer
     * @param customerId,accountId
     * @return ResponseEntity of type RegisteredBillerResponse
     */
    public ResponseEntity<BillerResponse> getRegisteredBillerResponseEntity(String customerId) {
        BillerResponse response = new BillerResponse();
        try {
            HttpResponse<String> apiResponse =
                    Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.REGISTERED_BILLERS_API_ENDPOINT, customerId,null)).header("cache-control", "no-cache").asString();
            ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
            if (StringUtils.isNotEmpty(apiResponse.getBody())) {
                ApplicationLogger.logInfo("Registered Billers Response Body Before Transformation :" + apiResponse.getBody());
                response = billpaymentResponseMapper.getManipulatedRegisteredBillerResponse(apiResponse.getBody());
                ApplicationLogger.logInfo("Registered Billers Response Body After Transformation :" + response);
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
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<BillerResponse> getBillerDetailsResponseEntity(String customerId, String billerId) {
        BillerResponse response = new BillerResponse();
        try {
            HttpResponse<String> apiResponse =
                    Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.BILLER_DETAILS_API_ENDPOINT, customerId,billerId)).header("cache-control", "no-cache").asString();
            ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
            if (StringUtils.isNotEmpty(apiResponse.getBody())) {
                ApplicationLogger.logInfo("Biller Details Response Body Before Transformation :" + apiResponse.getBody());
                response = billpaymentResponseMapper.getManipulatedBillerDetailsResponse(apiResponse.getBody());
                ApplicationLogger.logInfo("Biller Details Response Body After Transformation :" + response);
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
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<BillPaymentResponse> getBillPaymentResponseEntity(BillPaymentRequest billPaymentRequest) {
        BillPaymentResponse response = new BillPaymentResponse();
        try {
            HttpResponse<String> apiResponse =
                    Unirest.post(propertyUtil.getAPIUrl(PropertyConstants.BILL_PAYMENT_API_ENDPOINT, billPaymentRequest.getCustomerId(), billPaymentRequest.getBillerDetails().getBillerId() )).header("cache-control", "no-cache").asString();
            ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
            if (StringUtils.isNotEmpty(apiResponse.getBody())) {
                ApplicationLogger.logInfo("Bill Payment Confirm Response Body Before Transformation :" + apiResponse.getBody());
                response = billpaymentResponseMapper.getManipulatedBillPaymentResponse(apiResponse.getBody());
                ApplicationLogger.logInfo("Bill Payment Confirm Response Body After Transformation :" + response);
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
