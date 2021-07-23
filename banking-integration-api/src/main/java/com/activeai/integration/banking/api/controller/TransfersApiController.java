package com.activeai.integration.banking.api.controller;

import com.activeai.integration.banking.api.services.TransferService;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.domain.request.FundTransferRequest;
import com.activeai.integration.banking.domain.request.PayeesRequest;
import com.activeai.integration.banking.domain.request.PayeesValidationRequest;
import com.activeai.integration.banking.domain.response.FundTransferResponse;
import com.activeai.integration.banking.domain.response.OneTimeTransferResponse;
import com.activeai.integration.banking.domain.response.PayeesResponse;
import com.activeai.integration.banking.domain.response.PayeesValidationResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Fund Transfer Related APIs", description = "Shows API documentation regarding fund transfer APIs")
@RestController
public class TransfersApiController {

  @Autowired private TransferService transferService;

  @PostMapping(value = "/{customerId}/transfer/payees", produces = {"application/json"}, consumes = {"application/json"})
  public ResponseEntity<PayeesResponse> getPayeesList(@Valid @RequestBody final PayeesRequest payeeRequest,
      @PathVariable(value = "customerId", required = true) String customerId) {
    return transferService.getPayeesResponseEntity(payeeRequest);
  }

  @PostMapping(value = "/{customerId}/transfer/confirm", produces = {"application/json"}, consumes = {"application/json"})
  public ResponseEntity<FundTransferResponse> confirmTransfer(@PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final FundTransferRequest fundTransferRequest) {
    return transferService.getConfirmTransferResponseEntity(fundTransferRequest);
  }


  @PostMapping(value = "/{customerId}/onetime-transfer/payee/inputs", produces = {"application/json"})
  public ResponseEntity<OneTimeTransferResponse> getOneTimeTransferInputList(
      @PathVariable(value = "customerId", required = true) String customerId, @RequestBody final PayeesRequest payeesRequest) {
    ApplicationLogger.logInfo("Entering getOneTimeTransferInputList API", this.getClass());
    return transferService.getOneTimeTransferResponseEntity(payeesRequest);
  }

  @PostMapping(value = "/{customerId}/onetime-transfer/payee/validation", produces = {"application/json"})
  public ResponseEntity<PayeesValidationResponse> getOneTimeTransferPayeeValidation(@PathVariable(value = "customerId") String customerId,
      @RequestBody final PayeesValidationRequest payeesValidationRequest) {
    ApplicationLogger.logInfo("Entering getOneTimeTransferPayeeValidation API", this.getClass());
    return transferService.getOneTimeTransferPayeeValidationResponseEntity(payeesValidationRequest);
  }
}

