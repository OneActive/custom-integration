package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.ChequeBookOrderConfirmRequest;
import com.activeai.integration.banking.domain.response.ChequeBookOrderConfirmResponse;
import com.activeai.integration.banking.services.CheckBookServices;
import com.activeai.integration.banking.utils.ApplicationLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Check Book Related APIs", description = "Shows API documentation regarding Check Book Services APIs")
@RestController
public class CheckBookApiController {

  @Autowired private CheckBookServices checkBookServices;

  @ApiOperation(value = "Returns confirmation of order cheque book")
  @RequestMapping(value = "/{customerId}/chequebook/order/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<ChequeBookOrderConfirmResponse> confirmChequeBookOrder(
      @PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final ChequeBookOrderConfirmRequest chequeBookConfirmRequest) {
    ApplicationLogger.logInfo("Entering getconfirmChequeBookOrder API");
    return checkBookServices.getChequeBookOrderConfirmResponseEntity(chequeBookConfirmRequest);
  }
}
