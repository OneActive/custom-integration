package com.activeai.integration.banking.api.controller;

import com.activeai.integration.banking.api.services.BillPaymentService;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.domain.request.BillPaymentRequest;
import com.activeai.integration.banking.domain.response.BillPaymentResponse;
import com.activeai.integration.banking.domain.response.BillerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "BillPayment Related APIs", description = "Shows API documentation regarding billpayment APIs")
@RestController
public class BillPaymentApiController {


  @Autowired private BillPaymentService billpaymentService;

  @ApiOperation(value = "Returns list of registered billers based on customerId")
  @GetMapping(value = "/{customerId}/billers", produces = {"application/json"})
  public ResponseEntity<BillerResponse> getRegisteredBillerListForCustomer(
      @PathVariable(name = "customerId", required = true) String customerId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getRegisteredBillers API", this.getClass());
    return billpaymentService.getRegisteredBillerResponseEntity(customerId, accessToken);
  }

  @ApiOperation(value = "Returns Biller details of biller based on billerId")
  @GetMapping(value = "/{customerId}/billers/{billerId}", produces = {"application/json"})
  public ResponseEntity<BillerResponse> getBillerDetialsofBiller(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "billerId", required = true) String billerId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getBillerDetails API", this.getClass());
    return billpaymentService.getBillerDetailsResponseEntity(customerId, billerId, accessToken);
  }

  @ApiOperation(value = "Returns confirmation of bill payment")
  @PostMapping(value = "/{customerId}/bill/payment/confirm", produces = {"application/json"}, consumes = {"application/json"})
  public ResponseEntity<BillPaymentResponse> confirmBillPayment(@PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final BillPaymentRequest billPaymentRequest) {
    ApplicationLogger.logInfo("Entering getBillPaymentConfirm API", this.getClass());
    return billpaymentService.getBillPaymentResponseEntity(billPaymentRequest);
  }
}
