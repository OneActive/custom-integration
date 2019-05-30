package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.response.AccountDetailResponse;
import com.activeai.integration.banking.domain.response.AccountTransactionsResponse;
import com.activeai.integration.banking.domain.response.AccountsResponse;
import com.activeai.integration.banking.domain.response.DepositAccountsResponse;
import com.activeai.integration.banking.domain.response.AccountBalanceResponse;
import com.activeai.integration.banking.domain.response.LoanAccountsResponse;
import com.activeai.integration.banking.services.AccountsService;
import com.activeai.integration.banking.services.AccountDetailsService;
import com.activeai.integration.banking.services.AccountBalanceService;
import com.activeai.integration.banking.domain.response.DepositAccountDetailResponse;
import com.activeai.integration.banking.domain.response.LoanAccountDetailResponse;
import com.activeai.integration.banking.domain.response.LoanAccountBalanceResponse;
import com.activeai.integration.banking.domain.response.DepositAccountBalanceResponse;
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
  @Autowired private AccountDetailsService accountDetailsService;
  @Autowired private AccountBalanceService accountBalanceService;

  @ApiOperation(value = "Returns selected casa account balance")
  @RequestMapping(value = "/{customerId}/accounts/CASA/{accountId}/balance", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountBalanceResponse> getCasaAccountBalance(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getCasaAccountBalance API");
    return accountBalanceService.getCasaAccountBalanceResponseEntity(customerId, accountId);
  }
  @ApiOperation(value = "Returns selected deposit account balance")
  @RequestMapping(value = "/{customerId}/accounts/DEPOSIT/{accountId}/balance", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<DepositAccountBalanceResponse> getDepositAccountBalance(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getDepositAccountBalance API");
    return accountBalanceService.getDepositAccountBalanceResponseEntity(customerId, accountId);
  }
  @ApiOperation(value = "Returns selected loan account balance")
  @RequestMapping(value = "/{customerId}/accounts/LOAN/{accountId}/balance", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<LoanAccountBalanceResponse> getLoanAccountBalance(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getCasaAccountBalance API");
    return accountBalanceService.getLoanAccountBalanceResponseEntity(customerId, accountId);
  }
  @ApiOperation(value = "Returns selected casa account details")
  @RequestMapping(value = "/{customerId}/accounts/CASA/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountDetailResponse> getCasaAccountDetail(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getCasaAccountDetail API");
    return accountDetailsService.getCasaAccountDetailsResponseEntity(customerId, accountId);
  }
  @ApiOperation(value = "Returns selected deposit account details")
  @RequestMapping(value = "/{customerId}/accounts/DEPOSIT/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<DepositAccountDetailResponse> getDepositAccountDetail(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getDepositAccountDetail API");
    return accountDetailsService.getDepositAccountDetailsResponseEntity(customerId, accountId);
  }
  @ApiOperation(value = "Returns selected loan account details")
  @RequestMapping(value = "/{customerId}/accounts/LOAN/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<LoanAccountDetailResponse> getLoanAccountDetail(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getLoanAccountDetail API");
    return accountDetailsService.getLoanAccountDetailsResponseEntity(customerId, accountId);
  }
  @ApiOperation(value = "Returns selected casa account transaction history")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountTransactionsResponse> getCasaAccountTransactions(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getCasaAccountTransactions API");
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
