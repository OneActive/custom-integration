package com.activeai.integration.banking.api.controller;

import com.activeai.integration.banking.api.services.CustomerProfileService;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.domain.request.CustomerProfileRequest;
import com.activeai.integration.banking.domain.response.CustomerProfileResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Customer Profile Related APIs", description = "Shows API documentation regarding customer profile APIs")
@RestController
public class CustomerProfileApiController {

  @Autowired private CustomerProfileService customerProfileService;

  @GetMapping(value = "/customers/profile/{customerId}", produces = {"application/json"})
  public ResponseEntity<CustomerProfileResponse> getCustomerProfile(@PathVariable("customerId") String customerId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering CustomerProfile Controller", this.getClass());
    return customerProfileService.getCustomerProfileResponseEntity(customerId);
  }

  @PutMapping(value = "/customers/profile/{customerId}/email", produces = {"application/json"})
  public ResponseEntity<CustomerProfileResponse> updateCustomerProfileEmail(@PathVariable("customerId") String customerId,
      @RequestBody final CustomerProfileRequest customerProfileRequest) {
    ApplicationLogger.logInfo("Entering CustomerProfile Email Controller", this.getClass());
    return customerProfileService.getCustomerProfileEmailResponseEntity(customerId, customerProfileRequest);
  }

  @PutMapping(value = "/customers/profile/{customerId}/phoneNo", produces = {"application/json"})
  public ResponseEntity<CustomerProfileResponse> updateCustomerProfilePhone(@PathVariable("customerId") String customerId,
      @RequestBody final CustomerProfileRequest customerProfileRequest) {
    ApplicationLogger.logInfo("Entering CustomerProfile Phone Controller", this.getClass());
    return customerProfileService.getCustomerProfilePhoneResponseEntity(customerId, customerProfileRequest);
  }

  @PutMapping(value = "/customers/profile/{customerId}/address", produces = {"application/json"})
  public ResponseEntity<CustomerProfileResponse> updateCustomerProfileAddress(@PathVariable("customerId") String customerId,
      @RequestBody final CustomerProfileRequest customerProfileRequest) {
    ApplicationLogger.logInfo("Entering CustomerProfile Address Controller", this.getClass());
    return customerProfileService.getCustomerProfileAddressResponseEntity(customerId, customerProfileRequest);
  }
}
