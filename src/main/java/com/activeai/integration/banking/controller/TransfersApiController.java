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
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"payeeDetails\" : [\n"
              + "\t\t{\n" + "\t\t\t\"payeeId\": \"91301y053851317\",\n" + "\t\t\t\"payeeName\": \"Ajay\",\n"
              + "\t\t\t\"payeeNickName\": \"Ajay\",\n" + "\t\t\t\"payeeType\": \"EXTERNAL\",\n"
              + "\t\t\t\"payeeAccountNo\": \"1234y6213\",\n" + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6213\",\n"
              + "\t\t\t\"payeeAccountId\": \"913010y53851317\",\n" + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n"
              + "\t\t\t\"payeeBank\":\"BankVic\",\n" + "\t\t\t\"ifscCode\" : \"BVIC0000004\",\n" + "\t\t\t\"payeeBankBranch\":\"String\",\n"
              + "\t\t\t\"payeeBankIFSC\":\"String\",\n" + "\t\t\t\"payeeBankSWIFT\":\"String\"\n" + "\t\t},\n" + "\t\t{\n"
              + "\t\t\t\"payeeId\": \"233010y53850098\",\n" + "\t\t\t\"payeeName\": \"Alia\",\n" + "\t\t\t\"payeeNickName\": \"Alia\",\n"
              + "\t\t\t\"payeeType\": \"EXTERNAL\",\n" + "\t\t\t\"payeeAccountNo\": \"1234y6214\",\n"
              + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6214\",\n" + "\t\t\t\"payeeAccountId\": \"233010y53850098\",\n"
              + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n" + "\t\t\t\"payeeBank\":\"BankViD\",\n"
              + "\t\t\t\"ifscCode\" : \"BVID0000003\",\n" + "\t\t\t\"payeeBankBranch\":\"String\",\n"
              + "\t\t\t\"payeeBankIFSC\":\"String\",\n" + "\t\t\t\"payeeBankSWIFT\":\"String\"\n" + "\t\t},\n" + "\t\t{\n"
              + "\t\t\t\"payeeId\": \"3021547y9663221\",\n" + "\t\t\t\"payeeName\": \"Ajit\",\n" + "\t\t\t\"payeeNickName\": \"Ajit\",\n"
              + "\t\t\t\"payeeType\": \"INTERNAL\",\n" + "\t\t\t\"payeeAccountNo\": \"123y56215\",\n"
              + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6215\",\n" + "\t\t\t\"payeeAccountId\": \"30215y789663221\",\n"
              + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n" + "\t\t\t\"payeeBank\":\"BankViD\",\n"
              + "\t\t\t\"payeeBankBranch\":\"String\",\n" + "\t\t\t\"payeeBankIFSC\":\"String\",\n"
              + "\t\t\t\"payeeBankSWIFT\":\"String\"\n" + "\t\t},\n" + "\t\t{\n" + "\t\t\t\"payeeId\": \"302154y89663121\",\n"
              + "\t\t\t\"payeeName\": \"Amit\",\n" + "\t\t\t\"payeeNickName\": \"Amit\",\n" + "\t\t\t\"payeeType\": \"INTERNAL\",\n"
              + "\t\t\t\"payeeAccountNo\": \"1234y6216\",\n" + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6216\",\n"
              + "\t\t\t\"payeeAccountId\": \"302154789y63121\",\n" + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n"
              + "\t\t\t\"payeeBank\":\"BankViD\",\n" + "\t\t\t\"payeeBankBranch\":\"String\",\n" + "\t\t\t\"payeeBankIFSC\":\"String\",\n"
              + "\t\t\t\"payeeBankSWIFT\":\"String\"\n" + "\t\t},\n" + "\t\t{\n" + "\t\t\t\"payeeId\": \"30215478y663122\",\n"
              + "\t\t\t\"payeeName\": \"Elan Kumaran\",\n" + "\t\t\t\"payeeNickName\": \"Elan Kumaran\",\n"
              + "\t\t\t\"payeeType\": \"INTERNAL\",\n" + "\t\t\t\"payeeAccountNo\": \"123y56217\",\n"
              + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6217\",\n" + "\t\t\t\"payeeAccountId\": \"30215478y663122\",\n"
              + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n" + "\t\t\t\"payeeBankBranch\":\"String\",\n"
              + "\t\t\t\"payeeBank\":\"BankViD\",\n" + "\t\t\t\"payeeBankIFSC\":\"String\",\n" + "\t\t\t\"payeeBankSWIFT\":\"String\"\n"
              + "\t\t},\n" + "\t\t{\n" + "\t\t\t\"payeeId\": \"30215478y663123\",\n" + "\t\t\t\"payeeName\": \"Elan Cheziyan\",\n"
              + "\t\t\t\"payeeNickName\": \"Elan Cheziyan\",\n" + "\t\t\t\"payeeType\": \"INTERNAL\",\n"
              + "\t\t\t\"payeeAccountNo\": \"1234y6218\",\n" + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6220\",\n"
              + "\t\t\t\"payeeAccountId\": \"302154789y63123\",\n" + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n"
              + "\t\t\t\"payeeBankBranch\":\"String\",\n" + "\t\t\t\"payeeBankIFSC\":\"String\",\n"
              + "\t\t\t\"payeeBankSWIFT\":\"String\",\n" + "\t\t\t\"payeeBank\":\"BankViD\"\n" + "\t\t}\n" + "\t]\n\n }",
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