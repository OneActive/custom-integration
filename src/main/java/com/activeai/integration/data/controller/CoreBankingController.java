package com.activeai.integration.data.controller;

import com.activeai.integration.banking.domain.response.AccountTransactionsResponse;
import com.activeai.integration.banking.domain.response.CardTransactionsResponse;
import com.activeai.integration.banking.domain.response.PayeesResponse;
import com.activeai.integration.data.model.CoreBankingModel;
import com.activeai.integration.data.service.CoreBankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.data.service.GenericService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

@RestController("coreBankingController")
@RequestMapping("data")
@Api(value = "Handle Redis Data", description = "To Reset Redis Data as per the requirement")
public class CoreBankingController {

  @Autowired
  private GenericService genericService;

  @Autowired
  private CoreBankingService coreBankingService;


  /*
  Return health check
   */
  @ApiOperation(value = "Returns health check api")
  @RequestMapping(value = "/health", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<String> healthCheck() {
	ApplicationLogger.logInfo("Entering health chack API");
	String response = "Banking service is up and running";
    return ResponseEntity.ok(response);
  }

  @ApiOperation(value = "Clear Redis Completely")
  @RequestMapping(value = "/reset", method = RequestMethod.GET)
  public String resetCache() {
    return genericService.resetAll();
  }

  @ApiOperation(value = "Delete Customer specific information from redis")
  @RequestMapping(value = "/reset/{customerId}", method = RequestMethod.GET)
  public String resetCacheByCustomerId(@PathVariable(value = "customerId") String customerId) {
    return genericService.resetCacheByCustomerId(customerId);
  }

  @ApiOperation(value = "Delete Credit cards from redis")
  @RequestMapping(value = "/reset/{customerId}/creditcards", method = RequestMethod.GET)
  public String resetCreditCards(@PathVariable(value = "customerId") String customerId) {
    return genericService.resetCreditCards(customerId);
  }

  @ApiOperation(value = "Delete Card transactions from redis")
  @RequestMapping(value = "/reset/{customerId}/cards/transactions", method = RequestMethod.GET)
  public String resetCardTransactions(@PathVariable(value = "customerId") String customerId) {
    return genericService.resetCardsTransactions(customerId);
  }

  @ApiOperation(value = "Delete accounts transaction from redis")
  @RequestMapping(value = "/reset/{customerId}/accounts/transactions", method = RequestMethod.GET)
  public String resetAccountsTransactions(@PathVariable(value = "customerId") String customerId) {
    return genericService.resetAccountsTransactions(customerId);
  }

  /**
   * Update Payee Response for specific customers
   *
   * @param payeesResponse
   * @param customerId
   * @return
   */
  @ApiOperation(value = "Update Payee Response for specific users")
  @RequestMapping(value = "/init/{customerId}/payee", consumes = {"application/json"}, method = RequestMethod.POST)
  public ResponseEntity<String> resetPayeeResponse(@RequestBody PayeesResponse payeesResponse,
      @PathVariable(name = "customerId", required = true) String customerId) {
    ApplicationLogger.logInfo("Updating payee response...");
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    coreBankingModel.setPayeesResponse(payeesResponse);
    coreBankingService.saveCoreBankingModel(coreBankingModel);
    String response = "Re-initialised Payee response successfully!";
    return ResponseEntity.ok(response);
  }

  /**
   * Reset Account related Transactions for specific customer
   *
   * @param accountTransactionsResponseMap
   * @param customerId
   * @param accountId
   * @return
   */
  @ApiOperation(value = "Update accounts transactions Response for specific users")
  @RequestMapping(value = "/init/{customerId}/accounts/transactions", consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<String> resetAccountTransactionsResponse(
      @RequestBody Map<String, AccountTransactionsResponse> accountTransactionsResponseMap,
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Updating account transactions response...");
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    coreBankingModel.setAccountTransactionsResponse(accountTransactionsResponseMap);
    coreBankingService.saveCoreBankingModel(coreBankingModel);
    String response = "Re-initialised account response successfully!";
    return ResponseEntity.ok(response);
  }

  /**
   * Reset Card related Transactions for specific customer
   *
   * @param cardTransactionsResponseMap
   * @param customerId
   * @param accountId
   * @return
   */
  @ApiOperation(value = "Update Payee Response for specific users")
  @RequestMapping(value = "/init/{customerId}/cards/transactions", consumes = {"application/json"}, method = RequestMethod.POST)
  public ResponseEntity<String> resetCardTransactionsResponse(
      @RequestBody Map<String, CardTransactionsResponse> cardTransactionsResponseMap,
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "accountId", required = true) String accountId) {
    ApplicationLogger.logInfo("Updating card response...");
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    coreBankingModel.setCardTransactionsResponse(cardTransactionsResponseMap);
    coreBankingService.saveCoreBankingModel(coreBankingModel);
    String response = "Re-initialised card response successfully";
    return ResponseEntity.ok(response);
  }

}
