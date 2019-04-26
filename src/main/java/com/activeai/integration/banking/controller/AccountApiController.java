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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Accounts Related APIs", description = "Shows API Documentation Regards Accounts APIs")
@RestController
public class AccountApiController {

  @Autowired private AccountsService accountsService;
  @Autowired private ObjectMapper objectMapper;

  @ApiOperation(value = "Returns selected account balance")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}/balance", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountBalanceResponse> getAccountBalance(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getAccountBalance API");
    return accountsService.getAccountBalanceResponseEntity(customerId, accountId);
  }

  @ApiOperation(value = "Returns selected account details")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountDetailResponse> getAccountDetail(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getAccountDetail API");
    return accountsService.getAccountDetailsResponseEntity(customerId, accountId);
  }

  @ApiOperation(value = "Returns selected account transaction history")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountTransactionsResponse> getAccountTransactions(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getAccountTransactions API");
    return accountsService.getAccountTransactionsResponseEntity(customerId, accountId);
  }

  @ApiOperation(value = "Returns list of accounts based on customer ID")
  @RequestMapping(value = "/{customerId}/accounts", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountsResponse> getAccounts(@PathVariable(name = "customerId", required = true) String customerId) {
    ApplicationLogger.logInfo("Entering getAccounts API");
    return accountsService.getAccountsResponseEntity(customerId);
  }

}
