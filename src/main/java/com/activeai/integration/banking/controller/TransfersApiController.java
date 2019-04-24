package com.activeai.integration.banking.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.validation.Valid;

import com.activeai.integration.banking.model.request.FundTransferRequest;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.activeai.integration.banking.model.PayeesResponse;
import com.activeai.integration.banking.model.TransferConfirmResponse;
import com.activeai.integration.banking.model.TransferInititateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Api(value = "Fund Transfer Related APIs", description = "Shows API Documentation Regards Fund Transfer APIs")
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
              + "\t\t\t\"payeeNickName\": \"Ajay\",\n" + "\t\t\t\"payeeType\": \"EXTERNAL_DOMESTIC\",\n"
              + "\t\t\t\"payeeAccountNo\": \"1234y6213\",\n" + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6213\",\n"
              + "\t\t\t\"payeeAccountId\": \"913010y53851317\",\n" + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n"
              + "\t\t\t\"payeeBank\":\"BankVic\",\n" + "\t\t\t\"ifscCode\" : \"BVIC0000004\",\n" + "\t\t\t\"payeeBankBranch\":\"String\",\n"
              + "\t\t\t\"payeeBankIFSC\":\"String\",\n" + "\t\t\t\"payeeBankSWIFT\":\"String\"\n" + "\t\t},\n" + "\t\t{\n"
              + "\t\t\t\"payeeId\": \"233010y53850098\",\n" + "\t\t\t\"payeeName\": \"Alia\",\n" + "\t\t\t\"payeeNickName\": \"Alia\",\n"
              + "\t\t\t\"payeeType\": \"EXTERNAL_DOMESTIC\",\n" + "\t\t\t\"payeeAccountNo\": \"1234y6214\",\n"
              + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6214\",\n" + "\t\t\t\"payeeAccountId\": \"233010y53850098\",\n"
              + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n" + "\t\t\t\"payeeBank\":\"BankViD\",\n"
              + "\t\t\t\"ifscCode\" : \"BVID0000003\",\n" + "\t\t\t\"payeeBankBranch\":\"String\",\n"
              + "\t\t\t\"payeeBankIFSC\":\"String\",\n" + "\t\t\t\"payeeBankSWIFT\":\"String\"\n" + "\t\t},\n" + "\t\t{\n"
              + "\t\t\t\"payeeId\": \"3021547y9663221\",\n" + "\t\t\t\"payeeName\": \"Ajit\",\n" + "\t\t\t\"payeeNickName\": \"Ajit\",\n"
              + "\t\t\t\"payeeType\": \"INTERNAL_DOMESTIC\",\n" + "\t\t\t\"payeeAccountNo\": \"123y56215\",\n"
              + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6215\",\n" + "\t\t\t\"payeeAccountId\": \"30215y789663221\",\n"
              + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n" + "\t\t\t\"payeeBank\":\"BankViD\",\n"
              + "\t\t\t\"payeeBankBranch\":\"String\",\n" + "\t\t\t\"payeeBankIFSC\":\"String\",\n"
              + "\t\t\t\"payeeBankSWIFT\":\"String\"\n" + "\t\t},\n" + "\t\t{\n" + "\t\t\t\"payeeId\": \"302154y89663121\",\n"
              + "\t\t\t\"payeeName\": \"Amit\",\n" + "\t\t\t\"payeeNickName\": \"Amit\",\n" + "\t\t\t\"payeeType\": \"INTERNAL_DOMESTIC\",\n"
              + "\t\t\t\"payeeAccountNo\": \"1234y6216\",\n" + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6216\",\n"
              + "\t\t\t\"payeeAccountId\": \"302154789y63121\",\n" + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n"
              + "\t\t\t\"payeeBank\":\"BankViD\",\n" + "\t\t\t\"payeeBankBranch\":\"String\",\n" + "\t\t\t\"payeeBankIFSC\":\"String\",\n"
              + "\t\t\t\"payeeBankSWIFT\":\"String\"\n" + "\t\t},\n" + "\t\t{\n" + "\t\t\t\"payeeId\": \"30215478y663122\",\n"
              + "\t\t\t\"payeeName\": \"Elan Kumaran\",\n" + "\t\t\t\"payeeNickName\": \"Elan Kumaran\",\n"
              + "\t\t\t\"payeeType\": \"INTERNAL_DOMESTIC\",\n" + "\t\t\t\"payeeAccountNo\": \"123y56217\",\n"
              + "\t\t\t\"payeeMaskedAccountNo\" : \"12xxx6217\",\n" + "\t\t\t\"payeeAccountId\": \"30215478y663122\",\n"
              + "\t\t\t\"currency\": \"USD\",\n" + "\t\t\t\"status\": \"ACTIVE\",\n" + "\t\t\t\"payeeBankBranch\":\"String\",\n"
              + "\t\t\t\"payeeBank\":\"BankViD\",\n" + "\t\t\t\"payeeBankIFSC\":\"String\",\n" + "\t\t\t\"payeeBankSWIFT\":\"String\"\n"
              + "\t\t},\n" + "\t\t{\n" + "\t\t\t\"payeeId\": \"30215478y663123\",\n" + "\t\t\t\"payeeName\": \"Elan Cheziyan\",\n"
              + "\t\t\t\"payeeNickName\": \"Elan Cheziyan\",\n" + "\t\t\t\"payeeType\": \"INTERNAL_DOMESTIC\",\n"
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

  @RequestMapping(value = "/{customerId}/transfer/initiate", produces = {"application/json"}, consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<TransferInititateResponse> initTransfer(@PathVariable(value = "customerId", required = true) Integer customerId,
      @RequestBody final FundTransferRequest fundTransferRequest){
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

  @RequestMapping(value = "/{customerId}/transfer/confirm", produces = {"application/json"}, consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<TransferConfirmResponse> confirmTransfer(@PathVariable(value = "customerId", required = true) Integer customerId,
      @RequestBody final FundTransferRequest fundTransferRequest) {
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
