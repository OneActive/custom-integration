package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.response.CardDetailResponse;
import com.activeai.integration.banking.domain.response.CardLimitResponse;
import com.activeai.integration.banking.domain.response.CardTransactionsResponse;
import com.activeai.integration.banking.domain.response.CardsResponse;
import com.activeai.integration.banking.services.CardsService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.OffsetDateTime;

@Api(value = "Cards Related APIs", description = "Shows API Documentation Regards Cards APIs")
@RestController
public class CardsApiController {

  @Autowired private ObjectMapper objectMapper;
  @Autowired private CardsService cardsService;

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

  @ApiOperation(value = "Returns selected credit card transaction history")
  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardTransactionsResponse> getCreditCardTransactions(
      @PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber) {
    logger.info("Entering getCreditCardTransactions");
    return cardsService.getCreditAccountTransactionsResponseEntity(customerId, cardNumber);
  }


  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/overseasUse", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> updateCreditCardOverseasUsage(@PathVariable(value = "customerId", required = true) String customerId,
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

  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/limitUpdate", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> creditCardlimitUpdate(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
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

  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/getLimits", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardLimitResponse> getCreditCardLimits(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber) {
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

  @RequestMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/resetPin", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> resetCreditCardPin(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber, @RequestParam(value = "resetType", required = true) String resetType,
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

  @RequestMapping(value = "/{customerId}/cards/debitcards/{cardNumber}/limitUpdate", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> debitCardlimitUpdate(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
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

  @RequestMapping(value = "/{customerId}/cards/debitcards/{cardNumber}/getLimits", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardLimitResponse> getDebitCardLimits(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber) {
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

  @RequestMapping(value = "/{customerId}/cards/debitcards/{cardNumber}/resetPin", produces = {"application/json"}, consumes = {
      "multipart/form-data"}, method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> resetDebitCardPin(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber, @RequestParam(value = "resetType", required = true) String resetType,
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
}
