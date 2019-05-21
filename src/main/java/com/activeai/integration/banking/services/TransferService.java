package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.FundTransferRequest;
import com.activeai.integration.banking.domain.request.PayeesRequest;
import com.activeai.integration.banking.domain.response.FundTransferResponse;
import com.activeai.integration.banking.domain.response.PayeesResponse;
import com.activeai.integration.banking.mapper.response.FundTransferResponseMapper;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.PropertyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service("transferService")
public class TransferService {

  @Autowired private FundTransferResponseMapper fundTransferResponseMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private PropertyUtil propertyUtil;

  public ResponseEntity<PayeesResponse> getPayeesResponseEntity(PayeesRequest payeeRequest) {
    PayeesResponse payeeResponse = new PayeesResponse();
    try {
      /**
       * Here only for first account fetching payees
       * But you need to fetch all payees for each accounts where user added as beneficiary
       */
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrlForPayees(PropertyConstants.PAYEES_API_END_POINT, payeeRequest))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Payees Response Body Before Transformation :" + response.getBody());
        String payeesResponseString = fundTransferResponseMapper.getManipulatedPayeesResponse(response.getBody());
        ApplicationLogger.logInfo("Payees Response Body After Transformation :" + response.getBody());
        payeeResponse = objectMapper.readValue(payeesResponseString, PayeesResponse.class);
      }
      return ResponseEntity.ok(payeeResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    payeeResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(payeeResponse);
  }

  public ResponseEntity<FundTransferResponse> getConfirmTransferResponseEntity(FundTransferRequest fundTransferRequest) {
    FundTransferResponse fundTransferResponse = new FundTransferResponse();
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrlForFundTransfer(PropertyConstants.TRANSFER_CONFIRM_API_END_POINT, fundTransferRequest))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Confirm Transfer Response Body Before Transformation :" + response.getBody());
        String ConfirmTransferResponseString = fundTransferResponseMapper.getManipulatedFundTransferResponse(response.getBody());
        ApplicationLogger.logInfo("Confirm Transfer Response Body After Transformation :" + response.getBody());
        fundTransferResponse = objectMapper.readValue(ConfirmTransferResponseString, FundTransferResponse.class);
      }
      return ResponseEntity.ok(fundTransferResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    fundTransferResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(fundTransferResponse);
  }
}
