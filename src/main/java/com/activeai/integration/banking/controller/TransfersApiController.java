package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.FundTransferRequest;
import com.activeai.integration.banking.domain.request.PayeesRequest;
import com.activeai.integration.banking.domain.request.PayeesValidationRequest;
import com.activeai.integration.banking.domain.response.FundTransferResponse;
import com.activeai.integration.banking.domain.response.OneTimeTransferResponse;
import com.activeai.integration.banking.domain.response.PayeesResponse;
import com.activeai.integration.banking.domain.response.PayeesValidationResponse;
import com.activeai.integration.banking.services.TransferService;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Fund Transfer Related APIs", description = "Shows API documentation regarding fund transfer APIs")
@RestController
public class TransfersApiController {

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private TransferService transferService;

  @RequestMapping(value = "/{customerId}/transfer/payees", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<PayeesResponse> getPayeesList(@Valid @RequestBody final PayeesRequest payeeRequest,
      @PathVariable(value = "customerId", required = true) String customerId) {
    return transferService.getPayeesResponseEntity(payeeRequest);
  }

  @RequestMapping(value = "/{customerId}/transfer/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<FundTransferResponse> confirmTransfer(@PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final FundTransferRequest fundTransferRequest) {
    return transferService.getConfirmTransferResponseEntity(fundTransferRequest);
  }


  @RequestMapping(value = "/{customerId}/onetime-transfer/payee/inputs", produces = {"application/json"}, method = RequestMethod.POST)
  public ResponseEntity<OneTimeTransferResponse> getOneTimeTransferInputList(
      @PathVariable(value = "customerId", required = true) String customerId, @RequestBody final PayeesRequest payeesRequest) {
    ApplicationLogger.logInfo("Entering getOneTimeTransferInputList API");
    return transferService.getOneTimeTransferResponseEntity(payeesRequest);
  }
  @RequestMapping(value = "/{customerId}/onetime-transfer/payee/validation", produces = {"application/json"}, method = RequestMethod.POST)
  public ResponseEntity<PayeesValidationResponse> getOneTimeTransferPayeeValidation(
      @PathVariable(value = "customerId") String customerId, @RequestBody final PayeesValidationRequest payeesValidationRequest) {
    ApplicationLogger.logInfo("Entering getOneTimeTransferPayeeValidation API");
    return transferService.getOneTimeTransferPayeeValidationResponseEntity(payeesValidationRequest);
  }
}

