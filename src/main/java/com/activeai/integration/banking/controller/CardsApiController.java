package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.ActivationCardRequest;
import com.activeai.integration.banking.domain.request.BlockCardRequest;
import com.activeai.integration.banking.domain.request.CreditCardLimitConfirmRequest;
import com.activeai.integration.banking.domain.request.DebitCardLimitConfirmRequest;
import com.activeai.integration.banking.domain.request.ResetPinConfirmRequest;
import com.activeai.integration.banking.domain.request.ReplaceCardConfirmRequest;
import com.activeai.integration.banking.domain.request.InternationalCardUsageRequest;
import com.activeai.integration.banking.domain.response.CardDetailResponse;
import com.activeai.integration.banking.domain.response.CardTransactionsResponse;
import com.activeai.integration.banking.domain.response.CardsResponse;
import com.activeai.integration.banking.domain.response.DebitCardLimitResponse;
import com.activeai.integration.banking.domain.response.CreditCardLimitResponse;
import com.activeai.integration.banking.domain.response.DebitCardLimitConfirmResponse;
import com.activeai.integration.banking.domain.response.BlockCardResponse;
import com.activeai.integration.banking.domain.response.CreditCardLimitConfirmResponse;
import com.activeai.integration.banking.domain.response.ActivationCardResponse;
import com.activeai.integration.banking.domain.response.ResetPinConfirmResponse;
import com.activeai.integration.banking.domain.response.ReplaceCardConfirmResponse;
import com.activeai.integration.banking.domain.response.InternationalUsageResponse;
import com.activeai.integration.banking.services.CardsService;
import com.activeai.integration.banking.services.CreditCardService;
import com.activeai.integration.banking.services.DebitCardService;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



import java.io.IOException;
import java.time.OffsetDateTime;

@Api(value = "Cards Related APIs", description = "Shows API Documentation Regards Cards APIs") @RestController
public class CardsApiController {

  @Autowired private ObjectMapper objectMapper;
  @Autowired private CardsService cardsService;
  @Autowired private DebitCardService debitCardService;
  @Autowired private CreditCardService creditCardService;

  private static final Logger logger = LoggerFactory.getLogger(CardsApiController.class);

  @ApiOperation(value = "Returns list of credit cards based on customer ID")
  @RequestMapping(value = "/{customerId}/cards/creditcards", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardsResponse> getCreditCards(@PathVariable(value = "customerId", required = true) String customerId) {
    logger.info("Entering getCreditCards");
    return cardsService.getCreditCardsResponseEntity(customerId);
  }

  @ApiOperation(value = "Returns selected credit card details")
  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardDetailResponse> getCreditCardDetails(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber) {
    logger.info("Entering getCreditCardDetails");
    return cardsService.getCreditCardDetailsResponseEntity(customerId, cardNumber);
  }


  @ApiOperation(value = "Returns selected card transaction history")
  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/transactions", produces = {
      "application/json"}, method = RequestMethod.GET) public ResponseEntity<CardTransactionsResponse> getCreditCardTransactions(
      @PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber) {
    logger.info("Entering getCreditCardTransactions");
    return cardsService.getCreditAccountTransactionsResponseEntity(customerId, cardNumber);
  }

  /*
   * Getting DebitCardLimit
   */
  @ApiOperation(value = "Returns Debit Card Limit")
  @RequestMapping(value = "/{customerId}/debitcards/{cardNumber}/getLimits", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<DebitCardLimitResponse> getDebitCardLimits(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber) {
    logger.info("Entering getDebitLimitResponse");
    return debitCardService.getDebitCardLimitResponseEntity(customerId, cardNumber);
  }

  @ApiOperation(value = "Returns Credit Card Limit")
  @RequestMapping(value = "/{customerId}/creditcards/{cardNumber}/getLimits", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CreditCardLimitResponse> getCreditCardLimits(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber) {
    logger.info("Entering getCreditLimitResponse");
    return creditCardService.getCreditCardLimitResponseEntity(customerId, cardNumber);
  }

  /*
   * Getting DebitCardLimitConfirmation
   */
  @ApiOperation(value = "Returns Confirmation of Debit Card Limit")
  @RequestMapping(value = "/{customerId}/debitcards/{cardNumber}/limit/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<DebitCardLimitConfirmResponse> confirmDebitCardLimit(
      @PathVariable(value = "customerId", required = true) String customerId,@PathVariable(value = "cardNumber", required = true) String cardNumber,
      @RequestBody final DebitCardLimitConfirmRequest debitCardLimitConfirmRequest) {
    ApplicationLogger.logInfo("Entering getDebitCardLimitConfirm API");
    return debitCardService.getDebitLimitConfirmResponseEntity(debitCardLimitConfirmRequest);
  }

  @ApiOperation(value = "Returns Confirmation of Credit Card Limit")
  @RequestMapping(value = "/{customerId}/creditcards/limit/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST) public ResponseEntity<CreditCardLimitConfirmResponse> confirmCreditCardLimit(
      @PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final CreditCardLimitConfirmRequest creditCardLimitConfirmRequest) {
    ApplicationLogger.logInfo("Entering getCreditCardLimitConfirm API");
    return creditCardService.getCreditLimitConfirmResponseEntity(creditCardLimitConfirmRequest);
  }

  /*
   * Getting BlockCardStatus
   */
  @ApiOperation("Return Blocking Status of Selected Card")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/block/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<BlockCardResponse> blockCard(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber, @RequestBody final BlockCardRequest blockCardRequest) {
       ApplicationLogger.logInfo("Entering getBlockStatus API");
       return cardsService.getBlockCardResponseEntity(blockCardRequest);
  }

  /*
   * Getting ActivateCardStatus
   */
  @ApiOperation("Return Activation Status of Selected Card")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/activation/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<ActivationCardResponse> activationCard(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber, @RequestBody final ActivationCardRequest activationCardRequest) {
    ApplicationLogger.logInfo("Entering getActivationStatus API");
    return cardsService.getActivationCardResponseEntity(activationCardRequest);
  }

  /*
   * Getting ResetPin
   */
  @ApiOperation("Return Reset Pin Status of Selected Card")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/resetPin/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<ResetPinConfirmResponse> resetPin(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber, @RequestBody final ResetPinConfirmRequest resetPinConfirmRequest) {
    ApplicationLogger.logInfo("Entering getResetPin Confirm API");
    return cardsService.getResetPinResponseEntity(resetPinConfirmRequest);
  }

  /*
   * Getting ResetPin
   */
  @ApiOperation("Return Status of Replacement of Card")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/replaceCard/confirm", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<ReplaceCardConfirmResponse> replaceCard(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber, @RequestBody final ReplaceCardConfirmRequest replaceCardConfirmRequest) {
    ApplicationLogger.logInfo("Entering getReplaceCard Confirm API");
    return cardsService.getReplaceCardResponseEntity(replaceCardConfirmRequest);
  }



  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/overseasUse", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT) public ResponseEntity<CardDetailResponse> updateCreditCardOverseasUsage(
      @PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
      @RequestParam(value = "activationMode", required = true) String activationMode) {
    ResponseEntity<CardDetailResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : {    \"branchId\" : \"branchId\",    \"product\" : \"product\",    \"closingBalance\" : 6.027456183070403,    \"branchName\" : \"branchName\",    \"type\" : \"CREDIT\",    \"accountNumber\" : \"accountNumber\",    \"cardIssuer\" : \"Visa\",    \"amountDue\" : 1.4658129805029452,    \"accountId\" : \"accountId\",    \"paymentDueDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"lastStatementDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"productCode\" : \"productCode\",    \"lastStatementBalance\" : 3.616076749251911,    \"oversearCardActivated\" : true,    \"minimumPayment\" : 5.962133916683182,    \"creditLimit\" : 5.637376656633329,    \"displayAccountNumber\" : \"123xxxx890\",    \"permanentCreditLimit\" : 9.301444243932576,    \"category\" : \"category\",    \"openingBalance\" : 0.8008281904610115,    \"availableCreditLimit\" : 2.3021358869347655,    \"temporaryCreditLimit\" : 7.061401241503109,    \"status\" : \"ACTIVE\"  }}",
          CardDetailResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return response;
  }

  @ApiOperation("Return International Usage of Card")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/internationalUsage/enabled", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<InternationalUsageResponse> updateInternationalUsage(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber, @RequestBody final InternationalCardUsageRequest internationalCardUsageRequest ) {
    ApplicationLogger.logInfo("Entering updateInternationalUsage  API");
    return cardsService.getInternationalUsageCardResponseEntity(internationalCardUsageRequest);
  }
  @ApiOperation("Return International Usage Final API Call ")
  @RequestMapping(value = "/{customerId}/cards/{cardNumber}/internationalUsage/api", produces = {"application/json"}, consumes = {
      "application/json"}, method = RequestMethod.POST)
  public ResponseEntity<InternationalUsageResponse> updateInternationalUsageFinalApiCall(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber, @RequestBody final InternationalCardUsageRequest internationalCardUsageRequest ) {
    ApplicationLogger.logInfo("Entering updateInternationalUsage  API");
    return cardsService.updateInternationalUsageFinalApiCall(internationalCardUsageRequest);
  }

  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/payment", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT)
  public ResponseEntity<Void> cardPayment(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber, @RequestParam(value = "amount", required = true) Double amount,
      @RequestParam(value = "date", required = true) OffsetDateTime date,
      @RequestParam(value = "currency", required = true) String currency,
      @RequestParam(value = "remarks", required = true) String remarks) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ApiOperation(value = "Returns list of debit cards based on customer ID")
  @RequestMapping(value = "/{customerId}/cards/debitcards", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardsResponse> getDebitCards(@PathVariable(value = "customerId", required = true) String customerId) {
    logger.info("Entering getDebitCards");
    return cardsService.getDebitCardsResponseEntity(customerId);
  }

  @ApiOperation(value = "Returns selected debit card details")
  @RequestMapping(value = "/{customerId}/cards/debitcards/{cardNumber}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardDetailResponse> getDebitCardDetails(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber) {
    logger.info("Entering getDebitCardDetails");
    return cardsService.getDebitCardDetailsResponseEntity(customerId, cardNumber);
  }

  @RequestMapping(value = "/{customerId}/cards/debitcards/{cardNumber}/overseasUse", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT) public ResponseEntity<CardDetailResponse> updateDebitCardOverseasUsage(
      @PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
      @RequestParam(value = "activationMode", required = true) String activationMode) {
    ResponseEntity<CardDetailResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : {    \"branchId\" : \"branchId\",    \"product\" : \"product\",    \"closingBalance\" : 6.027456183070403,    \"branchName\" : \"branchName\",    \"type\" : \"CREDIT\",    \"accountNumber\" : \"accountNumber\",    \"cardIssuer\" : \"Visa\",    \"amountDue\" : 1.4658129805029452,    \"accountId\" : \"accountId\",    \"paymentDueDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"lastStatementDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"productCode\" : \"productCode\",    \"lastStatementBalance\" : 3.616076749251911,    \"oversearCardActivated\" : true,    \"minimumPayment\" : 5.962133916683182,    \"creditLimit\" : 5.637376656633329,    \"displayAccountNumber\" : \"123xxxx890\",    \"permanentCreditLimit\" : 9.301444243932576,    \"category\" : \"category\",    \"openingBalance\" : 0.8008281904610115,    \"availableCreditLimit\" : 2.3021358869347655,    \"temporaryCreditLimit\" : 7.061401241503109,    \"status\" : \"ACTIVE\"  }}",
          CardDetailResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return response;
  }

}
