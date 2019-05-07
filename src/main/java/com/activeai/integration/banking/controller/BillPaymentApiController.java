package com.activeai.integration.banking.controller;

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

@Api(value = "BillPayment Related APIs", description = "Shows API Documentation Regards Billpayment APIs")
@RestController
public class BillPaymentApiController {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private BillPaymentService billpaymentService;

  @RequestMapping(value = "/billers/categories", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<BillerCategoriesResponse> getBillerCategoriesList() {
    ResponseEntity<BillerCategoriesResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"billerCategories\" : [ {    \"categoryName\" : \"categoryName\",    \"categoryId\" : \"categoryId\"  }, {    \"categoryName\" : \"categoryName\",    \"categoryId\" : \"categoryId\"  } ]}",
          BillerCategoriesResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json", e);
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
      ApplicationLogger.logError("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @ApiOperation(value = "Returns list of Registered Billers based on customerapplication.properties ID")
  @RequestMapping(value = "/{customerId}/billers", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<BillerResponse> getRegisteredBillerListForCustomer(@RequestParam(value = "categoryId", required = false) Integer categoryId,@PathVariable(name = "customerId", required = true) String customerId) {
    ApplicationLogger.logInfo("Entering getRegisteredBillers API");
    return billpaymentService.getRegisteredBillerResponseEntity(customerId);
  }
}
