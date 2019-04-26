package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.model.AccountBalanceResponse;
import com.activeai.integration.banking.model.AccountDetailResponse;
import com.activeai.integration.banking.model.AccountTransactionsResponse;
import com.activeai.integration.banking.model.AccountsResponse;
import com.activeai.integration.banking.services.AccountsService;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@Api(value = "Accounts Related APIs", description = "Shows API Documentation Regards Accounts APIs")
@RestController
public class AccountApiController {

  @Autowired
  private AccountsService accountsService;

  @Autowired
  private ObjectMapper objectMapper;

  @ApiOperation(value = "Returns selected account balance")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}/balance", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountBalanceResponse> getAccountBalance(@PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "accountId", required = true) Integer accountId) {
    ApplicationLogger.logInfo("Entering getAccountBalance");
    String balance = null;
    ResponseEntity<AccountBalanceResponse> response = null;
    try {
      balance =
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"accountBalance\" : {    \"amount\" : 65001.32,    \"currentBalance\" : 58001.32,    \"currencyCode\" : \"USD\",    \"availableBalance\" : 7000  }}";
      response = new ResponseEntity<>(objectMapper.readValue(balance, AccountBalanceResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    ApplicationLogger.logInfo("Response : " + response);
    return response;
  }

  @ApiOperation(value = "Returns selected account details")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountDetailResponse> getAccountDetail(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) Integer accountId) {
    ApplicationLogger.logInfo("Entering getAccountDetail API");
    String accountDetail = null;
    ResponseEntity<AccountDetailResponse> response = accountsService.getAccountDetailsResponseEntity(customerId);
    return response;
  }

  @ApiOperation(value = "Returns selected account transaction history")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountTransactionsResponse> getAccountTransactions(
      @PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "accountId", required = true) Integer accountId) {
    ApplicationLogger.logInfo("Entering getAccountTransactions");
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
      ApplicationLogger.logError("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    ApplicationLogger.logInfo("Cards Response : " + response);
    return response;
  }

  @ApiOperation(value = "Returns list of accounts based on customer ID")
  @RequestMapping(value = "/{customerId}/accounts", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountsResponse> getAccounts(@PathVariable(name = "customerId", required = true) Integer customerId) {
    ApplicationLogger.logInfo("Entering getAccounts API");
    ResponseEntity<AccountsResponse> response = accountsService.getAccountsResponseEntity();
    return response;
  }

}
