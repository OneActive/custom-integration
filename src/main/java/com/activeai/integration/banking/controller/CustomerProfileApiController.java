package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.CustomerProfileRequest;
import com.activeai.integration.banking.domain.response.CustomerProfileResponse;
import com.activeai.integration.banking.services.CustomerProfileService;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.annotations.Beta;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Beta
@Api(value = "Customer Profile Related APIs", description = "Shows API documentation regarding customer profile APIs")
@RestController
public class CustomerProfileApiController {

  @Autowired private ObjectMapper objectMapper;
  @Autowired private CustomerProfileService customerProfileService;

  @RequestMapping(value = "/customers/profile/{customerId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CustomerProfileResponse> getCustomerProfile(@PathVariable("customerId") String customerId,
      @RequestParam(name = "accessToken") String accessToken) {
    ApplicationLogger.logInfo("Entering CustomerProfile Controller");
    return customerProfileService.getCustomerProfileResponseEntity(customerId);
  }

  @RequestMapping(value = "/customers/profile/{customerId}/email", produces = {"application/json"}, method = RequestMethod.PUT)
  public ResponseEntity<CustomerProfileResponse> updateCustomerProfileEmail(@PathVariable("customerId") String customerId,
      @RequestBody final CustomerProfileRequest customerProfileRequest) {
    ApplicationLogger.logInfo("Entering CustomerProfile Controller");
    return customerProfileService.getCustomerProfileEmailResponseEntity(customerId, customerProfileRequest);
  }



  @RequestMapping(value = "/customers/profile/{customerId}/phoneNo", produces = {"application/json"}, method = RequestMethod.PUT)
  public ResponseEntity<CustomerProfileResponse> updateCustomerProfilePhone(@PathVariable("customerId") String customerId,
      @RequestBody final CustomerProfileRequest customerProfileRequest) {
    ApplicationLogger.logInfo("Entering CustomerProfile Controller");
    return customerProfileService.getCustomerProfilePhoneResponseEntity(customerId, customerProfileRequest);
  }

  @RequestMapping(value = "/customers/profile/{customerId}/address", produces = {"application/json"}, method = RequestMethod.PUT)
  public ResponseEntity<CustomerProfileResponse> updateCustomerProfileAddress(@PathVariable("customerId") String customerId,
      @RequestBody final CustomerProfileRequest customerProfileRequest) {
    ApplicationLogger.logInfo("Entering CustomerProfile Controller");
    return customerProfileService.getCustomerProfileAddressResponseEntity(customerId, customerProfileRequest);
  }
}