package com.activeai.integration.data.controller;

import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.data.service.GenericService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("coreBankingController")
@RequestMapping("/reset")
@Api(value = "Handle Redis Data", description = "To Reset Redis Data as per the requirement")
public class CoreBankingController {

  @Autowired
  private GenericService genericService;
  
  @Autowired
  private Environment env;
  
  /*
  Return health check
   */
  @ApiOperation(value = "Returns health check api")
  @RequestMapping(value = "/health", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<String> healthCheck() {
	ApplicationLogger.logInfo("Entering health chack API");
	ApplicationLogger.logInfo("Redis host: {}", env.getProperty("REDIS_HOST"));
	ApplicationLogger.logInfo("redis port: {}", env.getProperty("REDIS_PORT"));
	ApplicationLogger.logInfo("redis DB: {}", env.getProperty("REDIS_DATABASE"));
	ApplicationLogger.logInfo("is redis enabled: {}", env.getProperty("IS_REDIS_ENABLED"));
	String response = "Banking service is up and running";
    return ResponseEntity.ok(response);
  }

  @ApiOperation(value = "Clear Redis Completely")
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String resetCache() {
    return genericService.resetAll();
  }

  @ApiOperation(value = "Delete Customer specific information from redis")
  @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
  public String resetCacheByCustomerId(@PathVariable(value = "customerId") String customerId) {
    return genericService.resetCacheByCustomerId(customerId);
  }

  @ApiOperation(value = "Delete Credit cards from redis")
  @RequestMapping(value = "/{customerId}/creditcards", method = RequestMethod.GET)
  public String resetCreditCards(@PathVariable(value = "customerId") String customerId) {
    return genericService.resetCreditCards(customerId);
  }

  @ApiOperation(value = "Delete Card transactions from redis")
  @RequestMapping(value = "/{customerId}/cards/transactions", method = RequestMethod.GET)
  public String resetCardTransactions(@PathVariable(value = "customerId") String customerId) {
    return genericService.resetCardsTransactions(customerId);
  }

  @ApiOperation(value = "Delete accounts transaction from redis")
  @RequestMapping(value = "/{customerId}/accounts/transactions", method = RequestMethod.GET)
  public String resetAccountsTransactions(@PathVariable(value = "customerId") String customerId) {
    return genericService.resetAccountsTransactions(customerId);
  }

}
