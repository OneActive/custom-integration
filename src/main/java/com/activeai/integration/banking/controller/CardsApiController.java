package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.BillPaymentRequest;
import com.activeai.integration.banking.domain.request.DebitCardLimitRequest;
import com.activeai.integration.banking.domain.response.*;
import com.activeai.integration.banking.services.CardsService;
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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.OffsetDateTime;

@Api(value = "Cards Related APIs", description = "Shows API Documentation Regards Cards APIs")
@RestController
public class CardsApiController {

  @Autowired private ObjectMapper objectMapper;
  @Autowired private CardsService cardsService;

  private static final Logger logger = LoggerFactory.getLogger(CardsApiController.class);
  @Autowired private DebitCardService debitCardService;

  @ApiOperation(value = "Returns list of credit cards based on customer ID")
  @RequestMapping(value = "/{customerId}/cards/creditcards", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardsResponse> getCreditCards(@PathVariable(value = "customerId", required = true) String customerId) {
    logger.info("Entering getCreditCards");
    return cardsService.getCardsResponseEntity(customerId);
  }

  @ApiOperation(value = "Returns selected card details")
  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardDetailResponse> getCreditCardDetails(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardId", required = true) String cardId) {
    logger.info("Entering getCreditCardDetails");
    return cardsService.getCardDetailsResponseEntity(customerId, cardId);
  }

  @ApiOperation(value = "Returns selected card transaction history")
  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardId}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardTransactionsResponse> getCreditCardTransactions(
      @PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardId", required = true) String cardId) {
    logger.info("Entering getCreditCardTransactions");
    return cardsService.getAccountTransactionsResponseEntity(customerId, cardId);
  }

  @ApiOperation(value = "Returns Debit Card Limit")
  @RequestMapping(value = "/{customerId}/debitcards/{cardId}/getLimits", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<DebitCardLimitResponse> getDebitCardLimits(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardId", required = true) String cardId) {
    logger.info("Entering getDebitLimitResponse");
    return debitCardService.getDebitCardLimitResponseEntity(customerId, cardId);
  }

  @ApiOperation(value = "Returns Confirmation of Debit Card Limit")
  @RequestMapping(value = "/{customerId}/debitcards/limit/confirm", produces = {"application/json"}, consumes = {"application/json"},
      method = RequestMethod.POST)
  public ResponseEntity<DebitCardLimitResponse> confirmDebitCardLimit(@PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final DebitCardLimitRequest debitCardLimitRequest) {
    ApplicationLogger.logInfo("Entering getBillPaymentConfirm API");
    return debitCardService.getDebitLimitConfirmResponseEntity(debitCardLimitRequest);
  }


  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardId}/overseasUse", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> updateCreditCardOverseasUsage(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardId", required = true) String cardId,
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

  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardId}/limitUpdate", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> creditCardlimitUpdate(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardId", required = true) String cardId,
      @RequestParam(value = "limitUpdateType", required = true) String limitUpdateType,
      @RequestParam(value = "amount", required = true) Double amount, @RequestParam(value = "reason", required = true) String reason,
      @RequestParam(value = "fromDate", required = false) OffsetDateTime fromDate,
      @RequestParam(value = "toDate", required = false) OffsetDateTime toDate) {
    ResponseEntity<CardDetailResponse> response = null;
    try {
      response = new ResponseEntity<CardDetailResponse>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : {    \"branchId\" : \"branchId\",    \"product\" : \"product\",    \"closingBalance\" : 6.027456183070403,    \"branchName\" : \"branchName\",    \"type\" : \"CREDIT\",    \"accountNumber\" : \"accountNumber\",    \"cardIssuer\" : \"Visa\",    \"amountDue\" : 1.4658129805029452,    \"accountId\" : \"accountId\",    \"paymentDueDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"lastStatementDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"productCode\" : \"productCode\",    \"lastStatementBalance\" : 3.616076749251911,    \"oversearCardActivated\" : true,    \"minimumPayment\" : 5.962133916683182,    \"creditLimit\" : 5.637376656633329,    \"displayAccountNumber\" : \"123xxxx890\",    \"permanentCreditLimit\" : 9.301444243932576,    \"category\" : \"category\",    \"openingBalance\" : 0.8008281904610115,    \"availableCreditLimit\" : 2.3021358869347655,    \"temporaryCreditLimit\" : 7.061401241503109,    \"status\" : \"ACTIVE\"  }}",
          CardDetailResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<CardDetailResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardId}/resetPin", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> resetCreditCardPin(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardId", required = true) String cardId, @RequestParam(value = "resetType", required = true) String resetType,
      @RequestParam(value = "newPin", required = true) String newPin) {
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

  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardId}/payment", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT)
  public ResponseEntity<Void> cardPayment(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardId", required = true) String cardId, @RequestParam(value = "amount", required = true) Double amount,
      @RequestParam(value = "date", required = true) OffsetDateTime date,
      @RequestParam(value = "currency", required = true) String currency,
      @RequestParam(value = "remarks", required = true) String remarks) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
