package com.activeai.integration.banking.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.validation.Valid;

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

import com.activeai.integration.banking.model.PayeesResponse;
import com.activeai.integration.banking.model.TransferConfirmResponse;
import com.activeai.integration.banking.model.TransferInititateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TransfersApiController {

  private static final Logger logger = LoggerFactory.getLogger(TransfersApiController.class);

  @Autowired
  private ObjectMapper objectMapper;

  @RequestMapping(value = "/{customerId}/transfer/payees", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<PayeesResponse> getPayeesList(@PathVariable(value = "customerId", required = true) Integer customerId,
      @Valid @RequestParam(value = "payeeType", required = false) String payeeType) {
    ResponseEntity<PayeesResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : \"\"}",
          PayeesResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/{customerId}/transfer/initiate", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.POST)
  public ResponseEntity<TransferInititateResponse> initTransfer(@PathVariable(value = "customerId", required = true) Integer customerId,
      @RequestParam(value = "fromAccountId", required = true) Integer fromAccountId,
      @RequestParam(value = "toAccountId", required = true) Integer toAccountId,
      @RequestParam(value = "amount", required = true) BigDecimal amount,
      @RequestParam(value = "currency", required = true) String currency,
      @RequestParam(value = "remarks", required = true) String remarks) {
    ResponseEntity<TransferInititateResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"txnReferenceId\" : \"txnReferenceId\",  \"status\" : \"INIT\"}",
          TransferInititateResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/{customerId}/transfer/confirm", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.POST)
  public ResponseEntity<TransferConfirmResponse> confirmTransfer(@PathVariable(value = "customerId", required = true) Integer customerId,
      @RequestParam(value = "fromAccountId", required = true) Integer fromAccountId,
      @RequestParam(value = "toAccountId", required = true) Integer toAccountId,
      @RequestParam(value = "amount", required = true) BigDecimal amount,
      @RequestParam(value = "currency", required = true) String currency, @RequestParam(value = "remarks", required = true) String remarks,
      @RequestParam(value = "txnReferenceId", required = true) String txnReferenceId) {
    ResponseEntity<TransferConfirmResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"txnReferenceId\" : \"txnReferenceId\",  \"status\" : \"DONE\"}",
          TransferConfirmResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

}
