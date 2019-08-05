package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.*;
import com.activeai.integration.banking.domain.response.*;
import com.activeai.integration.banking.services.CardsService;
import com.activeai.integration.banking.services.CreditCardService;
import com.activeai.integration.banking.services.DebitCardService;
import com.activeai.integration.banking.utils.ApplicationLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Api(value = "Cards Related APIs", description = "Shows API documentation regarding cards APIs like credit card, debit card")
@RestController
public class CardsApiController {

  @Autowired private CardsService cardsService;
  @Autowired private DebitCardService debitCardService;
  @Autowired private CreditCardService creditCardService;

  private static final Logger logger = LoggerFactory.getLogger(CardsApiController.class);

  /*
  Return List of Credit Cards
   */
  @ApiOperation(value = "Returns list of credit cards based on customer ID")
  @RequestMapping(value = "/{customerId}/cards/creditcards", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardsResponse> getCreditCards(@PathVariable(value = "customerId", required = true) String customerId,
      @RequestParam(name = "accessToken") String accessToken) {
    logger.info("Entering getCreditCards");
    return cardsService.getCreditCardsResponseEntity(customerId, accessToken);
  }

  /*
  Return Selected Credit Card Details
   */
  @ApiOperation(value = "Returns selected credit card details")
  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardDetailResponse> getCreditCardDetails(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber, @RequestParam(name = "accessToken") String accessToken) {
    logger.info("Entering getCreditCardDetails");
    return cardsService.getCreditCardDetailsResponseEntity(customerId, cardNumber, accessToken);
  }

  /*
  Return Selected Credit Card Transaction History
   */
  @ApiOperation(value = "Returns selected card transaction history")
  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/transactions", produces = {
      "application/json"}, method = RequestMethod.GET) public ResponseEntity<CardTransactionsResponse> getCreditCardTransactions(
      @PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber, @RequestParam(name = "accessToken") String accessToken) {
    logger.info("Entering getCreditCardTransactions");
    return cardsService.getCreditAccountTransactionsResponseEntity(customerId, cardNumber, accessToken);
  }


  /*
  Return Selected Debit Card Limits
   */
  @ApiOperation(value = "Returns debit card limit")
  @RequestMapping(value = "/{customerId}/debitcards/{cardNumber}/getLimits", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<DebitCardLimitResponse> getDebitCardLimits(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber, @RequestParam(name = "accessToken") String accessToken) {
    logger.info("Entering getDebitLimitResponse");
    return debitCardService.getDebitCardLimitResponseEntity(customerId, cardNumber, accessToken);
  }

  /*
  Return Selected Credit Card Limit
   */
  @ApiOperation(value = "Returns credit card limit")
  @RequestMapping(value = "/{customerId}/creditcards/{cardNumber}/getLimits", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CreditCardLimitResponse> getCreditCardLimits(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber, @RequestParam(name = "accessToken") String accessToken) {
    logger.info("Entering getCreditLimitResponse");
    return creditCardService.getCreditCardLimitResponseEntity(customerId, cardNumber, accessToken);
  }

  /*
   * Getting DebitCardLimitConfirmation
   */
  @ApiOperation(value = "Returns confirmation of debit card limit")
  @RequestMapping(value = "/{customerId}/debitcards/{cardNumber}/limit/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<DebitCardLimitConfirmResponse> confirmDebitCardLimit(
      @PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
      @RequestBody final DebitCardLimitConfirmRequest debitCardLimitConfirmRequest) {
    ApplicationLogger.logInfo("Entering getDebitCardLimitConfirm API");
    return debitCardService.getDebitLimitConfirmResponseEntity(debitCardLimitConfirmRequest);
  }

  /*
  Return DEbit CArd Limit Confirmation
   */
  @ApiOperation(value = "Returns confirmation of credit card limit")
  @RequestMapping(value = "/{customerId}/creditcards/limit/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<CreditCardLimitConfirmResponse> confirmCreditCardLimit(
      @PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final CreditCardLimitConfirmRequest creditCardLimitConfirmRequest) {
    ApplicationLogger.logInfo("Entering getCreditCardLimitConfirm API");
    return creditCardService.getCreditLimitConfirmResponseEntity(creditCardLimitConfirmRequest);
  }

  /*
   * Return Status of Blocking of Card
   */
  @ApiOperation("Return blocking status of selected card")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/block/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<BlockCardResponse> blockCard(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber, @RequestBody final BlockCardRequest blockCardRequest) {
    ApplicationLogger.logInfo("Entering getBlockStatus API");
    return cardsService.getBlockCardResponseEntity(blockCardRequest);
  }

  /*
   * Return Status of Activation of Card
   */
  @ApiOperation("Return activation status of selected card")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/activation/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<ActivationCardResponse> activationCard(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final ActivationCardRequest activationCardRequest) {
    ApplicationLogger.logInfo("Entering getActivationStatus API");
    return cardsService.getActivationCardResponseEntity(activationCardRequest);
  }

  /*
   * Return Status of Resetting of Pin
   */
  @ApiOperation("Return reset pin status of selected card")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/resetPin/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<ResetPinConfirmResponse> resetPin(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final ResetPinConfirmRequest resetPinConfirmRequest) {
    ApplicationLogger.logInfo("Entering getResetPin Confirm API");
    return cardsService.getResetPinResponseEntity(resetPinConfirmRequest);
  }

  /*
  Return Status of Replacement of Card
   */
  @ApiOperation("Return status of replacement of card")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/replaceCard/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<ReplaceCardConfirmResponse> replaceCard(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final ReplaceCardConfirmRequest replaceCardConfirmRequest) {
    ApplicationLogger.logInfo("Entering getReplaceCard Confirm API");
    return cardsService.getReplaceCardResponseEntity(replaceCardConfirmRequest);
  }

  /*
  Return Intenational Usage Enabled of Card
   */
  @ApiOperation("Return international usage of card is enabled or disabled")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/internationalUsage/enabled", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<InternationalUsageResponse> updateInternationalUsage(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final InternationalCardUsageRequest internationalCardUsageRequest) {
    ApplicationLogger.logInfo("Entering updateInternationalUsage  API");
    return cardsService.getInternationalUsageCardResponseEntity(internationalCardUsageRequest);
  }

  /*
  Return Status of Internation Usage Enabled
   */
  @ApiOperation("Return international usage reference id and status ")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/internationalUsage/api", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<InternationalUsageResponse> updateInternationalUsageFinalApiCall(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final InternationalCardUsageRequest internationalCardUsageRequest) {
    ApplicationLogger.logInfo("Entering updateInternationalUsage  API");
    return cardsService.updateInternationalUsageFinalApiCall(internationalCardUsageRequest);
  }

  /*
  Return List of Debit Cards
   */
  @ApiOperation(value = "Returns list of debit cards based on customer ID")
  @RequestMapping(value = "/{customerId}/cards/debitcards", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardsResponse> getDebitCards(@PathVariable(value = "customerId", required = true) String customerId,
      @RequestParam(name = "accessToken") String accessToken) {
    logger.info("Entering getDebitCards");
    return cardsService.getDebitCardsResponseEntity(customerId, accessToken);
  }

  /*
  Return Selected Debit Card Details
   */
  @ApiOperation(value = "Returns selected debit card details")
  @RequestMapping(value = "/{customerId}/cards/debitcards/{cardNumber}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardDetailResponse> getDebitCardDetails(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber, @RequestParam(name = "accessToken") String accessToken) {
    logger.info("Entering getDebitCardDetails");
    return cardsService.getDebitCardDetailsResponseEntity(customerId, cardNumber, accessToken);
  }

  /*
   * Return the Conversion of Transaction to EMI for Credit Cards
   */
  @ApiOperation("Return Conversion to EMI from Transaction of Credit card")
  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/convertemi", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.GET) public ResponseEntity<ConvertEMIResponse> convertEMI(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber, @RequestParam(name = "accessToken") String accessToken) {
    ApplicationLogger.logInfo("Entering getEMIConversion API");
    return cardsService.getConvertEMIResponseEntity(customerId, cardNumber, accessToken);
  }

  /*
   * Return the Status of Conversion of Transaction to EMI for Credit Cards
   */
  @ApiOperation("Return status of Conversion to EMI from Transaction of Credit card")
  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/convertemi/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<ConvertEMIConfirmResponse> convertEMIConfirm(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final ConvertEMIConfirmRequest convertEMIConfirmRequest) {
    ApplicationLogger.logInfo("Entering getEMIConversion Confirm API");
    return cardsService.getConvertEMIConfirmResponseEntity(convertEMIConfirmRequest);
  }
}
