package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.mapper.response.BillPaymentResponseMapper;
import com.activeai.integration.banking.model.BillerResponse;
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
}
