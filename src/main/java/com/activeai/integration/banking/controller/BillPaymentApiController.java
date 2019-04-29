package com.activeai.integration.banking.controller;

import java.io.IOException;

import javax.validation.Valid;

import io.swagger.annotations.Api;
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

import com.activeai.integration.banking.domain.response.BillerCategoriesResponse;
import com.activeai.integration.banking.model.BillerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Api(value = "BillPayment Related APIs", description = "Shows API Documentation Regards Billpayment APIs")
@RestController
public class BillPaymentApiController {

  private static final Logger logger = LoggerFactory.getLogger(BillPaymentApiController.class);

  @Autowired
  private ObjectMapper objectMapper;

  @RequestMapping(value = "/billers/categories", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<BillerCategoriesResponse> getBillerCategoriesList() {
    ResponseEntity<BillerCategoriesResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"billerCategories\" : [ {    \"categoryName\" : \"categoryName\",    \"categoryId\" : \"categoryId\"  }, {    \"categoryName\" : \"categoryName\",    \"categoryId\" : \"categoryId\"  } ]}",
          BillerCategoriesResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/billers", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<BillerResponse> getBillerList(@Valid @RequestParam(value = "categoryId", required = false) Integer categoryId) {
    ResponseEntity<BillerResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"billers\" : [ {    \"billerCategory\" : \"billerCategory\",    \"billerId\" : \"billerId\",    \"allowPartialPay\" : true,    \"billerName\" : \"billerName\"  }, {    \"billerCategory\" : \"billerCategory\",    \"billerId\" : \"billerId\",    \"allowPartialPay\" : true,    \"billerName\" : \"billerName\"  } ]}",
          BillerResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/{customerId}/billers", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<BillerResponse> getBillerListForCustomer(@RequestParam(value = "categoryId", required = false) Integer categoryId,
      @PathVariable(value = "customerId", required = true) String customerId) {
    ResponseEntity<BillerResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"billers\" : [ {    \"billerCategory\" : \"billerCategory\",    \"billerId\" : \"billerId\",    \"allowPartialPay\" : true,    \"billerName\" : \"billerName\"  }, {    \"billerCategory\" : \"billerCategory\",    \"billerId\" : \"billerId\",    \"allowPartialPay\" : true,    \"billerName\" : \"billerName\"  } ]}",
          BillerResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

}
