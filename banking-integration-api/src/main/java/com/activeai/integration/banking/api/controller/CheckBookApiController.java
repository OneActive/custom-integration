package com.activeai.integration.banking.api.controller;

import com.activeai.integration.banking.api.services.CheckBookServices;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.domain.request.ChequeBookOrderConfirmRequest;
import com.activeai.integration.banking.domain.response.ChequeBookOrderConfirmResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Check Book Related APIs", description = "Shows API documentation regarding Check Book Services APIs")
@RestController
public class CheckBookApiController {

  @Autowired private CheckBookServices checkBookServices;

  @ApiOperation(value = "Returns confirmation of order cheque book")
  @PostMapping(value = "/{customerId}/chequebook/order/confirm", produces = {"application/json"}, consumes = {
      "application/json"}) public ResponseEntity<ChequeBookOrderConfirmResponse> confirmChequeBookOrder(
      @PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final ChequeBookOrderConfirmRequest chequeBookConfirmRequest) {
    ApplicationLogger.logInfo("Entering getconfirmChequeBookOrder API", this.getClass());
    return checkBookServices.getChequeBookOrderConfirmResponseEntity(chequeBookConfirmRequest);
  }
}
