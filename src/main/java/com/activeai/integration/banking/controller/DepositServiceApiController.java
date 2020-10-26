package com.activeai.integration.banking.controller;


import com.activeai.integration.banking.domain.request.DepositServiceRequest;
import com.activeai.integration.banking.domain.response.DepositServiceResponse;
import com.activeai.integration.banking.services.DepositService;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@Api(value = "Deposit Plan Related APIs", description = "Shows API documentation regarding deposit plan APIs")
@RestController
public class DepositServiceApiController {

  @Autowired private ObjectMapper objectMapper;
  @Autowired private DepositService depositService;

  @ApiOperation(value = "Returns available deposit service plans")
  @RequestMapping(value = "/{customerId}/deposit/plans", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<DepositServiceResponse> getDepositPlans(@PathVariable(name = "customerId", required = true) String customerId,
      @RequestBody final DepositServiceRequest depositServiceRequest) {
    ApplicationLogger.logInfo("Entering getDepositPlans API");
    return depositService.getDepositPlansResponseEntity(depositServiceRequest);
  }

  @ApiOperation(value = "Returns nominees for deposit service")
  @RequestMapping(value = "/{customerId}/deposit/nominees", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<DepositServiceResponse> getDepositPlanNominees(
      @PathVariable(name = "customerId", required = true) String customerId,
      @RequestBody final DepositServiceRequest depositServiceRequest) {
    ApplicationLogger.logInfo("Entering getDepositPlanNominees API");
    return depositService.getDepositPlanNomineesResponseEntity(depositServiceRequest);
  }

  @ApiOperation(value = "Final API call for fixed deposit status and reference id")
  @RequestMapping(value = "/{customerId}/deposit/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<DepositServiceResponse> confirmDepositService(@PathVariable(name = "customerId", required = true) String customerId,
      @RequestBody final DepositServiceRequest depositServiceRequest) {
    ApplicationLogger.logInfo("Entering confirmDepositService API");
    return depositService.getDepositPlanFinalResponseResponseEntity(depositServiceRequest);
  }
}
