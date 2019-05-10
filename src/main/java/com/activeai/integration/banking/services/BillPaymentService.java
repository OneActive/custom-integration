package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.BillPaymentRequest;
import com.activeai.integration.banking.domain.response.BillPaymentConfirmResponse;
import com.activeai.integration.banking.mapper.response.BillPaymentResponseMapper;
import com.activeai.integration.banking.domain.response.BillerResponse;
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

@Service("billpaymentService")
public class BillPaymentService {

    @Autowired private BillPaymentResponseMapper
            billpaymentResponseMapper;

    @Autowired
    private PropertyUtil propertyUtil;

    @Autowired private ObjectMapper objectMapper;


    /**
     * Fetches Registered Biller List for selected Customer
     * @param customerId,accountId
     * @return ResponseEntity of type RegisteredBillerResponse
     */
    public ResponseEntity<BillerResponse> getRegisteredBillerResponseEntity(String customerId) {
        BillerResponse billerResponse = null;
        try {
            HttpResponse<String> response =
                    Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.REGISTERED_BILLERS_API_ENDPOINT, customerId,null)).header("cache-control", "no-cache").asString();
            ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
            if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
                ApplicationLogger.logInfo("Registered Billers Response Body Before Transformation :" + response.getBody());
                String billerResponseString = billpaymentResponseMapper.getManipulatedRegisteredBillerResponse(response.getBody());
                ApplicationLogger.logInfo("Registered Billers Response Body After Transformation :" + response.getBody());
                billerResponse = objectMapper.readValue(billerResponseString, BillerResponse.class);
            }
            return new ResponseEntity<>(billerResponse, HttpStatus.valueOf(response.getStatus()));
        } catch (UnirestException e) {
            ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
        } catch (IOException e) {
            ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
        } catch (Exception e) {
            ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
        }
        return new ResponseEntity<>(billerResponse, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<BillerResponse> getBillerDetailsResponseEntity(String customerId, String billerId) {
        BillerResponse billerDetailsResponse = null;
        try {
            HttpResponse<String> response =
                    Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.BILLER_DETAILS_API_ENDPOINT, customerId,billerId)).header("cache-control", "no-cache").asString();
            ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
            if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
                ApplicationLogger.logInfo("Biller Details Response Body Before Transformation :" + response.getBody());
                String billerDetailsResponseString = billpaymentResponseMapper.getManipulatedBillerDetailsResponse(response.getBody());
                ApplicationLogger.logInfo("Biller Details Response Body After Transformation :" + response.getBody());
                billerDetailsResponse = objectMapper.readValue(billerDetailsResponseString, BillerResponse.class);
            }
            return new ResponseEntity<>(billerDetailsResponse, HttpStatus.valueOf(response.getStatus()));
        } catch (UnirestException e) {
            ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
        } catch (IOException e) {
            ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
        } catch (Exception e) {
            ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
        }
        return new ResponseEntity<>(billerDetailsResponse, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<BillPaymentConfirmResponse> getBillPaymentResponseEntity(BillPaymentRequest billPaymentRequest) {
        BillPaymentConfirmResponse billPaymentConfirmResponse = null;
        try {
            HttpResponse<String> response =
                    Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.BILL_PAYMENT_CONFIRM_API_ENDPOINT, billPaymentRequest.getCustomerId(),billPaymentRequest.getBillerDetails().getBillerId() )).header("cache-control", "no-cache").asString();
            ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
            if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
                ApplicationLogger.logInfo("Bill Payment Confirm Response Body Before Transformation :" + response.getBody());
                String billPaymentConfirmResponseString = billpaymentResponseMapper.getManipulatedBillPaymentConfirmResponse(response.getBody());
                ApplicationLogger.logInfo("Bill Payment Confirm Response Body After Transformation :" + response.getBody());
                billPaymentConfirmResponse = objectMapper.readValue(billPaymentConfirmResponseString, BillPaymentConfirmResponse.class);
            }
            return new ResponseEntity<>(billPaymentConfirmResponse, HttpStatus.valueOf(response.getStatus()));
        } catch (UnirestException e) {
            ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
        } catch (IOException e) {
            ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
        } catch (Exception e) {
            ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
        }
        return new ResponseEntity<>(billPaymentConfirmResponse, HttpStatus.OK);
    }
}
