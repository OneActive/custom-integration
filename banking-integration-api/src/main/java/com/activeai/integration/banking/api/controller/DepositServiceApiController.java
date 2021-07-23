package com.activeai.integration.banking.api.controller;


import com.activeai.integration.banking.api.services.DepositService;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.domain.request.DepositServiceRequest;
import com.activeai.integration.banking.domain.response.DepositServiceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "Deposit Plan Related APIs", description = "Shows API documentation regarding deposit plan APIs")
@RestController
public class DepositServiceApiController {

  @Autowired private DepositService depositService;

  @ApiOperation(value = "Returns available deposit service plans")
  @PostMapping(value = "/{customerId}/deposit/plans", produces = {"application/json"}, consumes = {"application/json"})
  public ResponseEntity<DepositServiceResponse> getDepositPlans(@PathVariable(name = "customerId", required = true) String customerId,
      @RequestBody final DepositServiceRequest depositServiceRequest) {
    ApplicationLogger.logInfo("Entering getDepositPlans API", this.getClass());
    return depositService.getDepositPlansResponseEntity(depositServiceRequest);
  }

  @ApiOperation(value = "Returns nominees for deposit service")
  @PostMapping(value = "/{customerId}/deposit/nominees", produces = {"application/json"}, consumes = {"application/json"})
  public ResponseEntity<DepositServiceResponse> getDepositPlanNominees(
      @PathVariable(name = "customerId", required = true) String customerId,
      @RequestBody final DepositServiceRequest depositServiceRequest) {
    ApplicationLogger.logInfo("Entering getDepositPlanNominees API", this.getClass());
    return depositService.getDepositPlanNomineesResponseEntity(depositServiceRequest);
  }

  @ApiOperation(value = "Final API call for fixed deposit status and reference id")
  @PostMapping(value = "/{customerId}/deposit/confirm", produces = {"application/json"}, consumes = {"application/json"})
  public ResponseEntity<DepositServiceResponse> confirmDepositService(@PathVariable(name = "customerId", required = true) String customerId,
      @RequestBody final DepositServiceRequest depositServiceRequest) {
    ApplicationLogger.logInfo("Entering confirmDepositService API", this.getClass());
    return depositService.getDepositPlanFinalResponseResponseEntity(depositServiceRequest);
  }
}
