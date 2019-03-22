package com.activeai.integration.banking.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activeai.integration.banking.model.CustomerProfileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CustomerProfileApiController {

  private static final Logger logger = LoggerFactory.getLogger(CustomerProfileApiController.class);

  @Autowired
  private ObjectMapper objectMapper;

  @RequestMapping(value = "/customers/profile/{custId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CustomerProfileResponse> getCustomerProfile(@PathVariable("custId") String custId) {
    try {
      return new ResponseEntity<CustomerProfileResponse>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"customerProfile\" : {    \"firstName\" : \"firstName\",    \"lastName\" : \"lastName\",    \"address\" : {      \"country\" : \"country\",      \"province\" : \"province\",      \"city\" : \"city\",      \"addressType\" : \"PRIMARY\",      \"countryCode\" : \"countryCode\",      \"addressLine1\" : \"addressLine1\",      \"addressLine2\" : \"addressLine2\",      \"addressLine3\" : \"addressLine3\",      \"state\" : \"state\"    },    \"custSegment\" : \"\",    \"phone\" : [ {      \"phoneType\" : \"PRIMARY\",      \"phoneCountryCode\" : \"+65\",      \"phoneNo\" : 9876543    }, {      \"phoneType\" : \"PRIMARY\",      \"phoneCountryCode\" : \"+65\",      \"phoneNo\" : 9876543    } ],    \"custId\" : 80975412,    \"middleName\" : \"middleName\",    \"title\" : \"Mr.\"  }}",
          CustomerProfileResponse.class), HttpStatus.NOT_IMPLEMENTED);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      return new ResponseEntity<CustomerProfileResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
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
            CustomerProfileResponse.class), HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        logger.error("Couldn't serialize response for content type application/json", e);
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
            CustomerProfileResponse.class), HttpStatus.NOT_IMPLEMENTED);
      } catch (IOException e) {
        logger.error("Couldn't serialize response for content type application/json", e);
        return new ResponseEntity<CustomerProfileResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    return new ResponseEntity<CustomerProfileResponse>(HttpStatus.NOT_IMPLEMENTED);
  }

}
