package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.BillPaymentRequest;
import com.activeai.integration.banking.domain.response.BillPaymentResponse;
import com.activeai.integration.banking.domain.response.BillerCategoriesResponse;
import com.activeai.integration.banking.domain.response.BillerResponse;
import com.activeai.integration.banking.services.BillPaymentService;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Api(value = "BillPayment Related APIs", description = "Shows API documentation regarding billpayment APIs")
@RestController
public class BillPaymentApiController {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private BillPaymentService billpaymentService;

  @ApiOperation(value = "Returns list of registered billers based on customerId")
  @RequestMapping(value = "/{customerId}/billers", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<BillerResponse> getRegisteredBillerListForCustomer(
      @PathVariable(name = "customerId", required = true) String customerId, @RequestParam(name = "accessToken") String accessToken) {
    ApplicationLogger.logInfo("Entering getRegisteredBillers API");
    return billpaymentService.getRegisteredBillerResponseEntity(customerId, accessToken);
  }

  @ApiOperation(value = "Returns Biller details of biller based on billerId")
  @RequestMapping(value = "/{customerId}/billers/{billerId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<BillerResponse> getBillerDetialsofBiller(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "billerId", required = true) String billerId, @RequestParam(name = "accessToken") String accessToken) {
    ApplicationLogger.logInfo("Entering getBillerDetails API");
    return billpaymentService.getBillerDetailsResponseEntity(customerId, billerId, accessToken);
  }

  @ApiOperation(value = "Returns confirmation of bill payment")
  @RequestMapping(value = "/{customerId}/bill/payment/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<BillPaymentResponse> confirmBillPayment(@PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final BillPaymentRequest billPaymentRequest) {
    ApplicationLogger.logInfo("Entering getBillPaymentConfirm API");
    return billpaymentService.getBillPaymentResponseEntity(billPaymentRequest);
  }
}