package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.response.AccountDetailResponse;
import com.activeai.integration.banking.domain.response.AccountTransactionsResponse;
import com.activeai.integration.banking.domain.response.AccountsResponse;
import com.activeai.integration.banking.domain.response.DepositAccountsResponse;
import com.activeai.integration.banking.domain.response.AccountBalanceResponse;
import com.activeai.integration.banking.domain.response.LoanAccountsResponse;
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
  @ApiOperation(value = "Returns selected casa account balance")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}/balance", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountBalanceResponse> getCasaAccountBalance(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getAccountBalance API");
    return accountsService.getAccountBalanceResponseEntity(customerId, accountId);
  }

  @ApiOperation(value = "Returns selected casa account details")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountDetailResponse> getCasaAccountDetail(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getAccountDetails API");
    return accountsService.getAccountDetailsResponseEntity(customerId, accountId);
  }

  @ApiOperation(value = "Returns selected casa account transaction history")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountTransactionsResponse> getCasaAccountTransactions(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getAccountTransactions API");
    return accountsService.getAccountTransactionsResponseEntity(customerId, accountId);
  }

  /**
   *
   * @param customerId
   * @return
   */
  @ApiOperation(value = "Returns list of casa accounts based on customer ID")
  @RequestMapping(value = "/{customerId}/accounts/CASA", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountsResponse> getCasaAccounts(@PathVariable(name = "customerId", required = true) String customerId) {
    ApplicationLogger.logInfo("Entering getCasaAccountsResponseEntity API");
    return accountsService.getCasaAccountsResponseEntity(customerId);
  }
  @ApiOperation(value = "Returns list of deposit accounts based on customer ID")
  @RequestMapping(value = "/{customerId}/accounts/DEPOSIT", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<DepositAccountsResponse> getDepositAccounts(@PathVariable(name = "customerId", required = true) String customerId) {
    ApplicationLogger.logInfo("Entering getDepositAccountsResponseEntity API");
    return accountsService.getDepositAccountsResponseEntity(customerId);
  }
  @ApiOperation(value = "Returns list of loan accounts based on customer ID")
  @RequestMapping(value = "/{customerId}/accounts/LOAN", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<LoanAccountsResponse> getLoanAccounts(@PathVariable(name = "customerId", required = true) String customerId) {
    ApplicationLogger.logInfo("Entering getLoanAccountsResponseEntity API");
    return accountsService.getLoanAccountsResponseEntity(customerId);
  }
}
