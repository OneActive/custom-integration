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
    logger.info("Entering getAccountBalance");
    String balance = null;
    ResponseEntity<AccountBalanceResponse> response = null;
    try {
      balance =
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"accountBalance\" : {    \"amount\" : 65001.32,    \"currentBalance\" : 58001.32,    \"currencyCode\" : \"USD\",    \"availableBalance\" : 7000  }}";
      response = new ResponseEntity<>(objectMapper.readValue(balance, AccountBalanceResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    logger.info("Response : " + response);
    return response;
  }

  @RequestMapping(value = "/{customerId}/accounts/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountDetailResponse> getAccountDetail(@PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "accountId", required = true) Integer accountId) {
    logger.info("Entering getAccountDetail");
    String accountDetail = null;
    ResponseEntity<AccountDetailResponse> response = null;
    try {
      accountDetail =
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"accountDetail\" : {    \"branchId\" : \"branchId\",    \"product\" : \"product\",    \"branchName\" : \"branchName\",    \"type\" : \"SAVINGS\",    \"accountNumber\" : \"accountNumber\",    \"accountId\" : \"accountId\",    \"lastStatementDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"productCode\" : \"productCode\",    \"lastStatementBalance\" : 0.8008281904610115,    \"balance\" : {      \"amount\" : 6.027456183070403,      \"currentBalance\" : 5.962133916683182,      \"currencyCode\" : \"currencyCode\",      \"availableBalance\" : 1.4658129805029452    },    \"displayAccountNumber\" : \"123xxxx890\",    \"category\" : \"category\",    \"openingDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"status\" : \"ACTIVE\"  }}";
      response = new ResponseEntity<>(objectMapper.readValue(accountDetail, AccountDetailResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    logger.info("Response : " + response);
    return response;
  }


  @RequestMapping(value = "/{customerId}/accounts/{accountId}/statement", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountTransactionsResponse> getAccountStatement(
      @PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "accountId", required = true) Integer accountId) {
    logger.info("Entering getAccountStatement");
    String accountStatement = null;
    ResponseEntity<AccountTransactionsResponse> response = null;
    try {
      accountStatement =
          "{  \n" + "   \"result\":{  \n" + "      \"messageCode\":\"messageCode\",\n" + "      \"message\":\"message\",\n"
              + "      \"status\":0\n" + "   },\n" + "   \"accountTransactions\":[  \n" + "      {  \n"
              + "         \"foreignTxnCurrency\":\"foreignTxnCurrency\",\n" + "         \"amount\":6.027456183070403,\n"
              + "         \"description\":\"carnito payment for food\",\n" + "         \"currency\":\"USD\",\n"
              + "         \"foreignTxnAmount\":1.4658129805029452,\n" + "         \"isDebit\":true,\n"
              + "         \"category\":\"category\",\n" + "         \"foreignTxnExchangeRate\":5.962134,\n"
              + "         \"txnDate\":\"2019-01-23\",\n" + "         \"referenceId\":\"referenceId\"\n" + "      },\n" + "      {  \n"
              + "         \"foreignTxnCurrency\":\"foreignTxnCurrency\",\n" + "         \"amount\":16.027456183070403,\n"
              + "         \"description\":\"electricity bill Payment\",\n" + "         \"currency\":\"USD\",\n"
              + "         \"foreignTxnAmount\":3.4658129805029452,\n" + "         \"isDebit\":true,\n"
              + "         \"category\":\"category\",\n" + "         \"foreignTxnExchangeRate\":5.962134,\n"
              + "         \"txnDate\":\"2019-01-20\",\n" + "         \"referenceId\":\"referenceId\"\n" + "      },\n" + "      {  \n"
              + "         \"foreignTxnCurrency\":\"foreignTxnCurrency\",\n" + "         \"amount\":61.027456183070403,\n"
              + "         \"description\":\"mobile purchase\",\n" + "         \"currency\":\"USD\",\n"
              + "         \"foreignTxnAmount\":2.4658129805029452,\n" + "         \"isDebit\":true,\n"
              + "         \"category\":\"category\",\n" + "         \"foreignTxnExchangeRate\":5.962134,\n"
              + "         \"txnDate\":\"2019-02-20\",\n" + "         \"referenceId\":\"referenceId\"\n" + "      },\n" + "      {  \n"
              + "         \"foreignTxnCurrency\":\"foreignTxnCurrency\",\n" + "         \"amount\":23.027456183070403,\n"
              + "         \"description\":\"water bill payment\",\n" + "         \"currency\":\"USD\",\n"
              + "         \"foreignTxnAmount\":13.4658129805029452,\n" + "         \"isDebit\":true,\n"
              + "         \"category\":\"category\",\n" + "         \"foreignTxnExchangeRate\":5.962134,\n"
              + "         \"txnDate\":\"2019-04-20\",\n" + "         \"referenceId\":\"referenceId\"\n" + "      }\n" + "\n" + "   ]\n"
              + "}";
      response = new ResponseEntity<>(objectMapper.readValue(accountStatement, AccountTransactionsResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    logger.info("Statement Response : " + response);
    return response;
  }

  @RequestMapping(value = "/{customerId}/accounts/{accountId}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountTransactionsResponse> getAccountTransactions(
      @PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "accountId", required = true) Integer accountId) {
    logger.info("Entering getAccountTransactions");
    String transactions = null;
    ResponseEntity<AccountTransactionsResponse> response = null;
    try {
      transactions =
          "{  \n" + "   \"result\":{  \n" + "      \"messageCode\":\"messageCode\",\n" + "      \"message\":\"message\",\n"
              + "      \"status\":0\n" + "   },\n" + "   \"accountTransactions\":[  \n" + "      {  \n"
              + "         \"foreignTxnCurrency\":\"foreignTxnCurrency\",\n" + "         \"amount\":6.027456183070403,\n"
              + "         \"description\":\"carnito payment for food\",\n" + "         \"currency\":\"USD\",\n"
              + "         \"foreignTxnAmount\":1.4658129805029452,\n" + "         \"isDebit\":true,\n"
              + "         \"category\":\"category\",\n" + "         \"foreignTxnExchangeRate\":5.962134,\n"
              + "         \"txnDate\":\"2019-01-23\",\n" + "         \"referenceId\":\"referenceId\"\n" + "      },\n" + "      {  \n"
              + "         \"foreignTxnCurrency\":\"foreignTxnCurrency\",\n" + "         \"amount\":16.027456183070403,\n"
              + "         \"description\":\"electricity bill payment\",\n" + "         \"currency\":\"USD\",\n"
              + "         \"foreignTxnAmount\":3.4658129805029452,\n" + "         \"isDebit\":true,\n"
              + "         \"category\":\"category\",\n" + "         \"foreignTxnExchangeRate\":5.962134,\n"
              + "         \"txnDate\":\"2019-01-20\",\n" + "         \"referenceId\":\"referenceId\"\n" + "      },\n" + "      {  \n"
              + "         \"foreignTxnCurrency\":\"foreignTxnCurrency\",\n" + "         \"amount\":61.027456183070403,\n"
              + "         \"description\":\"shoping in amazone\",\n" + "         \"currency\":\"USD\",\n"
              + "         \"foreignTxnAmount\":2.4658129805029452,\n" + "         \"isDebit\":true,\n"
              + "         \"category\":\"category\",\n" + "         \"foreignTxnExchangeRate\":5.962134,\n"
              + "         \"txnDate\":\"2019-02-20\",\n" + "         \"referenceId\":\"referenceId\"\n" + "      },\n" + "      {  \n"
              + "         \"foreignTxnCurrency\":\"foreignTxnCurrency\",\n" + "         \"amount\":23.027456183070403,\n"
              + "         \"description\":\"water bill payment\",\n" + "         \"currency\":\"USD\",\n"
              + "         \"foreignTxnAmount\":13.4658129805029452,\n" + "         \"isDebit\":true,\n"
              + "         \"category\":\"category\",\n" + "         \"foreignTxnExchangeRate\":5.962134,\n"
              + "         \"txnDate\":\"2019-04-20\",\n" + "         \"referenceId\":\"referenceId\"\n" + "      }\n" + "\n" + "   ]\n"
              + "}";
      response = new ResponseEntity<>(objectMapper.readValue(transactions, AccountTransactionsResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    logger.info("Cards Response : " + response);
    return response;
  }

  @RequestMapping(value = "/{customerId}/accounts", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountsResponse> getAccounts(@PathVariable(name = "customerId", required = true) Integer customerId) {
    logger.info("Entering getAccounts");
    String accounts = null;
    ResponseEntity<AccountsResponse> response = null;
    try {
      accounts ="{\n" + "  \"result\": {\n" + "    \"status\": 0,\n" + "    \"message\": \"string\",\n"
          + "    \"messageCode\": \"string\"\n" + "  },\n" + "  \"accounts\": [\n" + "    {\n" + "      \"category\": \"ACCOUNT\",\n"
          + "      \"productCode\": \"string\",\n" + "      \"bankName\": \"Wells Fargo\",\n"
          + "      \"accountName\": \"Regular Savings\",\n" + "      \"product\": \"string\",\n" + "      \"type\": \"SAVINGS\",\n"
          + "      \"displayAccountNumber\": \"123xxxx4567\",\n" + "      \"accountNumber\": \"1234567\",\n"
          + "      \"accountId\": \"1234567\",\n" + "      \"branchId\": \"WF001\",\n" + "      \"branchName\": \"Arlington\",\n"
          + "      \"status\": \"ACTIVE\",\n" + "      \"openingDate\": \"2019-03-22\",\n"
          + "      \"lastStatementDate\": \"2019-03-22\",\n" + "      \"lastStatementBalance\": 34879,\n" + "      \"balance\": {\n"
          + "        \"amount\": 90000,\n" + "        \"availableBalance\": 75675,\n" + "        \"currentBalance\": 20000,\n"
          + "        \"currencyCode\": \"USD\"\n" + "      },\n" + "      \"branchAddress\": \"Arlington, USA \"\n" + "    },\n" + "    {\n"
          + "      \"category\": \"ACCOUNT\",\n" + "      \"productCode\": \"string\",\n" + "      \"bankName\": \"US Bancorp\",\n"
          + "      \"accountName\": \"Easy Access\",\n" + "      \"product\": \"string\",\n" + "      \"type\": \"SAVINGS\",\n"
          + "      \"displayAccountNumber\": \"123xxxx4568\",\n" + "      \"accountNumber\": \"1234568\",\n"
          + "      \"accountId\": \"1234568\",\n" + "      \"branchId\": \"USB001\",\n" + "      \"branchName\": \"Marceline\",\n"
          + "      \"status\": \"ACTIVE\",\n" + "      \"openingDate\": \"2019-03-23\",\n"
          + "      \"lastStatementDate\": \"2019-03-23\",\n" + "      \"lastStatementBalance\": 6543,\n" + "      \"balance\": {\n"
          + "        \"amount\": 654323,\n" + "        \"availableBalance\": 23456,\n" + "        \"currentBalance\": 30000,\n"
          + "        \"currencyCode\": \"USD\"\n" + "      },\n" + "      \"branchAddress\": \"Marceline, USA \"\n" + "    },\n" + "    {\n"
          + "      \"category\": \"ACCOUNT\",\n" + "      \"productCode\": \"string\",\n" + "      \"product\": \"string\",\n"
          + "      \"bankName\": \"JPMorgan Chase\",\n" + "      \"accountName\": \"SavingsMax\",\n" + "      \"type\": \"SAVINGS\",\n"
          + "      \"displayAccountNumber\": \"123xxxx4569\",\n" + "      \"accountNumber\": \"1234569\",\n"
          + "      \"accountId\": \"1234569\",\n" + "      \"branchId\": \"JPMC001\",\n" + "      \"branchName\": \"Manhattan\",\n"
          + "      \"status\": \"ACTIVE\",\n" + "      \"openingDate\": \"2019-03-24\",\n"
          + "      \"lastStatementDate\": \"2019-03-24\",\n" + "      \"lastStatementBalance\": 3456,\n" + "      \"balance\": {\n"
          + "        \"amount\": 234567,\n" + "        \"availableBalance\": 65432,\n" + "        \"currentBalance\": 40000,\n"
          + "        \"currencyCode\": \"USD\"\n" + "      },\n" + "      \"branchAddress\": \"Manhattan, USA \"\n" + "    },\n" + "    {\n"
          + "      \"category\": \"ACCOUNT\",\n" + "      \"productCode\": \"string\",\n" + "      \"bankName\": \"US Bancorp\",\n"
          + "      \"accountName\": \"Easy Access\",\n" + "      \"product\": \"string\",\n" + "      \"type\": \"SAVINGS\",\n"
          + "      \"displayAccountNumber\": \"123xxxx4570\",\n" + "      \"accountNumber\": \"1234570\",\n"
          + "      \"accountId\": \"1234570\",\n" + "      \"branchId\": \"USB002\",\n" + "      \"branchName\": \"Marceline\",\n"
          + "      \"status\": \"ACTIVE\",\n" + "      \"openingDate\": \"2019-03-23\",\n"
          + "      \"lastStatementDate\": \"2019-03-23\",\n" + "      \"lastStatementBalance\": 234556,\n" + "      \"balance\": {\n"
          + "        \"amount\": 987654,\n" + "        \"availableBalance\": 786043,\n" + "        \"currentBalance\": 985654,\n"
          + "        \"currencyCode\": \"USD\"\n" + "      },\n" + "      \"branchAddress\": \"Marceline, USA \"\n" + "    },\n" + "    {\n"
          + "      \"category\": \"ACCOUNT\",\n" + "      \"productCode\": \"string\",\n" + "      \"product\": \"string\",\n"
          + "      \"bankName\": \"JPMorgan Chase\",\n" + "      \"accountName\": \"SavingsMax\",\n" + "      \"type\": \"SAVINGS\",\n"
          + "      \"displayAccountNumber\": \"123xxxx4671\",\n" + "      \"accountNumber\": \"1234671\",\n"
          + "      \"accountId\": \"1234671\",\n" + "      \"branchId\": \"JPMC002\",\n" + "      \"branchName\": \"Manhattan\",\n"
          + "      \"status\": \"ACTIVE\",\n" + "      \"openingDate\": \"2019-03-24\",\n"
          + "      \"lastStatementDate\": \"2019-03-24\",\n" + "      \"lastStatementBalance\": 3456,\n" + "      \"balance\": {\n"
          + "        \"amount\": 2342567,\n" + "        \"availableBalance\": 328574,\n" + "        \"currentBalance\": 2142567,\n"
          + "        \"currencyCode\": \"USD\"\n" + "      },\n" + "      \"branchAddress\": \"Manhattan, USA \"\n" + "    }\n" + "  ]\n"
          + "}";
      response = new ResponseEntity<>(objectMapper.readValue(accounts, AccountsResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    logger.info("Accounts Response : " + response);
    return response;
  }

}
