package com.activeai.integration.banking.api.controller;

import com.activeai.integration.banking.api.services.AccountBalanceService;
import com.activeai.integration.banking.api.services.AccountDetailsService;
import com.activeai.integration.banking.api.services.AccountsService;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.domain.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
  @GetMapping(value = "/{customerId}/accounts/casa/{accountId}/balance", produces = {"application/json"})
  public ResponseEntity<AccountBalanceResponse> getCasaAccountBalance(@PathVariable(name = "customerId") String customerId,
      @PathVariable(name = "accountId", required = true) String accountId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getCasaAccountBalance API", this.getClass());
    return accountBalanceService.getCasaAccountBalanceResponseEntity(customerId, accountId, accessToken);
  }

  /*
  Return Balance of Selected Deposit Account
   */
  @ApiOperation(value = "Returns selected deposit account balance")
  @GetMapping(value = "/{customerId}/accounts/deposit/{accountId}/balance", produces = {"application/json"})
  public ResponseEntity<DepositAccountBalanceResponse> getDepositAccountBalance(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getDepositAccountBalance API", this.getClass());
    return accountBalanceService.getDepositAccountBalanceResponseEntity(customerId, accountId, accessToken);
  }

  /*
  Return Balance of Selected of Loan Account
   */
  @ApiOperation(value = "Returns selected loan account balance")
  @GetMapping(value = "/{customerId}/accounts/loan/{accountId}/balance", produces = {"application/json"})
  public ResponseEntity<LoanAccountBalanceResponse> getLoanAccountBalance(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getCasaAccountBalance API", this.getClass());
    return accountBalanceService.getLoanAccountBalanceResponseEntity(customerId, accountId, accessToken);
  }

  /*
  Return Selected CASA Account Details
   */
  @ApiOperation(value = "Returns selected casa account details")
  @GetMapping(value = "/{customerId}/accounts/casa/{accountId}", produces = {"application/json"})
  public ResponseEntity<AccountDetailResponse> getCasaAccountDetail(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getCasaAccountDetail API", this.getClass());
    return accountDetailsService.getCasaAccountDetailsResponseEntity(customerId, accountId, accessToken);
  }

  /*
  Return Selected Deposit Account Details
   */
  @ApiOperation(value = "Returns selected deposit account details")
  @GetMapping(value = "/{customerId}/accounts/deposit/{accountId}", produces = {"application/json"})
  public ResponseEntity<DepositAccountDetailResponse> getDepositAccountDetail(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getDepositAccountDetail API", this.getClass());
    return accountDetailsService.getDepositAccountDetailsResponseEntity(customerId, accountId, accessToken);
  }

  /*
  Return Selected Loan Account  Details
   */
  @ApiOperation(value = "Returns selected loan account details")
  @GetMapping(value = "/{customerId}/accounts/loan/{accountId}", produces = {"application/json"})
  public ResponseEntity<LoanAccountDetailResponse> getLoanAccountDetail(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getLoanAccountDetail API", this.getClass());
    return accountDetailsService.getLoanAccountDetailsResponseEntity(customerId, accountId, accessToken);
  }

  /*
  Return Selected CASA Account Transaction History
   */
  @ApiOperation(value = "Returns selected casa account transaction history")
  @GetMapping(value = "/{customerId}/accounts/{accountId}/transactions", produces = {"application/json"})
  public ResponseEntity<AccountTransactionsResponse> getCasaAccountTransactions(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getCasaAccountTransactions API", this.getClass());
    return accountsService.getAccountTransactionsResponseEntity(customerId, accountId, accessToken);
  }

  /*
  Return List of CASA Account
   */
  @ApiOperation(value = "Returns list of casa accounts based on customer ID")
  @GetMapping(value = "/{customerId}/accounts/casa", produces = {"application/json"})
  public ResponseEntity<AccountsResponse> getCasaAccounts(@PathVariable(name = "customerId", required = true) String customerId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getCasaAccountsResponseEntity API", this.getClass());
    return accountsService.getCasaAccountsResponseEntity(customerId, accessToken);
  }

  /*
  Return List of Deposit Accounts
   */
  @ApiOperation(value = "Returns list of deposit accounts based on customer ID")
  @GetMapping(value = "/{customerId}/accounts/deposit", produces = {"application/json"})
  public ResponseEntity<DepositAccountsResponse> getDepositAccounts(@PathVariable(name = "customerId", required = true) String customerId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getDepositAccountsResponseEntity API", this.getClass());
    return accountsService.getDepositAccountsResponseEntity(customerId, accessToken);
  }

  /*
  Return List of Loan Accounts
   */
  @ApiOperation(value = "Returns list of loan accounts based on customer ID")
  @GetMapping(value = "/{customerId}/accounts/loan", produces = {"application/json"})
  public ResponseEntity<LoanAccountsResponse> getLoanAccounts(@PathVariable(name = "customerId", required = true) String customerId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getLoanAccountsResponseEntity API", this.getClass());
    return accountsService.getLoanAccountsResponseEntity(customerId, accessToken);
  }

}
