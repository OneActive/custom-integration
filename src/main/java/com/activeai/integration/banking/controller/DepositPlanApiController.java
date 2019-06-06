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


@Api(value = "Deposit Plan Related APIs", description = "Shows API Documentation Regarding Deposit Plan APIs")
@RestController
public class DepositPlanApiController {

  @Autowired private ObjectMapper objectMapper;
  @Autowired private DepositService depositService;

  @ApiOperation(value = "Returns available deposit plans")
  @RequestMapping(value = "/{customerId}/deposit/plans", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
  public ResponseEntity<DepositServiceResponse> getDepositPlans(@PathVariable(name = "customerId", required = true) String customerId,@RequestBody final DepositServiceRequest depositServiceRequest) {
    ApplicationLogger.logInfo("Entering getDepositPlans API");
    return depositService.getDepositPlansResponseEntity(depositServiceRequest);
  }
  @ApiOperation(value = "Returns nominees for deposit plans")
  @RequestMapping(value = "/{customerId}/deposit/nominees", produces = {"application/json"},consumes = {"application/json"}, method = RequestMethod.POST)
  public ResponseEntity<DepositServiceResponse> getDepositPlanNominees(@PathVariable(name = "customerId", required = true) String customerId,@RequestBody final DepositServiceRequest depositServiceRequest) {
    ApplicationLogger.logInfo("Entering getDepositPlanNominees API");
    return depositService.getDepositPlanNomineesResponseEntity(depositServiceRequest);
  }

  @ApiOperation(value = "Returns fd status and reference id ")
  @RequestMapping(value = "/{customerId}/deposit/plan/apicall", produces = {"application/json"}, consumes = {"application/json"},method = RequestMethod.POST)
  public ResponseEntity<DepositServiceResponse> getDepositPlanFinalResponse(@PathVariable(name = "customerId", required = true) String customerId,@RequestBody final DepositServiceRequest depositServiceRequest) {
    ApplicationLogger.logInfo("Entering getDepositPlanFinalResponse API");
    return depositService.getDepositPlanFinalResponseResponseEntity(depositServiceRequest);
  }
}
