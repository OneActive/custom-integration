package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.response.CustomerProfileResponse;
import com.activeai.integration.banking.services.CustomerProfileService;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(value = "Customer Profile Related APIs", description = "Shows API Documentation Regards Customer Profile APIs")
@RestController
public class CustomerProfileApiController {

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private CustomerProfileService customerProfileService;

  @RequestMapping(value = "/customers/profile/{customerId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CustomerProfileResponse> getCustomerProfile(@PathVariable("customerId") String customerId) {
    ApplicationLogger.logInfo("Entering CustomerProfile Controller");
    return customerProfileService.getCustomerProfileResponseEntity(customerId);
  }

  @RequestMapping(value = "/customers/profile/{custId}/email", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.PUT)
  public ResponseEntity<CustomerProfileResponse> updateCustomerProfileEmail(@PathVariable("custId") String custId,
      @RequestParam(value = "email", required = false) String email, HttpServletRequest request) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<CustomerProfileResponse>(objectMapper.readValue(
            "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"customerProfile\" : {    \"firstName\" : \"firstName\",    \"lastName\" : \"lastName\",    \"address\" : {      \"country\" : \"country\",      \"province\" : \"province\",      \"city\" : \"city\",      \"addressType\" : \"PRIMARY\",      \"countryCode\" : \"countryCode\",      \"addressLine1\" : \"addressLine1\",      \"addressLine2\" : \"addressLine2\",      \"addressLine3\" : \"addressLine3\",      \"state\" : \"state\"    },    \"custSegment\" : \"\",    \"phone\" : [ {      \"phoneType\" : \"PRIMARY\",      \"phoneCountryCode\" : \"+65\",      \"phoneNo\" : 9876543    }, {      \"phoneType\" : \"PRIMARY\",      \"phoneCountryCode\" : \"+65\",      \"phoneNo\" : 9876543    } ],    \"custId\" : 80975412,    \"middleName\" : \"middleName\",    \"title\" : \"Mr.\"  }}",
            CustomerProfileResponse.class), HttpStatus.OK);
      } catch (IOException e) {
        ApplicationLogger.logError("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<CustomerProfileResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    return new ResponseEntity<CustomerProfileResponse>(HttpStatus.NOT_IMPLEMENTED);
  }

  @RequestMapping(value = "/customers/profile/{custId}/phoneNo", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.PUT)
  public ResponseEntity<CustomerProfileResponse> updateCustomerProfilePhone(@PathVariable("custId") String custId,
      @RequestParam(value = "phoneNo", required = false) Integer phoneNo,
      @RequestParam(value = "phoneCountryCode", required = false) String phoneCountryCode,
      @RequestParam(value = "phoneType", required = false) String phoneType, HttpServletRequest request) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
      try {
        return new ResponseEntity<CustomerProfileResponse>(objectMapper.readValue(
            "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"customerProfile\" : {    \"firstName\" : \"firstName\",    \"lastName\" : \"lastName\",    \"address\" : {      \"country\" : \"country\",      \"province\" : \"province\",      \"city\" : \"city\",      \"addressType\" : \"PRIMARY\",      \"countryCode\" : \"countryCode\",      \"addressLine1\" : \"addressLine1\",      \"addressLine2\" : \"addressLine2\",      \"addressLine3\" : \"addressLine3\",      \"state\" : \"state\"    },    \"custSegment\" : \"\",    \"phone\" : [ {      \"phoneType\" : \"PRIMARY\",      \"phoneCountryCode\" : \"+65\",      \"phoneNo\" : 9876543210    }, {      \"phoneType\" : \"PRIMARY\",      \"phoneCountryCode\" : \"+65\",      \"phoneNo\" : 9876543210    } ],    \"custId\" : 80975412,    \"middleName\" : \"middleName\",    \"title\" : \"Mr.\"  }}",
            CustomerProfileResponse.class), HttpStatus.OK);
      } catch (IOException e) {
        ApplicationLogger.logError("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<CustomerProfileResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<CustomerProfileResponse>(HttpStatus.NOT_IMPLEMENTED);
  }

}
