package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.AccountRequest;
import com.activeai.integration.banking.domain.response.AccountBalanceResponse;
import com.activeai.integration.banking.domain.response.DepositAccountBalanceResponse;
import com.activeai.integration.banking.domain.response.LoanAccountBalanceResponse;
import com.activeai.integration.banking.domain.response.AccountDetailResponse;
import com.activeai.integration.banking.domain.response.DepositAccountDetailResponse;
import com.activeai.integration.banking.domain.response.LoanAccountDetailResponse;
import com.activeai.integration.banking.domain.response.AccountTransactionsResponse;
import com.activeai.integration.banking.domain.response.AccountsResponse;
import com.activeai.integration.banking.domain.response.DepositAccountsResponse;
import com.activeai.integration.banking.domain.response.LoanAccountsResponse;
import com.activeai.integration.banking.services.AccountsService;
import com.activeai.integration.banking.services.AccountDetailsService;
import com.activeai.integration.banking.services.AccountBalanceService;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(value = "Accounts Related APIs", description = "Shows API documentation regarding accounts APIs like current and savings account, loan accounts, deposit accounts")
@RestController
public class AccountApiController {

  @Autowired private AccountsService accountsService;
  @Autowired private AccountDetailsService accountDetailsService;
  @Autowired private AccountBalanceService accountBalanceService;

  /*
  Return Balance of Selected CASA Account
   */
  @ApiOperation(value = "Returns selected casa account balance")
  @RequestMapping(value = "/{customerId}/accounts/casa/{accountId}/balance", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountBalanceResponse> getCasaAccountBalance(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getCasaAccountBalance API");
    return accountBalanceService.getCasaAccountBalanceResponseEntity(customerId, accountId);
  }

  /*
  Return Balance of Selected Deposit Account
   */
  @ApiOperation(value = "Returns selected deposit account balance")
  @RequestMapping(value = "/{customerId}/accounts/deposit/{accountId}/balance", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<DepositAccountBalanceResponse> getDepositAccountBalance(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getDepositAccountBalance API");
    return accountBalanceService.getDepositAccountBalanceResponseEntity(customerId, accountId);
  }

  /*
  Return Balance of Selected of Loan Account
   */
  @ApiOperation(value = "Returns selected loan account balance")
  @RequestMapping(value = "/{customerId}/accounts/loan/{accountId}/balance", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<LoanAccountBalanceResponse> getLoanAccountBalance(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getCasaAccountBalance API");
    return accountBalanceService.getLoanAccountBalanceResponseEntity(customerId, accountId);
  }

  /*
  Return Selected CASA Account Details
   */
  @ApiOperation(value = "Returns selected casa account details")
  @RequestMapping(value = "/{customerId}/accounts/casa/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountDetailResponse> getCasaAccountDetail(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getCasaAccountDetail API");
    return accountDetailsService.getCasaAccountDetailsResponseEntity(customerId, accountId);
  }

  /*
  Return Selected Deposit Account Details
   */
  @ApiOperation(value = "Returns selected deposit account details")
  @RequestMapping(value = "/{customerId}/accounts/deposit/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<DepositAccountDetailResponse> getDepositAccountDetail(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getDepositAccountDetail API");
    return accountDetailsService.getDepositAccountDetailsResponseEntity(customerId, accountId);
  }

  /*
  Return Selected Loan Account  Details
   */
  @ApiOperation(value = "Returns selected loan account details")
  @RequestMapping(value = "/{customerId}/accounts/loan/{accountId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<LoanAccountDetailResponse> getLoanAccountDetail(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getLoanAccountDetail API");
    return accountDetailsService.getLoanAccountDetailsResponseEntity(customerId, accountId);
  }

  /*
  Return Selected CASA Account Transaction History
   */
  @ApiOperation(value = "Returns selected casa account transaction history")
  @RequestMapping(value = "/{customerId}/accounts/{accountId}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<AccountTransactionsResponse> getCasaAccountTransactions(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Entering getCasaAccountTransactions API");
    return accountsService.getAccountTransactionsResponseEntity(customerId, accountId);
  }

  /*
  Return List of CASA Account
   */
  @ApiOperation(value = "Returns list of casa accounts based on customer ID")
  @RequestMapping(value = "/{customerId}/accounts/casa", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
  public ResponseEntity<AccountsResponse> getCasaAccounts(@PathVariable(name = "customerId", required = true)String customerId, @RequestBody final AccountRequest accountRequest) {
    ApplicationLogger.logInfo("Entering getCasaAccountsResponseEntity API");
    return accountsService.getCasaAccountsResponseEntity(accountRequest);
  }

  /*
  Return List of Deposit Accounts
   */
  @ApiOperation(value = "Returns list of deposit accounts based on customer ID")
  @RequestMapping(value = "/{customerId}/accounts/deposit", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<DepositAccountsResponse> getDepositAccounts(@PathVariable(name = "customerId", required = true) String customerId) {
    ApplicationLogger.logInfo("Entering getDepositAccountsResponseEntity API");
    return accountsService.getDepositAccountsResponseEntity(customerId);
  }

  /*
  Return List of Loan Accounts
   */
  @ApiOperation(value = "Returns list of loan accounts based on customer ID")
  @RequestMapping(value = "/{customerId}/accounts/loan", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<LoanAccountsResponse> getLoanAccounts(@PathVariable(name = "customerId", required = true) String customerId) {
    ApplicationLogger.logInfo("Entering getLoanAccountsResponseEntity API");
    return accountsService.getLoanAccountsResponseEntity(customerId);
  }

}
