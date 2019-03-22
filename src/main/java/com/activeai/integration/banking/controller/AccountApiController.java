package com.activeai.integration.banking.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activeai.integration.banking.model.AccountBalanceResponse;
import com.activeai.integration.banking.model.AccountDetailResponse;
import com.activeai.integration.banking.model.AccountTransactionsResponse;
import com.activeai.integration.banking.model.AccountsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AccountApiController {

  private static final Logger logger = LoggerFactory.getLogger(AccountApiController.class);

  @Autowired
  private ObjectMapper objectMapper;

  @RequestMapping(value = "/{customerId}/accounts/{accountId}/balance", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountBalanceResponse> getAccountBalance(@PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "accountId", required = true) Integer accountId) {
    try {
      return new ResponseEntity<AccountBalanceResponse>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"accountBalance\" : {    \"amount\" : 6.027456183070403,    \"currentBalance\" : 5.962133916683182,    \"currencyCode\" : \"currencyCode\",    \"availableBalance\" : 1.4658129805029452  }}",
          AccountBalanceResponse.class), HttpStatus.NOT_IMPLEMENTED);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      return new ResponseEntity<AccountBalanceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/{customerId}/accounts/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountDetailResponse> getAccountDetail(@PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "accountId", required = true) Integer accountId) {
    try {
      return new ResponseEntity<AccountDetailResponse>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"accountDetail\" : {    \"branchId\" : \"branchId\",    \"product\" : \"product\",    \"branchName\" : \"branchName\",    \"type\" : \"SAVINGS\",    \"accountNumber\" : \"accountNumber\",    \"accountId\" : \"accountId\",    \"lastStatementDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"productCode\" : \"productCode\",    \"lastStatementBalance\" : 0.8008281904610115,    \"balance\" : {      \"amount\" : 6.027456183070403,      \"currentBalance\" : 5.962133916683182,      \"currencyCode\" : \"currencyCode\",      \"availableBalance\" : 1.4658129805029452    },    \"displayAccountNumber\" : \"123xxxx890\",    \"category\" : \"category\",    \"openingDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"status\" : \"ACTIVE\"  }}",
          AccountDetailResponse.class), HttpStatus.NOT_IMPLEMENTED);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      return new ResponseEntity<AccountDetailResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @RequestMapping(value = "/{customerId}/accounts/{accountId}/statement", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountBalanceResponse> getAccountStatement(@PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "accountId", required = true) Integer accountId) {
    try {
      return new ResponseEntity<AccountBalanceResponse>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"accountBalance\" : {    \"amount\" : 6.027456183070403,    \"currentBalance\" : 5.962133916683182,    \"currencyCode\" : \"currencyCode\",    \"availableBalance\" : 1.4658129805029452  }}",
          AccountBalanceResponse.class), HttpStatus.NOT_IMPLEMENTED);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      return new ResponseEntity<AccountBalanceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/{customerId}/accounts/{accountId}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountTransactionsResponse> getAccountTransactions(
      @PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "accountId", required = true) Integer accountId,
      @NotNull @Valid @DateTimeFormat(iso = ISO.DATE) @RequestParam(value = "fromDate", required = true) LocalDate fromDate,
      @NotNull @Valid @DateTimeFormat(iso = ISO.DATE) @RequestParam(value = "toDate", required = true) LocalDate toDate,
      @NotNull @Valid @RequestParam(value = "pageSize", required = true) Integer pageSize) {
    try {
      return new ResponseEntity<AccountTransactionsResponse>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"accountTransactions\" : {    \"foreignTxnCurrency\" : \"foreignTxnCurrency\",    \"amount\" : 6.027456183070403,    \"description\" : \"description\",    \"currency\" : \"currency\",    \"foreignTxnAmount\" : 1.4658129805029452,    \"isDebit\" : true,    \"category\" : \"category\",    \"foreignTxnExchangeRate\" : 5.962134,    \"txnDate\" : \"2019-03-20\",    \"referenceId\" : \"referenceId\"  }}",
          AccountTransactionsResponse.class), HttpStatus.NOT_IMPLEMENTED);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      return new ResponseEntity<AccountTransactionsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(value = "/{customerId}/accounts", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountsResponse> getAccounts(@PathVariable(name = "customerId", required = true) Integer customerId) {
    try {
      return new ResponseEntity<AccountsResponse>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"accounts\" : []}",
          AccountsResponse.class), HttpStatus.NOT_IMPLEMENTED);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      return new ResponseEntity<AccountsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
