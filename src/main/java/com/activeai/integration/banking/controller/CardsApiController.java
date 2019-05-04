package com.activeai.integration.banking.controller;

import java.io.IOException;
import java.time.OffsetDateTime;

import com.activeai.integration.banking.services.CardsService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activeai.integration.banking.domain.response.CardDetailResponse;
import com.activeai.integration.banking.mapper.response.CardLimitResponse;
import com.activeai.integration.banking.domain.response.CardTransactionsResponse;
import com.activeai.integration.banking.mapper.response.CardsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Api(value = "Cards Related APIs", description = "Shows API Documentation Regards Cards APIs")
@RestController
public class CardsApiController {

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private CardsService cardsService;

  private static final Logger logger = LoggerFactory.getLogger(CardsApiController.class);

  // TODO : Populate stub card list
  @ApiOperation(value = "Returns list of cards based on customer ID")
  @RequestMapping(value = "/{customerId}/cards", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardsResponse> getCards(@PathVariable(value = "customerId", required = true) String customerId) {
    logger.info("Entering getCards");
    return cardsService.getCardsResponseEntity(customerId);
  }


  @ApiOperation(value = "Returns selected card details")
  @RequestMapping(value = "/{customerId}/cards/{cardId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardDetailResponse> getCardDetail(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardId", required = true) String cardId) {
    logger.info("Entering getCardDetails");
    return cardsService.getCardDetailsResponseEntity(customerId,cardId);
  }

  @ApiOperation(value = "Returns selected card transaction history")
  @RequestMapping(value = "/{customerId}/cards/{cardId}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardTransactionsResponse> getCardTransactions(
      @PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardId", required = true) String cardId) {
    logger.info("Entering getCardTransactions");
    return cardsService.getAccountTransactionsResponseEntity(customerId,cardId);
  }

  @ApiOperation("Return selected card blocking status selected card")
  @RequestMapping(value = "/{customerId}/cards/{cardId}/block", produces = {"application/json"}, consumes = {"application/json"},
      method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> blockCard(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardId", required = true) String cardId,
      @RequestParam(value = "blockMode", required = true) String blockMode) {
    return cardsService.getBlockCardDetailsResponseEntity(customerId,blockMode);
  }

  @RequestMapping(value = "/{customerId}/cards/{cardId}/overseasUse", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> updateOverseasUsage(@PathVariable(value = "customerId", required = true) String customerId,
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

  @RequestMapping(value = "/{customerId}/cards/{cardId}/limitUpdate", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> limitUpdate(@PathVariable(value = "customerId", required = true) String customerId,
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

  @RequestMapping(value = "/{customerId}/cards/{cardId}/getLimits", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardLimitResponse> getCardLimits(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardId", required = true) String cardId) {
    ResponseEntity<CardLimitResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : {    \"overseasMonthlyLimit\" : 8.097541212E7,    \"dailyLimit\" : 8.097541212E7,    \"overseasDailyLimit\" : 8.097541212E7  }}",
          CardLimitResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/{customerId}/cards/{cardId}/resetPin", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> resetPin(@PathVariable(value = "customerId", required = true) String customerId,
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

  @RequestMapping(value = "/{customerId}/cards/{cardId}/payment", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.PUT)
  public ResponseEntity<Void> cardPayment(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardId", required = true) String cardId, @RequestParam(value = "amount", required = true) Double amount,
      @RequestParam(value = "date", required = true) OffsetDateTime date,
      @RequestParam(value = "currency", required = true) String currency,
      @RequestParam(value = "remarks", required = true) String remarks) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
