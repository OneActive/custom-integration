package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.APIConstants;
import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.response.FundTransferResponseMapper;
import com.activeai.integration.banking.api.mapper.response.OneTimeTransferResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.constants.StatusEnum;
import com.activeai.integration.banking.domain.model.Result;
import com.activeai.integration.banking.domain.request.FundTransferRequest;
import com.activeai.integration.banking.domain.request.PayeesRequest;
import com.activeai.integration.banking.domain.request.PayeesValidationRequest;
import com.activeai.integration.banking.domain.response.FundTransferResponse;
import com.activeai.integration.banking.domain.response.OneTimeTransferResponse;
import com.activeai.integration.banking.domain.response.PayeesResponse;
import com.activeai.integration.banking.domain.response.PayeesValidationResponse;
import com.activeai.integration.data.model.CoreBankingModel;
import com.activeai.integration.data.service.CoreBankingService;
import com.activeai.integration.data.service.TransferServiceData;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Objects;

@Service("transferService")
public class TransferService {

  @Autowired private FundTransferResponseMapper fundTransferResponseMapper;
  @Autowired private OneTimeTransferResponseMapper oneTimeTransferResponseMapper;
  @Autowired private PropertyUtil propertyUtil;
  @Autowired private TransferServiceData transferServiceData;
  @Autowired private CoreBankingService coreBankingService;
  private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

  public ResponseEntity<PayeesResponse> getPayeesResponseEntity(PayeesRequest payeeRequest) {
    // Fetching Payees Response from cache, Remove this later
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(payeeRequest.getCustomerId());
    PayeesResponse response = coreBankingModel.getPayeesResponse();
    if (Objects.nonNull(response)) {
      ApplicationLogger.logInfo("Fetching from cache", this.getClass());
      return ResponseEntity.ok(response);
    }
    // Till this
    try {
      /**
       * Here only for first account fetching payees
       * But you need to fetch all payees for each accounts where user added as beneficiary
       */
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil.getAPIUrlForPayees(PropertyConstants.PAYEES_API_END_POINT, payeeRequest))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Payees Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = fundTransferResponseMapper.getManipulatedPayeesResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Payees Response Body After Transformation :" + apiResponse.getBody(), this.getClass());
        // Caching Payees Response, Remove this later
        ApplicationLogger.logInfo("Caching Payee Response", this.getClass());
        coreBankingModel.setPayeesResponse(response);
        coreBankingService.saveCoreBankingModel(coreBankingModel);
        // Till this
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
    if (response != null) {
      response.setResult(propertyUtil
          .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR, 500));
    }
    return ResponseEntity.ok(response);
  }

  public ResponseEntity<FundTransferResponse> getConfirmTransferResponseEntity(FundTransferRequest fundTransferRequest) {
    FundTransferResponse response = new FundTransferResponse();
    if (fundTransferRequest.getCustomerId().contains("testuser")) {
      customResponseForConfirmation(response, fundTransferRequest);
      return ResponseEntity.ok(response);
    }
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrlForFundTransfer(PropertyConstants.TRANSFER_CONFIRM_API_END_POINT, fundTransferRequest))
              .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Confirm Transfer Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = fundTransferResponseMapper.getManipulatedFundTransferResponse(apiResponse.getBody(), fundTransferRequest);
        ApplicationLogger.logInfo("Confirm Transfer Response Body After Transformation :" + response, this.getClass());
        // Updating transaction details on cache, Remove this later
        transferServiceData.updateTransactionDetailsOnCache(fundTransferRequest);
        // Till this
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

  /**
   * Temporary method to generate response directly
   *
   * @param response
   */
  private FundTransferResponse customResponseForConfirmation(FundTransferResponse response, FundTransferRequest fundTransferRequest) {
    Result result = new Result();
    result.setStatus(200);
    result.setMessageCode("OK");
    result.setMessage("Fund Transfer successful");
    response.setResult(result);
    response.setTransactionStatus(StatusEnum.SUCCESS);
    response.setTxnReferenceId(
        RandomStringUtils.random(com.activeai.integration.banking.common.constants.PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
    response.setTransferAmount(Double.valueOf(fundTransferRequest.getAmount()));
    return response;
  }

  /**
   * To get One time transfer payee detail inputs
   *
   * @param payeesRequest
   * @return
   */
  public ResponseEntity<OneTimeTransferResponse> getOneTimeTransferResponseEntity(PayeesRequest payeesRequest) {
    OneTimeTransferResponse response = new OneTimeTransferResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.post(propertyUtil.getAPIUrl(PropertyConstants.ONE_TIME_TRANSFER_API_END_POINT, payeesRequest.getCustomerId(), null))
              .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          "One Time Transfer API Response status: " + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse
              .getStatusText(), this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" One Time Transfer  Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = oneTimeTransferResponseMapper.getManipulatedOneTimeTransferResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("One Time Transfer  Response Body After Transformation :" + response, this.getClass());
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

  /**
   * To validate One time transfer payee inputs
   *
   * @param payeesValidationRequest
   * @return
   */
  public ResponseEntity<PayeesValidationResponse> getOneTimeTransferPayeeValidationResponseEntity(
      PayeesValidationRequest payeesValidationRequest) {
    PayeesValidationResponse response = new PayeesValidationResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrl(PropertyConstants.ONE_TIME_TRANSFER_PAYEE_VALIDATION_API_END_POINT, payeesValidationRequest.getCustomerId(), null))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          "One Time Transfer Payee Validation API Response status: " + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT
              + apiResponse.getStatusText(), this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger
            .logInfo(" One Time Transfer Payee Validation Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = oneTimeTransferResponseMapper.getManipulatedOneTimeTransferPayeeResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("One Time Transfer Payee Validation Response Body After Transformation :" + response, this.getClass());
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
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }
}

